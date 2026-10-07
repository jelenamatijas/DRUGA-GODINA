package jelena.etfbl.simulation;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.shape.StrokeLineCap;
import jelena.etfbl.bustraintransport.TransportController;
import jelena.etfbl.generate.TransportDataGenerator;
import jelena.etfbl.scenes.SceneManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * JavaFX controller for the simulation view.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Load generated transport data and build the city grid,</li>
 *   <li>Compute optimal routes according to a chosen criterion,</li>
 *   <li>Render the route on top of the grid and fill a table with leg details,</li>
 *   <li>Persist tickets for the currently selected optimal route.</li>
 * </ul>
 * <b>Time model:</b> all times are minutes since midnight.
 *
 * @author Jelena
 */
public class SimulationController {
    @FXML private ChoiceBox<String> departureCity_chb;
    @FXML private ChoiceBox<String> destinationCity_chb;
    @FXML private ChoiceBox<String> criteria_chb;
    @FXML private GridPane countryGrid_gp;
    @FXML private Button goBack_btn;
    @FXML private StackPane gridContainer_sp;
    @FXML private Pane pane;
    @FXML private TableView<OptimalRouteData> routeTable_tv;
    @FXML private TableColumn<OptimalRouteData, String> departureColumn_tc;
    @FXML private TableColumn<OptimalRouteData, String> arrivalColumn_tc;
    @FXML private TableColumn<OptimalRouteData, String> typeColumn_tc;
    @FXML private TableColumn<OptimalRouteData, String> priceColumn_tc;
    @FXML private Label totalTimePrice_lbl;
    @FXML private Button topFive_btn;
    @FXML private Button buyTicket_btn;

    private static int N = 6;
    private Route currentRoute;
    private List<Route> topFiveRoutes;
    private static Route optimalRoute;
    private String[][] countryMap;
    private Map<String, Pane> cities = new HashMap<>();
    private Map<String, List<Departure>> graph;
    private static final Path TICKET_DIR =
            Path.of(System.getProperty("user.dir")).toAbsolutePath().resolve("tickets");
    private static final double CELL = 44;

