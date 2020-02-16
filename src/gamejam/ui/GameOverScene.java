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
import javafx.stage.Stage;

public class GameOverScene {
    private static GameOverScene gameOverScene = null;
    private BorderPane root;
    private Scene scene;
    public static Stage stage;

    private GameOverScene() {
        root = new BorderPane();
        scene = new Scene(root);
        HBox gameOverBox = new HBox();
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

    public static GameOverScene getInstance(Stage mainStage) {
        if (gameOverScene == null) {
            gameOverScene = new GameOverScene();
        }
        stage = mainStage;
        return gameOverScene;
    }

    public Scene getScene () {
        return this.scene;
    }

}
