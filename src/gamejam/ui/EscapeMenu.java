package gamejam.ui;

import gamejam.model.interfaces.Drawable;
import gamejam.model.utils.Background;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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

    private static final double RESUME_BUTTON_X = Main.CANVAS_WIDTH / 2 - (BUTTON_WIDTH / 2);
    private static final double RESUME_BUTTON_Y = Main.CANVAS_HEIGHT / 3;

    private static final double RESTART_BUTTON_X = Main.CANVAS_WIDTH / 2 - (BUTTON_WIDTH / 2);
    private static final double RESTART_BUTTON_Y = Main.CANVAS_HEIGHT / 3 + BUTTON_HEIGHT + 50;

    private static final double QUIT_BUTTON_X = Main.CANVAS_WIDTH / 2 - (BUTTON_WIDTH / 2);
    private static final double QUIT_BUTTON_Y = Main.CANVAS_HEIGHT / 3 + 2 * BUTTON_HEIGHT + 50;

    private Background background;

    private Runnable resumeRunnable;
    private Runnable restartRunnable;
    private Runnable quitRunnable;

    private Image resumeButtonImage;
    private Image restartButtonImage;
    private Image quitButtonImage;

    public EscapeMenu(Runnable resumeRunnable,
                      Runnable restartRunnable,
                      Runnable quitRunnable) throws FileNotFoundException {
        this.resumeRunnable = resumeRunnable;
        this.restartRunnable = restartRunnable;
        this.quitRunnable = quitRunnable;
        this.background = new Background(new FileInputStream(ESCAPE_MENU_BACKGROUND_PATH));
        this.resumeButtonImage = new Image(new FileInputStream(RESUME_BUTTON_PATH));
        this.restartButtonImage = new Image(new FileInputStream(RESTART_BUTTON_PATH));
        this.quitButtonImage = new Image(new FileInputStream(QUIT_BUTTON_PATH));
    }

    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];

        drawBackground(gc);
        drawResumeButton(gc);
        drawRestartButton(gc);
        drawQuitButton(gc);

    }

    private <T> void drawBackground(T ... obj) {
        background.draw(obj);
    }

    private <T> void drawResumeButton(T ... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
//        gc.drawImage(restartButtonImage, RESUME_BUTTON_X, RESUME_BUTTON_Y);
        gc.fillRect(RESTART_BUTTON_X, RESTART_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private <T> void drawRestartButton(T ... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
//        gc.drawImage(restartButtonImage, RESTART_BUTTON_X, RESTART_BUTTON_Y);
        gc.fillRect(RESTART_BUTTON_X, RESTART_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private <T> void drawQuitButton(T ... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
//        gc.drawImage(quitButtonImage, QUIT_BUTTON_X, QUIT_BUTTON_Y);
        gc.fillRect(QUIT_BUTTON_X, QUIT_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }


    public void onClick(double mouseX,double mouseY){
        if (isOnResumeButton(mouseX, mouseY)) {
                    // TODO: Resume button is clicked
                    resumeRunnable.run();
                } else if (isOnRestartButton(mouseX, mouseY)) {
                    // TODO: Restart button is clicked
                    restartRunnable.run();
                } else if (isOnQuitButton(mouseX, mouseY)) {
                    // TODO: Quit button is clicked
                    quitRunnable.run();
                }
    }

//    public void setupCanvas() {
//        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                double mouseX = event.getSceneX();
//                double mouseY = event.getSceneY();
//
//                if (isOnResumeButton(mouseX, mouseY)) {
//                    // TODO: Resume button is clicked
//                    resumeRunnable.run();
//                } else if (isOnRestartButton(mouseX, mouseY)) {
//                    // TODO: Restart button is clicked
//                    restartRunnable.run();
//                } else if (isOnQuitButton(mouseX, mouseY)) {
//                    // TODO: Quit button is clicked
//                    quitRunnable.run();
//                }
//            }
//        });
//    }


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
