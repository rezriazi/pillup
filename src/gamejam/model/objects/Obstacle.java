package gamejam.model.objects;

public class Obstacle extends Object {
    // x, y is for platform
    // width, height is for obstacle
    public Obstacle(int xPlat, int yPlat, int width, int height) {
        super(xPlat + width, yPlat - height, width , height);
    }
    @Override
    public <T> void move(T... obj) {

    }

    @Override
    public <T> void jump(T... obj) {

    }

    @Override
    public <T> void draw(T... obj) {

    }

    @Override
    public <T> void update(T... obj) {

    }
}
