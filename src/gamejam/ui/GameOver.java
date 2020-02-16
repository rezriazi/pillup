package gamejam.ui;

import com.sun.org.apache.regexp.internal.RE;
import gamejam.model.interfaces.Drawable;
import gamejam.model.utils.Background;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameOver implements Drawable {

    private Background background;
    private Runnable restartRunnable;
    private Runnable quitRunnable;

    private static final String GAMEOVER_BACKGROUND_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/gameover.png";

    private static final double RESTART_BUTTON_X = Main.CANVAS_WIDTH / 2;
    private static final double RESTART_BUTTON_Y = 2 * Main.CANVAS_HEIGHT / 3;

    private static final double QUIT_BUTTON_X = RESTART_BUTTON_X;
    private static final double QUIT_BUTTON_Y = RESTART_BUTTON_Y + 20;

    private static final double RESTART_BUTTON_W = 50;
    private static final double RESTART_BUTTON_H = 20;

    private static final double QUIT_BUTTON_W = RESTART_BUTTON_W;
    private static final double QUIT_BUTTON_H = RESTART_BUTTON_H;


    public GameOver(Runnable r, Runnable q) throws FileNotFoundException {
        this.background = new Background(new FileInputStream(GAMEOVER_BACKGROUND_PATH));
        this.restartRunnable = r;
        this.quitRunnable = q;
    }

    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        drawBackground(gc);
        drawRestartButton(gc);
        drawQuitButton(gc);
    }

    private <T> void drawBackground(T... obj) {
        background.draw(obj);
    }


    private <T> void drawRestartButton(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.fillOval(RESTART_BUTTON_X, RESTART_BUTTON_Y, RESTART_BUTTON_W, RESTART_BUTTON_H);
    }

    private <T> void drawQuitButton(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.fillOval(QUIT_BUTTON_X, QUIT_BUTTON_Y, QUIT_BUTTON_W, QUIT_BUTTON_H);
    }

    private boolean isOnRestartButton(double mX, double mY) {
        return (mX >= RESTART_BUTTON_X - RESTART_BUTTON_W
                && mX <= RESTART_BUTTON_X + RESTART_BUTTON_W)
                && (mY >= RESTART_BUTTON_Y - RESTART_BUTTON_H
                && mY <= RESTART_BUTTON_Y + RESTART_BUTTON_H);
    }

    private boolean isOnQuitButton(double mX, double mY) {
        return (mX >= QUIT_BUTTON_X - QUIT_BUTTON_W
                && mX <= QUIT_BUTTON_X + QUIT_BUTTON_W)
                && (mY >= QUIT_BUTTON_Y - QUIT_BUTTON_H
                && mY <= QUIT_BUTTON_Y + QUIT_BUTTON_H);
    }

    public void onClick(double mouseX,double mouseY){
        if (isOnRestartButton(mouseX, mouseY)) {
            // TODO: Play button is clicked
            restartRunnable.run();
        }else if (isOnQuitButton(mouseX,mouseY)){
            quitRunnable.run();
        }
    }
}
