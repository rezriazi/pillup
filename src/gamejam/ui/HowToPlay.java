package gamejam.ui;

import gamejam.model.interfaces.Drawable;
import gamejam.model.utils.Background;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HowToPlay implements Drawable {

    private static final String HOW_TO_PLAY  =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/how-to-play.jpg";

    private static final String BACK  =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/back_to_menu.png";

    private static final String PLAY_BUTTON_PATH =
            System.getProperty("user.dir") + "/src/gamejam/assets/PLAYBUTTON.png";

    private static final double BUTTON_WIDTH = 75;
    private static final double BUTTON_HEIGHT = 50;


    private static final double BACK_WIDTH = 100;
    private static final double BACK_HEIGHT = 50;

    private static final double BACK_X = 20;
    private static final double BACK_Y = Main.HEIGHT - BACK_HEIGHT - 30;

    private static final double PLAY_BUTTON_X = Main.WIDTH/2 - BUTTON_WIDTH/2;
    private static final double PLAY_BUTTON_Y = Main.HEIGHT - BUTTON_HEIGHT - 30 ;

    private Background background;
    private Image back;
    private boolean alreadyShown;
    private Runnable startGame;
    private Runnable goToMenu;
    private Image playButtonImage;


    public HowToPlay(Runnable start,Runnable m) {
        startGame = start;
        goToMenu = m;

        try {
            this.background = new Background(new FileInputStream(HOW_TO_PLAY));
            back = new Image(new FileInputStream(BACK),
                    BACK_WIDTH,BACK_HEIGHT ,
                    false, false);
            this.playButtonImage =
                    new Image(new FileInputStream(PLAY_BUTTON_PATH),
                            BUTTON_WIDTH, BUTTON_HEIGHT,
                            false, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        background.draw(obj);
        gc.drawImage(this.back,BACK_X,BACK_Y);
        drawPlayButton(obj);

    }

    public void onClick(double x,double y){
        if(isOnBack(x,y)){
            goToMenu.run();
        }else{
            alreadyShown = true;
            this.startGame.run();
        }

    }
    private <T> void drawPlayButton(T ... obj){
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.drawImage(playButtonImage, PLAY_BUTTON_X, PLAY_BUTTON_Y);
    }

    public boolean isAlreadyShown(){
        return this.alreadyShown;
    }


    private boolean isOnBack(double mX, double mY) {
        return (mX >= BACK_X && mX <= BACK_X + back.getWidth())
                && (mY >= BACK_Y && mY <= BACK_Y + back.getHeight());
    }

}
