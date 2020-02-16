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

public class MainMenu implements Drawable {

    private static final String MAIN_MENU_BACKGROUND_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/mainscreen.png";
    // TODO: play_button & settings_button
    private static final String PLAY_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/background_day.png";
    private static final String SETTINGS_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/background_day.png";

    private static final double PLAY_BUTTON_RADIUS = 50;
    private static final double SETTINGS_BUTTON_RADIUS = 25;

    private Background background;
    private Canvas canvas;

    public MainMenu(Canvas canvas) throws FileNotFoundException {
        this.background = new Background(new FileInputStream(MAIN_MENU_BACKGROUND_PATH));
        this.canvas = canvas;
    }

    @Override
    public <T> void draw(T ... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];

        drawBackground(gc);
        try {
            drawPlayButton(gc);
            drawSettingsButton(gc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private <T> void drawBackground(T ... obj) {
        background.draw(obj);
    }

    private <T> void drawPlayButton(T ... obj) throws FileNotFoundException {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(new Image(new FileInputStream(PLAY_BUTTON_PATH)),
                Main.CANVAS_WIDTH / 2 - PLAY_BUTTON_RADIUS,
                Main.CANVAS_HEIGHT / 2 - PLAY_BUTTON_RADIUS);
    }

    private <T> void drawSettingsButton(T ... obj) throws FileNotFoundException {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(new Image(new FileInputStream(SETTINGS_BUTTON_PATH)),
                0 + SETTINGS_BUTTON_RADIUS,
                0 + SETTINGS_BUTTON_RADIUS);
    }

    public void setupCanvas() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double mouseX = event.getSceneX();
                double mouseY = event.getSceneY();

                if (isOnPlayButton(mouseX, mouseY)) {
                    // Play button is clicked
                } else if (isOnSettingsButton(mouseX, mouseY)) {
                    // Settings button is clicked
                }
            }
        });
    }

    private boolean isOnPlayButton(double mX, double mY) {
        return false;
    }

    private boolean isOnSettingsButton(double mX, double mY) {
        return false;
    }
}
