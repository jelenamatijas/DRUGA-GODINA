package jelena.etfbl.bustraintransport;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * {@code TransportApp} represents the main JavaFX application class
 * for the Bus and Train transport system.
 * <p>
 * This class is responsible for initializing and displaying the primary stage:
 * it loads the {@code main-view.fxml}, sets the window title and application icon,
 * and initializes the {@link TransportController} by populating ticket data once
 * the stage is shown.
 *
 * @author Jelena
 */

public class TransportApp extends Application {

    /**
     * Starts the JavaFX application.
     * <p>
     * This method:
     * <ul>
     *     <li>Loads the {@code main-view.fxml} file to create the scene graph,</li>
     *     <li>Sets the application title and window icon,</li>
     *     <li>Assigns the scene to the stage,</li>
     *     <li>Obtains the {@link TransportController} and triggers ticket data
     *         initialization once the stage is shown,</li>
     *     <li>Displays the stage.</li>
     * </ul>
     *
     * @param stage the primary {@link Stage} for this application, onto which
     *              the application scene is set
     * @throws IOException if the {@code main-view.fxml} resource cannot be loaded
     */

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TransportApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bus&Train transport");
        URL iconURL = TransportApp.class.getResource("/jelena/etfbl/bustraintransport/public-transport.png");
        Image icon = new Image(Objects.requireNonNull(iconURL).toExternalForm());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        TransportController transportController = fxmlLoader.getController();
        stage.setOnShown(e->transportController.setTicketsData());
        stage.show();
    }


}
