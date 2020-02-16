package gamejam.model.objects;

import gamejam.model.utils.Animation;
import gamejam.model.utils.Arrow;
import gamejam.ui.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Player extends Object {
    private static final String LITTLE_BOY_PATH =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/littleboy1!.png";

    private static final long JUMP_ANIMATION = 250;
    private static final long X_ANIMATION = 150;
    private static final double PLAYER_HEIGHT = 60;
    private static final double PLAYER_WIDTH = 70;


    private Animation action;
    private Animation actionX;

    public Player(){
        super(100,300, PLAYER_HEIGHT, PLAYER_WIDTH, 5,-5);
        action = new Animation(JUMP_ANIMATION);
        actionX = new Animation(X_ANIMATION);
        try {
            this.setImage(LITTLE_BOY_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        action.start();
    }


    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
//        gc.setFill(Color.RED);
//        gc.fillRect(getX(),getY(),getW(),getH());
        gc.drawImage(this.getImage(),this.getX(),this.getY(),this.getW(),this.getH());
    }

    @Override
    public <T> void move(T... obj) {
        // TODO
        Arrow arrow = (Arrow) obj[0];
        this.setxVel(Math.abs(this.getxVel()) * arrow.getValue());
        actionX.start();
        //this.setX(this.getX() + (arrow.getValue() * this.getxVel()));
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
        }else {
            this.moveY(Object.GRAVITY);
        }

        if(actionX.isInAction()){
            this.moveX();
        }



        if(this.getY() + this.getH() >= Main.CANVAS_HEIGHT){
            this.setY(Main.CANVAS_HEIGHT - this.getH());
            //System.out.println("out of the scene");
        }
    }
}
