package gamejam.model.objects;

import gamejam.model.utils.Animation;
import gamejam.model.utils.Arrow;
import gamejam.ui.Main;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;

public class Player extends Object {
    private static final String LITTLE_BOY_PATH =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/littleboy1!.png";
    private static final String LITTLE_BOY_PATH_1 =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/littleboy2!.png";

    private static final long JUMP_ANIMATION = 250;
    private static final long X_ANIMATION = 150;
    private static final double PLAYER_HEIGHT = 60;
    private static final double PLAYER_WIDTH = 70;
    private static final double PLAYER_INIT_X = Main.WIDTH /2 - PLAYER_WIDTH/2;
    private static final double PLAYER_INIT_Y = Main.HEIGHT - 2 * PLAYER_HEIGHT;


    private Animation action;
    private Animation actionX;



    public Player(){
        super(PLAYER_INIT_X,PLAYER_INIT_Y, PLAYER_HEIGHT, PLAYER_WIDTH, 5,-5);
        action = new Animation(JUMP_ANIMATION);
        actionX = new Animation(X_ANIMATION,LITTLE_BOY_PATH,LITTLE_BOY_PATH_1);
        try {
            this.setImage(LITTLE_BOY_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
       // gc.drawImage(this.getImage(),this.getX(),this.getY(),this.getW(),this.getH());
        gc.drawImage(this.actionX.getCurrentImage(),this.getX(),this.getY(),this.getW(),this.getH());
    }

    @Override
    public <T> void move(T... obj) {
        Arrow arrow = (Arrow) obj[0];
        this.setxVel(Math.abs(this.getxVel()) * arrow.getValue());
        actionX.start();
    }

    @Override
    public <T> void jump(T... obj) {
        action.start();
    }

    @Override
    public <T> void update(T... obj) {

        action.update(obj);
        actionX.update(obj);
        if(action.isInAction()) {
            this.moveY();
        } else {
            if (this.getY() + this.getH() < Main.GROUND) {
                this.moveY(Object.GRAVITY);
            }

        }

        if (actionX.isInAction()) {
            this.moveX();
        }

        this.inBound();

    }

    private void inBound() {
        //if (this.getY() + this.getH() >= Main.CANVAS_HEIGHT){
        //            this.setY(Main.CANVAS_HEIGHT - this.getH());
        //        }else
         if(this.getY() <= 0){
            this.setY(0);
        }
        if(this.getX() <= 0){
            this.setX(0);
        }else if(this.getX() + this.getW() >= Main.CANVAS_WIDTH){
            this.setX(Main.CANVAS_WIDTH - this.getW());
        }
    }


    public void pill(String type){
        switch (type){
            case "red":
                //bigger
                break;
            case "blue":
                //older
                break;
            case "yellow":
                //younger
                break;
        }
    }
}
