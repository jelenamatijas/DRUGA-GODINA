package jelena.etfbl.bustraintransport;

import javafx.application.Application;

/**
 * {@code Launcher} class is the entry point of the JavaFX application.
 * <p>
 * It contains the {@link #main(String[])} method which starts the app
 * by delegating to {@link Application#launch(Class, String...)}
 * with {@code TransportApp.class}.
 *
 * @author Jelena
 */

public class Launcher {
    /**
     * The main method of the application.
     * It launches the JavaFX runtime and starts the {@code TransportApp}.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.launch(TransportApp.class, args);
    }
}