    /**
     * JavaFX lifecycle hook. Wires UI, loads data, builds grid,
     * and sets listeners that recompute and redraw routes.
     */
    @FXML private void initialize(){
        criteria_chb.setItems(FXCollections.observableArrayList("Shortest traveling time",
                "Lowest price",
                "Minimum number of transfers"));
        criteria_chb.getSelectionModel().selectFirst();
        try{
            TransportDataGenerator.TransportData data = readData("transport_data.json");
            countryMap = data.countryMap;
            graph = Finder.makeGraph(data);
            List<String> cities = new ArrayList<>();
            for(String[] row : countryMap){
                Collections.addAll(cities, row);
            }

            ObservableList<String> allCities = FXCollections.observableArrayList(cities);
            destinationCity_chb.setItems(allCities);
            departureCity_chb.setItems(allCities);

            destinationCity_chb.valueProperty().addListener((o, ov, nv) -> {
                if (Objects.equals(nv, departureCity_chb.getValue())) {
                    destinationCity_chb.setValue(null);
                }
            });
            departureCity_chb.valueProperty().addListener((o, ov, nv) -> {
                if (Objects.equals(nv, destinationCity_chb.getValue())) {
                    departureCity_chb.setValue(null);
                }
            });
            makeCityGrid();

            pane.prefWidthProperty().bind(countryGrid_gp.widthProperty());
            pane.prefHeightProperty().bind(countryGrid_gp.heightProperty());
            gridContainer_sp.widthProperty().addListener((o, ov, nv) -> redrawRoute());
            gridContainer_sp.heightProperty().addListener((o, ov, nv) -> redrawRoute());
            criteria_chb.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> recomputeRoute());
            departureCity_chb.valueProperty().addListener((o, ov, nv) -> recomputeRoute());
            destinationCity_chb.valueProperty().addListener((o, ov, nv) -> recomputeRoute());
            departureColumn_tc.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDeparture()));
            arrivalColumn_tc.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDestination()));
            priceColumn_tc.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPrice()));
            typeColumn_tc.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getType()));

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Recomputes the top-N routes based on current UI selections
     * and draws the optimal route.
     */
    private void recomputeRoute(){
        String departure = departureCity_chb.getValue();
        String destination = destinationCity_chb.getValue();
        String criteria = criteria_chb.getValue();

        if(departure == null || destination == null || criteria == null || departure.equals(destination)){
            clearRoute();
            return;
        }

        LocalTime now = LocalTime.now();
        int start = now.getHour()*60 + now.getMinute();

        try{
            List<Route> routes = switch (criteria_chb.getValue()){
                case "Shortest traveling time" -> Finder.nBestRoutes(graph, departure, destination, start, N, Finder.Criteria.FASTEST);
                case "Lowest price" -> Finder.nBestRoutes(graph, departure, destination, start, N, Finder.Criteria.CHEAPEST);
                case "Minimum number of transfers" -> Finder.nBestRoutes(graph, departure, destination, start, N,Finder.Criteria.MIN_TRANSFERS);
                default -> Finder.nBestRoutes(graph, departure, destination, start, N,Finder.Criteria.FASTEST);
            };

            clearRoute();
            if(routes.isEmpty()){
                totalTimePrice_lbl.setText("There is no found route from " + departure + " to " + destination + ".");
                return;
            }
            topFive_btn.setDisable(false);
            buyTicket_btn.setDisable(false);
            topFiveRoutes = new ArrayList<>(routes.subList(1, routes.size()));
            optimalRoute = routes.get(0);
            drawRoute(optimalRoute);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /** Clears any drawn route overlay and resets current route reference. */
    private void clearRoute() {
        pane.getChildren().clear();
        currentRoute = null;
    }

    /** Redraws the currently drawn route (used on resize). */
    private void redrawRoute() {
        if (currentRoute != null) {
            drawRoute(currentRoute);
        }
    }

    /** Ensures the overlay pane for lines sits on top of the grid. */
    private void ensureOverlay() {
        if (!gridContainer_sp.getChildren().contains(pane)) {
            gridContainer_sp.getChildren().add(pane);
        }
    }

    /** Computes the visual center of a grid cell in the pane’s coordinate space. */
    private Point2D centerPoint(Pane pane, Node departureCity){
        Bounds bound = departureCity.localToScene(departureCity.getBoundsInLocal());
        Bounds local = pane.sceneToLocal(bound);
        double cx = (local.getMinX() + local.getMaxX()) / 2.0;
        double cy = (local.getMinY() + local.getMaxY()) / 2.0;
        return new Point2D(cx, cy);
    }

    /**
     * Draws a route as colored line segments between city cells and
     * fills the details table. Bus = magenta, Train = teal.
     */
    private void drawRoute(Route route){
        ensureOverlay();
        pane.getChildren().clear();

        if(route == null || route.getDepartures().isEmpty()){
            return;
        }

        for (int i = 0; i < route.getDepartures().size(); i++) {
            String departure = route.getDepartures().get(i).getFrom();
            String destination = route.getDepartures().get(i).getTo();

            Node departureCity = cities.get(departure);
            Node destinationCity = cities.get(destination);
            if(destinationCity == null || departureCity == null){
                continue;
            }

            Point2D p1 = centerPoint(pane, departureCity);
            Point2D p2 = centerPoint(pane, destinationCity);
            Line l = new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            if("autobus".equals(route.getDepartures().get(i).getType())){
                l.setStroke(Color.web("#fc03f4"));
            }else{
                l.setStroke(Color.web("#03fcba"));
            }
            l.setStrokeWidth(3);
            l.setStrokeLineCap(StrokeLineCap.ROUND);

            pane.getChildren().add(l);
        }
        SimulationController.fillInTable(route, routeTable_tv, totalTimePrice_lbl);

    }

    /**
     * Populates the legs table with the given route and updates the total time/price label.
     *
     * @param route             the route to display
     * @param routeTable        table to fill
     * @param totalTimePricelbl optional label to show "Total: {time}, {price}"
     */
    public static void fillInTable(Route route, TableView<OptimalRouteData> routeTable, Label totalTimePricelbl){
        ObservableList<OptimalRouteData> rows = FXCollections.observableArrayList();
        for(Departure d: route.getDepartures()){
            String departure, destination, type, price;
            int timeFrom = d.getDepartureTime();
            departure = ((d.getType().equals("autobus")) ? "A_" : "Z_") +
                    d.getFrom().substring(2)+ SimulationController.transformMinutes(timeFrom);

            int timeTo = d.getDuration() + timeFrom;
            destination = d.getTo() + SimulationController.transformMinutes(timeTo);

            type = (d.getType().equals("autobus")) ? "Bus" : "Train";
            price = String.valueOf(d.getPrice());
            rows.add(new OptimalRouteData(departure, destination, price, type));
        }
        routeTable.setItems(rows);
        if(totalTimePricelbl != null){
            String totalPriceTime = "Total: " + route.getTransformedTime() + ", " + String.valueOf(route.getTotalPrice()) + " monetary units.";
            totalTimePricelbl.setText(totalPriceTime);
        }
    }

    /**
     * Reads generator output JSON into {@link TransportDataGenerator.TransportData}.
     *
     * @param path path to JSON file
     * @return parsed transport data
     * @throws RuntimeException if read/parse fails
     */
    private TransportDataGenerator.TransportData readData(String path)throws RuntimeException{
        try{
            //System.out.println("CWD = " + System.getProperty("user.dir"));
            ObjectMapper map = new ObjectMapper();
            return map.readValue(new File(path), TransportDataGenerator.TransportData.class);
        } catch (Exception e) {
            throw new RuntimeException("Not able to read from file " + path);
        }
    }

    /**
     * Formats minutes since midnight to a short label " (HH:MM)".
     */
    private static String transformMinutes(int time){
        String s = " (";
        s += (time/60 >= 10) ? String.valueOf(time/60) : ("0" + String.valueOf(time/60));
        s += ":";
        s += (time - ((time/60)*60) < 10) ? (String.valueOf("0" + (time - ((time/60)*60)))) : String.valueOf(time - ((time/60)*60));
        s += ")";
        return s;

    }

    /**
     * Builds the country grid (rows × cols) and places a labeled cell for each city,
     * keeping a map from city name → cell node for quick lookup while drawing routes.
     */
    private void makeCityGrid(){
        countryGrid_gp.getChildren().clear();
        countryGrid_gp.getColumnConstraints().clear();
        countryGrid_gp.getRowConstraints().clear();
        cities.clear();

        countryGrid_gp.setHgap(8);
        countryGrid_gp.setVgap(8);

        int rows = countryMap.length;
        int cols = countryMap[0].length;

        for (int i = 0; i < rows; i++) {
            RowConstraints r = new RowConstraints(CELL, CELL, CELL);
            r.setPercentHeight(100.0 / rows);
            r.setVgrow(Priority.ALWAYS);
            countryGrid_gp.getRowConstraints().add(r);
        }

        for (int i = 0; i < cols; i++) {
            ColumnConstraints r = new ColumnConstraints(CELL, CELL, CELL);
            r.setPercentWidth(100.0 / cols);
            r.setHgrow(Priority.ALWAYS);
            countryGrid_gp.getColumnConstraints().add(r);
        }

        double prefW = cols * CELL + (cols - 1) * countryGrid_gp.getHgap();
        double prefH = rows * CELL + (rows - 1) * countryGrid_gp.getVgap();
        countryGrid_gp.setPrefSize(prefW, prefH);
        countryGrid_gp.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        countryGrid_gp.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        pane.prefWidthProperty().bind(countryGrid_gp.widthProperty());
        pane.prefHeightProperty().bind(countryGrid_gp.heightProperty());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String city = countryMap[i][j];
                StackPane cityCell = new StackPane();
                cityCell.setPrefSize(CELL, CELL);
                cityCell.setMinSize(CELL, CELL);
                cityCell.setMaxSize(CELL, CELL);
                cityCell.setAlignment(Pos.CENTER);
                cityCell.setStyle("-fx-border-color: #e5e5e5; -fx-background-color: white;");

                Label label = new Label(city);
                label.setMaxSize(CELL - 6, CELL - 6);
                label.setWrapText(false);
                label.setAlignment(Pos.CENTER);
                label.setTextOverrun(OverrunStyle.ELLIPSIS);
                label.setStyle("-fx-font-size:10px; -fx-text-fill:#333;");
                //Tooltip.install(cityCell, new Tooltip(city));
                cityCell.getChildren().addAll(label);

                GridPane.setColumnIndex(cityCell, j);
                GridPane.setRowIndex(cityCell, i);
                countryGrid_gp.getChildren().addAll(cityCell);

                cities.put(city,cityCell);
            }
        }
    }

    /**
     * Returns to the main view and refreshes ticket stats.
     * Clears any cached instance of the main view before switching.
     */
    @FXML private void goBack_btn_onClick(){
        //SceneManager.removeScene("/jelena/etfbl/bustraintransport/main-view.fxml");
        SceneManager.switchScene(goBack_btn,
                "/jelena/etfbl/bustraintransport/main-view.fxml",
                TransportController::setTicketsData);
    }

    /**
     * Opens the {@code top-five-routes-view} view and initializes it with the precomputed routes.
     */
    @FXML private void topFive_btn_onClick(){
        try{
            if(topFiveRoutes != null){
                SceneManager.removeScene("/jelena/etfbl/bustraintransport/top-five-routes-view.fxml");
                SceneManager.switchScene(topFive_btn, "/jelena/etfbl/bustraintransport/top-five-routes-view.fxml",
                        (TopFiveRoutesController c) -> { c.initWith(topFiveRoutes);});
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves a simple text ticket for the provided route in the {@code tickets/} directory.
     * File name format: {@code ticket-YYYYMMDD-HHmmss.txt}.
     */
    public static void saveTicket(Route r) throws IOException {
        if (!Files.exists(TICKET_DIR)) {
            Files.createDirectories(TICKET_DIR);
        }
        String route = "Route: " + r.getDepartures().get(0).getFrom();
        for (Departure d : r.getDepartures()) {
            route += " -> " + d.getTo();
        }
        String time = "Time: "  + r.getDepartures().get(0).getDepartureTime();
        String price = "Price: " + r.getTotalPrice();
        String date = "Date: "  + DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDate.now());

        String base = "ticket-" + DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss").format(LocalDateTime.now());
        Path file = TICKET_DIR.resolve(base + ".txt");

        try (var pw = new PrintWriter(Files.newBufferedWriter(file, StandardCharsets.UTF_8))) {
            pw.println(route);
            pw.println(time);
            pw.println(price);
            pw.println(date);
        }
    }

    /**
     * Persists a ticket for the current optimal route.
     */
    @FXML private void buyTicket_btn_onClick(){
        try{
            saveTicket(optimalRoute);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
