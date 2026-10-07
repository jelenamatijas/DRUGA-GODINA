package jelena.etfbl.simulation;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import jelena.etfbl.scenes.SceneManager;

import java.io.IOException;
import java.util.List;

/**
 * JavaFX controller for the "Top Five Routes" view.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Display up to five alternative routes computed by {@link Finder},</li>
 *   <li>Allow the user to select one of the routes,</li>
 *   <li>Fill a table with detailed leg information for the chosen route,</li>
 *   <li>Provide a button to buy (save) a ticket for the selected route,</li>
 *   <li>Allow returning to the simulation view.</li>
 * </ul>
 *
 * @author Jelena
 */
public class TopFiveRoutesController {
    private List<Route> topFiveRoutes;
    private static int ROUTE_INDEX;

    @FXML private Button goBack_btn;
    @FXML private Button route1_btn;
    @FXML private Button route2_btn;
    @FXML private Button route3_btn;
    @FXML private Button route4_btn;
    @FXML private Button route5_btn;
    @FXML private TableView<OptimalRouteData> routes_tb;
    @FXML private TableColumn<OptimalRouteData, String> departure_clm;
    @FXML private TableColumn<OptimalRouteData, String> arrival_clm;
    @FXML private TableColumn<OptimalRouteData, String> type_clm;
    @FXML private TableColumn<OptimalRouteData, String> price_clm;
    @FXML private Label transferPriceTime_lbl;
    @FXML private Button byeTicket_btn;

    /**
     * Initializes the controller with a list of up to five routes.
     * Also makes only as many route buttons visible as there are routes.
     *
     * @param topFiveRoutes list of top alternative routes
     */
    public void initWith(List<Route> topFiveRoutes){
        this.topFiveRoutes = topFiveRoutes;
        List<Button> buttons = List.of(route1_btn, route2_btn, route3_btn, route4_btn, route5_btn);
        for (int i = 0; i < topFiveRoutes.size(); i++) {
            buttons.get(i).setVisible(true);
        }

    }

    /**
     * JavaFX lifecycle hook. Initializes table column mappings.
     */
    @FXML private void initialize(){
        departure_clm.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDeparture()));
        arrival_clm.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getDestination()));
        type_clm.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getPrice()));
        price_clm.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getType()));

    }

    /** Displays details of route #1. */
    @FXML private void route1_btn_OnClick(){
        SimulationController.fillInTable(topFiveRoutes.get(0), routes_tb, transferPriceTime_lbl);
        ROUTE_INDEX = 0;
    }

    /** Displays details of route #2. */
    @FXML private void route2_btn_OnClick(){
        SimulationController.fillInTable(topFiveRoutes.get(1), routes_tb, transferPriceTime_lbl);
        ROUTE_INDEX = 1;
    }

    /** Displays details of route #3. */
    @FXML private void route3_btn_OnClick(){
        SimulationController.fillInTable(topFiveRoutes.get(2), routes_tb, transferPriceTime_lbl);
        ROUTE_INDEX = 2;
    }

    /** Displays details of route #4. */
    @FXML private void route4_btn_OnClick(){
        SimulationController.fillInTable(topFiveRoutes.get(3), routes_tb, transferPriceTime_lbl);
        ROUTE_INDEX = 3;
    }

    /** Displays details of route #5. */
    @FXML private void route5_btn_OnClick(){
        SimulationController.fillInTable(topFiveRoutes.get(4), routes_tb, transferPriceTime_lbl);
        ROUTE_INDEX = 4;
    }

    /**
     * Returns to the simulation view.
     */
    @FXML private void goBack_btn_OnClick(){
        SceneManager.switchScene(goBack_btn,
                "/jelena/etfbl/bustraintransport/simulation-view.fxml");
    }

    /**
     * Saves a ticket for the currently selected top route.
     */
    @FXML private void byeTicket_btn_OnClick(){
        try{
            SimulationController.saveTicket(topFiveRoutes.get(ROUTE_INDEX));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
