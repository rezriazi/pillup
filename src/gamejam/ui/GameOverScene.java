package gamejam.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class GameOverScene {
    private static GameOverScene gameOverScene = null;
    private BorderPane root;
    private Scene scene;

    private GameOverScene() {
        root = new BorderPane();
        scene = new Scene(root);
        HBox gameOverBox = new HBox();
        root.getChildren().add(gameOverBox);
        TextField field = new TextField();
        HBox.setHgrow(field, Priority.ALWAYS);
        gameOverBox.getChildren().addAll(new Label("Game Over"), field);
        root.setCenter(gameOverBox);
        Button reset = new Button ("reset");
        root.setBottom(reset);

        // TODO
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

            }
        });
    }

    public static GameOverScene getInstance() {
        if (gameOverScene == null) {
            gameOverScene = new GameOverScene();
        }

        return gameOverScene;
    }

}
