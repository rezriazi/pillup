package gamejam.ui;

import gamejam.model.objects.Player;
import gamejam.model.utils.Arrow;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int WIDTH = 540;
    public static final int HEIGHT = 600;

    public static final int SCENE_WIDTH = 530;
    public static final int SCENE_HEIGHT = 590;

    public static final int CANVAS_WIDTH = SCENE_WIDTH;
    public static final int CANVAS_HEIGHT = SCENE_HEIGHT;

    private static Stage window;
    private Scene scene;
    private Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    private Pane canvasLayout;
    private GraphicsContext gc = canvas.getGraphicsContext2D();

    private Game game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        game = new Game(gc);
        setup();
        game.start();
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
        canvasLayout = new Pane();
        canvasLayout.getChildren().add(canvas);
    }

    private void setupScene() {
        scene = new Scene(canvasLayout);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode keyCode = event.getCode();
                Player player = game.getPlayer();

                switch (keyCode) {
                    case LEFT:
                        player.move(Arrow.LEFT);
                        System.out.println("LEFT");
                        break;
                    case RIGHT:
                        player.move(Arrow.RIGHT);
                        System.out.println("RIGHT");
                        break;
                    case UP:
                        player.jump(Arrow.UP);
                        System.out.println("UP");
                        break;
                    case SPACE:
                        player.jump(Arrow.SPACE);
                        System.out.println("SPACE");
                        break;
                    default:
                        break;
                }
            }
            });

        window.setScene(scene);
    }

    public static void setScene(Scene scene) {
        this.window.setScene(scene);
    }
}