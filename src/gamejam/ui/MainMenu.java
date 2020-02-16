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

    private static final double PLAY_BUTTON_X = Main.CANVAS_WIDTH / 2;
    private static final double PLAY_BUTTON_Y = 2 * Main.CANVAS_HEIGHT / 3;
    private static final double PLAY_BUTTON_RADIUS = 50;

    private static final double SETTINGS_BUTTON_X = PLAY_BUTTON_X;
    private static final double SETTINGS_BUTTON_RADIUS = 50;
    private static final double SETTINGS_BUTTON_Y = PLAY_BUTTON_Y + PLAY_BUTTON_RADIUS + 10 + SETTINGS_BUTTON_RADIUS;


    private Background background;
    private Canvas canvas;

    private Image playButtonImage;
    private Runnable playRunnable;
    // TODO:
    private Runnable settingsRunnable;

    private Image settingsButtonImage;

    public MainMenu(Canvas canvas,Runnable playRunnable) throws FileNotFoundException {
        this.playRunnable = playRunnable;
        this.background = new Background(new FileInputStream(MAIN_MENU_BACKGROUND_PATH));
        this.canvas = canvas;
        this.playButtonImage = new Image(new FileInputStream(PLAY_BUTTON_PATH));
        this.settingsButtonImage = new Image(new FileInputStream(SETTINGS_BUTTON_PATH));
        setupCanvas();
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
        gc.drawImage(playButtonImage, PLAY_BUTTON_X, PLAY_BUTTON_Y);
    }

    private <T> void drawSettingsButton(T ... obj) throws FileNotFoundException {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(settingsButtonImage, SETTINGS_BUTTON_X, SETTINGS_BUTTON_Y);
    }

    public void setupCanvas() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double mouseX = event.getSceneX();
                double mouseY = event.getSceneY();

                if (isOnPlayButton(mouseX, mouseY)) {
                    // TODO: Play button is clicked
                    System.out.println("play clicked");
                    playRunnable.run();
                } else if (isOnSettingsButton(mouseX, mouseY)) {
                    // TODO: Settings button is clicked
                }
            }
        });
    }

    private boolean isOnPlayButton(double mX, double mY) {
        return (mX >= PLAY_BUTTON_X - PLAY_BUTTON_RADIUS
                && mX <= PLAY_BUTTON_X + PLAY_BUTTON_RADIUS)
                    && (mY >= PLAY_BUTTON_Y - PLAY_BUTTON_RADIUS
                        && mY <= PLAY_BUTTON_Y + PLAY_BUTTON_RADIUS);
    }

    private boolean isOnSettingsButton(double mX, double mY) {
        return (mX >= SETTINGS_BUTTON_X - SETTINGS_BUTTON_RADIUS
                && mX <= SETTINGS_BUTTON_X + SETTINGS_BUTTON_RADIUS)
                    && (mY >= SETTINGS_BUTTON_Y - SETTINGS_BUTTON_RADIUS
                        && mY <= SETTINGS_BUTTON_Y + SETTINGS_BUTTON_RADIUS);
    }
}