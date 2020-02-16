package gamejam.ui;

import gamejam.model.interfaces.Drawable;
import gamejam.model.utils.Background;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EscapeMenu implements Drawable {

    // TODO: PATH AND IMG
    private static final String ESCAPE_MENU_BACKGROUND_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/background_day.png";

    // TODO: PATHS
    private static final String RESUME_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/background_day.png";
    private static final String RESTART_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/background_day.png";
    private static final String QUIT_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/background_day.png";

    private static final double BUTTON_WIDTH = 100;
    private static final double BUTTON_HEIGHT = 50;

    private static final double RESUME_BUTTON_X = Main.CANVAS_WIDTH - (BUTTON_WIDTH / 2);
    private static final double RESUME_BUTTON_Y = Main.CANVAS_HEIGHT / 3;

    private static final double RESTART_BUTTON_X = Main.CANVAS_WIDTH - (BUTTON_WIDTH / 2);
    private static final double RESTART_BUTTON_Y = Main.CANVAS_HEIGHT / 3 + BUTTON_HEIGHT + 10;

    private static final double QUIT_BUTTON_X = Main.CANVAS_WIDTH - (BUTTON_WIDTH / 2);
    private static final double QUIT_BUTTON_Y = Main.CANVAS_HEIGHT / 3 + 2 * BUTTON_HEIGHT + 10;

    private Background background;
    private Canvas canvas;

    private Image resumeButtonImage;
    private Image restartButtonImage;
    private Image quitButtonImage;

    public EscapeMenu(Canvas canvas) throws FileNotFoundException {
        this.background = new Background(new FileInputStream(ESCAPE_MENU_BACKGROUND_PATH));
        this.canvas = canvas;
        this.resumeButtonImage = new Image(new FileInputStream(RESUME_BUTTON_PATH));
        this.restartButtonImage = new Image(new FileInputStream(RESTART_BUTTON_PATH));
        this.quitButtonImage = new Image(new FileInputStream(QUIT_BUTTON_PATH));
        setupCanvas();
    }

    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];

        drawBackground();
        try {
            drawResumeButton(gc);
            drawRestartButton(gc);
            drawQuitButton(gc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private <T> void drawBackground(T ... obj) {
        background.draw(obj);
    }

    private <T> void drawResumeButton(T ... obj) throws FileNotFoundException {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(restartButtonImage, RESUME_BUTTON_X, RESUME_BUTTON_Y);
    }

    private <T> void drawRestartButton(T ... obj) throws FileNotFoundException {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(restartButtonImage, RESTART_BUTTON_X, RESTART_BUTTON_Y);
    }

    private <T> void drawQuitButton(T ... obj) throws FileNotFoundException {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(quitButtonImage, QUIT_BUTTON_X, QUIT_BUTTON_Y);
    }

    public void setupCanvas() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double mouseX = event.getSceneX();
                double mouseY = event.getSceneY();

                if (isOnResumeButton(mouseX, mouseY)) {
                    // TODO: Resume button is clicked
                } else if (isOnRestartButton(mouseX, mouseY)) {
                    // TODO: Restart button is clicked
                } else if (isOnQuitButton(mouseX, mouseY)) {
                    // TODO: Quit button is clicked
                }
            }
        });
    }

    private boolean isOnResumeButton(double mX, double mY) {
        return (mX >= RESUME_BUTTON_X && mX <= RESUME_BUTTON_X + BUTTON_WIDTH)
                && (mY >= RESUME_BUTTON_Y && mY <= RESUME_BUTTON_Y + BUTTON_HEIGHT);
    }

    private boolean isOnRestartButton(double mX, double mY) {
        return (mX >= RESTART_BUTTON_X && mX <= RESTART_BUTTON_X + BUTTON_WIDTH)
                && (mY >= RESTART_BUTTON_Y && mY <= RESTART_BUTTON_Y + BUTTON_HEIGHT);
    }

    private boolean isOnQuitButton(double mX, double mY) {
        return (mX >= QUIT_BUTTON_X && mX <= QUIT_BUTTON_X + BUTTON_WIDTH)
                && (mY >= QUIT_BUTTON_Y && mY <= QUIT_BUTTON_Y + BUTTON_HEIGHT);
    }
}
