package gamejam.model.objects;

import gamejam.model.utils.Animation;
import gamejam.model.utils.Arrow;
import gamejam.ui.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends Object {

    private static final long JUMP_ANIMATION = 250;
    private static final long X_ANIMATION = 200;


    private Animation action;
    private Animation actionX;

    public Player(){
        super(100,300,20,30, 5,-5);
        action = new Animation(JUMP_ANIMATION);
        actionX = new Animation(X_ANIMATION);
//        action.start();
    }


    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.setFill(Color.RED);
        gc.fillRect(getX(),getY(),getW(),getH());

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
