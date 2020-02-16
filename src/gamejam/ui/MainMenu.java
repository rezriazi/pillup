package gamejam.ui;

import gamejam.model.interfaces.Drawable;
import gamejam.model.utils.Background;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainMenu implements Drawable {

    private static final String MAIN_MENU_BACKGROUND_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/mainscreen2.jpg";
    private static final String PLAY_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/PLAYBUTTON.png";
    private static final String MUSIC_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/note.png";

    private static final String MUTE_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/note_not.png";

    private static final double PLAY_BUTTON_X = Main.CANVAS_WIDTH / 2 - 50;
    private static final double PLAY_BUTTON_Y = 2 * Main.CANVAS_HEIGHT / 3 + 50;
    private static final double PLAY_BUTTON_RADIUS = 30;

    private static final double SETTINGS_BUTTON_X = 18;
    private static final double SETTINGS_BUTTON_Y = Main.CANVAS_HEIGHT - 103;
    private static final double SETTINGS_BUTTON_RADIUS = 50;

    private Background background;

    private Image playButtonImage;
    private Runnable playRunnable;
    private Runnable settingsRunnable;
    private MediaPlayer mediaPlayer;

    private Image settingsButtonImage;
    private Image muteButtonImage;

    public MainMenu(Runnable playRunnable, MediaPlayer mediaPlayer) throws FileNotFoundException {
        this.playRunnable = playRunnable;
        this.background = new Background(new FileInputStream(MAIN_MENU_BACKGROUND_PATH));
        this.playButtonImage =
                new Image(new FileInputStream(PLAY_BUTTON_PATH),
                        100, 50,
                        false, false);
        this.settingsButtonImage =
                new Image(new FileInputStream(MUSIC_BUTTON_PATH),
                        50, 50,
                        false, false);

        this.muteButtonImage =
                new Image(new FileInputStream(MUTE_BUTTON_PATH),
                        50, 50,
                        false, false);

        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public <T> void draw(T ... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        drawBackground(gc);
        drawSettingsButton(gc);drawPlayButton(gc);
    }

    private <T> void drawBackground(T ... obj) {
        background.draw(obj);
    }

    private <T> void drawPlayButton(T ... obj){
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(playButtonImage, PLAY_BUTTON_X, PLAY_BUTTON_Y);
    }

    private <T> void drawSettingsButton(T ... obj){
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(settingsButtonImage, SETTINGS_BUTTON_X, SETTINGS_BUTTON_Y);
    }

    public void onClick(double mouseX,double mouseY, GraphicsContext gc){
        if (isOnPlayButton(mouseX, mouseY)) {
            playRunnable.run();
        } else if (isOnSettingsButton(mouseX, mouseY)) {
            // TODO: Settings button is clicked
            if (this.mediaPlayer.isMute()) {
                this.mediaPlayer.setMute(false);
                gc.drawImage(settingsButtonImage, SETTINGS_BUTTON_X, SETTINGS_BUTTON_Y);
            } else{
                this.mediaPlayer.setMute(true);
                gc.drawImage(muteButtonImage, SETTINGS_BUTTON_X, SETTINGS_BUTTON_Y);
            }
        }
    }

    private boolean isOnPlayButton(double mX, double mY) {
        return (mX >= PLAY_BUTTON_X && mX <= PLAY_BUTTON_X + playButtonImage.getWidth())
                    && (mY >= PLAY_BUTTON_Y && mY <= PLAY_BUTTON_Y + playButtonImage.getHeight());
    }

    private boolean isOnSettingsButton(double mX, double mY) {
        return (mX >= SETTINGS_BUTTON_X && mX <= SETTINGS_BUTTON_X + settingsButtonImage.getWidth())
                    && (mY >= SETTINGS_BUTTON_Y && mY <= SETTINGS_BUTTON_Y + settingsButtonImage.getHeight());
    }
}