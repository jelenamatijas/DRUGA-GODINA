package jelena.etfbl.bustraintransport;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jelena.etfbl.generate.*;
import jelena.etfbl.scenes.SceneManager;
import jelena.etfbl.ticket.Ticket;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * {@code TransportController} is the JavaFX controller class responsible for handling
 * user interactions in the main transport view and for managing ticket-related data.
 * <p>
 * It provides functionality for:
 * <ul>
 *     <li>Reading user input (number od rows, columns and departures) and triggering the
 *         {@link TransportDataGenerator} to create the transport data JSON file,</li>
 *     <li>Switching from the main view to the simulation view,</li>
 *     <li>Loading existing {@link Ticket} data from files and updating total revenue
 *         and the number of sold tickets in the UI.</li>
 * </ul>
 *
 * @author Jelena
 */

public class TransportController {
    /** Path to the directory where tickets are stored. */
    private static final Path TICKET_DIR = Path.of(System.getProperty("user.dir")).toAbsolutePath().resolve("tickets");

    @FXML private TextField rowNumber_tf;
    @FXML private TextField columnNumber_tf;
    @FXML private TextField departuresNumber_tf;
    @FXML private Button enterDimension_btn;
    @FXML private Label invalidValues_lbl;
    @FXML private TextField numberSoldTicket_tf;
    @FXML private TextField totalSalesRevenue_tf;

    /**
     * Event handler for the {@code enterDimension_btn} button.
     * <p>
     * This method:
     * <ul>
     *     <li>Retrieves values from input text fields (number of rows, columns and departures),</li>
     *     <li>Calls {@link TransportDataGenerator#main(String[])} to generate the
     *         {@code transport_data.json} file,</li>
     *     <li>Switches the scene to the simulation view using {@link SceneManager},</li>
     *     <li>If invalid values are entered, displays a validation error label.</li>
     * </ul>
     */
    @FXML protected void onClick_enterDimension_btn(){
        try{
            String row = rowNumber_tf.getText();
            String cols = columnNumber_tf.getText();
            String departures = departuresNumber_tf.getText();
            TransportDataGenerator.main(new String[]{row, cols, departures});
            SceneManager.switchScene(enterDimension_btn,
                    "/jelena/etfbl/bustraintransport/simulation-view.fxml");
        }catch (NumberFormatException e){
            invalidValues_lbl.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads ticket data from files located in {@link #TICKET_DIR}, calculates
     * the total sales revenue and the number of sold tickets, and updates the
     * respective UI text fields.
     */
    public void setTicketsData(){
        int totalPrice = 0;
        int numberOfTickets = 0;
        try{
            if(Files.exists(TICKET_DIR) && Files.isDirectory(TICKET_DIR)){
                File []files = new File(TICKET_DIR.toString()).listFiles();
                for(File f : files){
                    Path filePath = TICKET_DIR.resolve(f.getName());
                    Ticket ticket = readTicket(filePath);
                    if(ticket != null){
                        totalPrice += Integer.parseInt(ticket.getValue("price"));
                        numberOfTickets++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            numberSoldTicket_tf.setText(String.valueOf(numberOfTickets));
            totalSalesRevenue_tf.setText(String.valueOf(totalPrice));
        }
    }

    /**
     * Reads a ticket from the given file path and constructs a {@link Ticket} object.
     * <p>
     * The expected ticket file format is plain text with lines starting with:
     * <ul>
     *     <li>{@code Route:}</li>
     *     <li>{@code Time:}</li>
     *     <li>{@code Price:}</li>
     *     <li>{@code Date:}</li>
     * </ul>
     * Missing fields will cause an {@link IOException}.
     * </p>
     *
     * @param path the path to the ticket file
     * @return a {@link Ticket} object created from the file content
     * @throws IOException if the file cannot be read or is missing required fields
     */
    private Ticket readTicket(Path path)throws IOException{
        try(BufferedReader in = new BufferedReader(Files.newBufferedReader(path))) {
            String route = null, time = null, price = null, date = null;
            String line;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                if (line.startsWith("Route:")) route = line.substring("Route:".length()).trim();
                else if (line.startsWith("Time:")) time = line.substring("Time:".length()).trim();
                else if (line.startsWith("Price:")) price = line.substring("Price:".length()).trim();
                else if (line.startsWith("Date:")) date = line.substring("Date:".length()).trim();
            }
            if (route == null || time == null || price == null || date == null) {
                throw new IOException("Ticket file missing fields: " + path);
            }
            return new Ticket(route, time, price, date);
        }
    }

}
