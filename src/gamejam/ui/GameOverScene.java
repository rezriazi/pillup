package gamejam.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameOverScene {
    private static GameOverScene gameOverScene = null;
    private BorderPane root = new BorderPane();
    private Scene scene = new Scene(root);

    private GameOverScene() {
        HBox gameOverBox = new HBox();
        Label label = new Label("GAME OVER");
        VBox box = new VBox();
        Button reset = new Button ("reset");
        box.getChildren().addAll(label, reset);
        root.setCenter(box);
        // TODO
//        reset.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//
//            }
//        });
    }

    public static GameOverScene getInstance() {
        if (gameOverScene == null) {
            gameOverScene = new GameOverScene();
        }
        return gameOverScene;
    }

    public Scene getScene () {
        return this.scene;
    }

}
