package gamejam.model.objects;

import gamejam.model.interfaces.Drawable;
import gamejam.model.interfaces.Updatable;

public class Platform extends Object implements Updatable, Drawable {

    public Platform(double x, double y, double w, double h, double yVel) {
        super(x, y, w, h, 0, yVel);
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
    }

    @Override
    public <T> void update(T... obj) {
        move(obj);
    }
}
