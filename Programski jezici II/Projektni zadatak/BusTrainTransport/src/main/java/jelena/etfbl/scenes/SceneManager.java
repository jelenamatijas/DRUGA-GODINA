package jelena.etfbl.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@code SceneManager} is a utility class for switching between JavaFX scenes.
 * <p>
 * It supports caching of already-loaded FXML files and their controllers so that
 * returning to a previously opened scene preserves its state. When switching to
 * {@code main-view.fxml}, the entire cache is cleared to ensure a fresh start.
 * </p>
 *
 * @author Jelena
 */
public class SceneManager {
    /**
     * Internal wrapper class that holds both the root {@link Parent} node
     * and its associated controller.
     *
     * @param <C> the type of the controller
     */
    private static class View<C>{
        final Parent root;
        final C controller;

        /**
         * Creates a new {@code View} with the given root and controller.
         *
         * @param root       the root node of the scene
         * @param controller the controller instance associated with the FXML
         */
        public View(Parent root, C controller){
            this.root = root;
            this.controller = controller;
        }
    }

    /** Cache of loaded FXML scenes keyed by their path. */
    private static final Map<String, View<?>> CACHE = new HashMap<>();

    /**
     * Switches the scene of the given node's window to the FXML file specified by {@code fxmlPath}.
     * <p>
     * Equivalent to calling {@link #switchScene(Node, String, Consumer)} with {@code null} as the consumer.
     * </p>
     *
     * @param anyNode  any node from the current scene
     * @param fxmlPath the path to the FXML file
     */
    public static void switchScene(Node anyNode, String fxmlPath) {
        switchScene(anyNode, fxmlPath, null);
    }

    /**
     * Switches the scene of the given node's window to the FXML file specified by {@code fxmlPath}.
     * <p>
     * Scenes are cached so that returning to a scene restores its state.
     * If the FXML path ends with {@code main-view.fxml}, the entire cache is cleared.
     * </p>
     *
     * @param anyNode   any node from the current scene
     * @param fxmlPath  the path to the FXML file
     * @param afterLoad an optional consumer called with the controller after the scene is loaded or reused
     * @param <C>       the type of the controller
     * @return the controller associated with the FXML file
     * @throws RuntimeException if the FXML file cannot be loaded
     */
    public static <C> C switchScene(Node anyNode, String fxmlPath, Consumer<C> afterLoad) {
        try {
            View<C> view = (View<C>) CACHE.get(fxmlPath);
            if(fxmlPath.endsWith("main-view.fxml")){
                CACHE.clear();
            }
            if(view == null){
                FXMLLoader loader = new FXMLLoader(
                        Objects.requireNonNull(SceneManager.class.getResource(fxmlPath),
                                "FXML not found: " + fxmlPath)
                );
                Parent root = loader.load();
                @SuppressWarnings("unchecked")
                C controller = (C) loader.getController();
                view = new View<>(root, controller);
                CACHE.put(fxmlPath, view);
            }

            Scene scene = Objects.requireNonNull(anyNode.getScene(), "Given node not in a scene.");
            scene.setRoot(view.root);
            Stage stage =(Stage) scene.getWindow();

            if(stage != null){
                stage.sizeToScene();
            }

            if (afterLoad != null) {
                afterLoad.accept(view.controller);
            }
            return view.controller;
        } catch (IOException e) {
            throw new RuntimeException("Not able to load " + fxmlPath, e);
        }
    }

    /**
     * Removes a specific FXML scene from the cache, forcing a fresh load next time it is requested.
     *
     * @param fxmlPath the path of the FXML scene to remove
     */
    public static void removeScene(String fxmlPath){
        CACHE.remove(fxmlPath);
    }

}
