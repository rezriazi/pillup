package gamejam.model.objects;

import gamejam.model.Action;
import javafx.scene.canvas.GraphicsContext;

public class Player extends Object {

    private static final long JUMP_ANIMATION = 400;


    private Action action;

    public Player(){
        super(100,100,20,30, 2,-2);
        action = new Action(JUMP_ANIMATION);
        action.start();
    }


    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.fillRect(getX(),getY(),getW(),getH());

    }

    @Override
    public <T> void move(T... obj) {
        this.moveX();
    }

    @Override
    public <T> void jump(T... obj) {
        action.start();
    }

    @Override
    public <T> void update(T... obj) {
        action.update(obj);
        if(action.isInAction()){
            this.moveY();
        }else{
            this.moveY(Object.GRAVITY);
        }
    }
}
