package gamejam.model;

import javafx.scene.canvas.GraphicsContext;

public class Player extends Object {


    public Player(){
        super(100,100,20,30, 2,2);
    }


    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.fillRect(getX(),getY(),getW(),getH());

    }

    @Override
    public <T> void move(T... obj) {

    }

    @Override
    public <T> void jump(T... obj) {

    }
}
