package gamejam.ui;

import gamejam.model.interfaces.Drawable;
import gamejam.model.utils.Background;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EscapeMenu implements Drawable {

    private static final String ESCAPE_MENU_BACKGROUND_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/background_day.png";

    private static final String RESUME_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/background_day.png";
    private static final String RESTART_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/background_day.png";
    private static final String QUIT_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/background_day.png";

    private static final double BUTTON_WIDTH = 100;
    private static final double BUTTON_HEIGHT = 50;

    private Background background;

    public EscapeMenu() throws FileNotFoundException {
        this.background = new Background(new FileInputStream(ESCAPE_MENU_BACKGROUND_PATH));
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
        gc.drawImage(new Image(new FileInputStream(RESUME_BUTTON_PATH)),
                Main.CANVAS_WIDTH - (BUTTON_WIDTH / 2),
                Main.CANVAS_HEIGHT / 3);
    }

    private <T> void drawRestartButton(T ... obj) throws FileNotFoundException {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(new Image(new FileInputStream(RESUME_BUTTON_PATH)),
                Main.CANVAS_WIDTH - (BUTTON_WIDTH / 2),
                Main.CANVAS_HEIGHT / 3 + BUTTON_HEIGHT + 10);
    }

    private <T> void drawQuitButton(T ... obj) throws FileNotFoundException {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(new Image(new FileInputStream(QUIT_BUTTON_PATH)),
                Main.CANVAS_WIDTH - (BUTTON_WIDTH / 2),
                Main.CANVAS_HEIGHT / 3 + 2 * BUTTON_HEIGHT + 10);
    }
}
