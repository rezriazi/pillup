package gamejam.model.objects;

import gamejam.model.interfaces.Drawable;
import gamejam.model.interfaces.Updatable;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class Platform extends Object implements Updatable, Drawable {

    enum types {
        FOOD,
        PILL,
        DEGREE,
        MONEY,
    }

    private types type = types.FOOD;
    public Platform(double x, double y, double w, double h, double yVel) {
        super(x, y, w, h, 0, yVel);
        changeType();
    }

    @Override
    public <T> void move(T... obj) {
        this.setY(this.getY() + this.getyVel());
    }

    @Override
    public <T> void jump(T... obj) {}

    // TODO
    @Override
    public <T> void draw(T... obj) {
        // get the gc and draw the image at its position
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.fillRect(getX(),getY(),getW(),getH());
    }

    @Override
    public <T> void update(T... obj) {
        move(obj);
    }

    public void changeType() {
        Random random = new Random();
        this.type = types.values()[random.nextInt(types.values().length)];
    }

}
