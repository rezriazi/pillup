package gamejam.model.objects;

import gamejam.model.utils.Action;
import gamejam.model.utils.Arrow;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends Object {

    private static final long JUMP_ANIMATION = 400;


    private Action action;

    public Player(){
        super(100,100,20,30, 0,0);
        action = new Action(JUMP_ANIMATION);
        action.start();
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
        this.setxVel(10);
        this.setX(this.getX() + (arrow.getValue() * this.getxVel()));
    }

    @Override
    public <T> void jump(T... obj) {
        action.start();
    }

    @Override
    public <T> void update(T... obj) {
        action.update(obj);
        if(action.isInAction()) {
            this.moveY();
        }else {
            this.moveY(Object.GRAVITY);
        }
    }
}
