package gamejam.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WIDTH = 540;
    private static final int HEIGHT = 600;

    private static final int SCENE_WIDTH = 530;
    private static final int SCENE_HEIGHT = 590;

    private static final int CANVAS_WIDTH = SCENE_WIDTH;
    private static final int CANVAS_HEIGHT = SCENE_HEIGHT;

    private Stage window;
    private Scene scene;
    private Canvas canvas;
    private Pane canvasLayout;
    private GraphicsContext gc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        setup();
        new Game(gc).start();
    }

    private void setup() {
        setupWindow();
        setupCanvas();
        setupScene();

        window.show();
    }

    private void setupWindow() {
        window.setWidth(WIDTH);
        window.setHeight(HEIGHT);
        window.setResizable(false);
        window.setX(200);
        window.setY(200);
    }

    private void setupCanvas() {
        canvas = new Canvas(WIDTH, HEIGHT);

        canvasLayout = new Pane();
        canvasLayout.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();
    }

    private void setupScene() {
        scene = new Scene(canvasLayout);
        window.setScene(scene);
    }
}