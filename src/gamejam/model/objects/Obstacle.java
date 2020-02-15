package gamejam.model.objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Obstacle extends Object {
    static enum types {
        FLAME_SHIELD,
        FLAME,
        FLOAT,
    }

    types t;
    // x, y is for platform
    // width, height is for obstacle
    public Obstacle(int xPlat, int yPlat, int width, int height) {
        super(xPlat + width, yPlat - height, width , height);
        changeType();
    }
    @Override
    public <T> void move(T... obj) {
        // Obstacle moves with its platform
        Platform platform = (Platform) obj[0];
        this.setX(platform.getX());
        this.setY(platform.getY() - this.getH());

    }

    @Override
    public <T> void jump(T... obj) {
        // Obstacle doesnt jump
    }

    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.setFill(Color.BLUE);
        gc.fillRect(getX(),getY(),getW(),getW());
    }


    public void changeType() {
        Random random = new Random();
        t = types.values()[random.nextInt(types.values().length)];
    }

    @Override
    public <T> void update(T... obj) {
        // hit -> remove
        Player player = (Player) obj[0];
        if (player.hits(this)) {
            switch (t) {
                case FLAME_SHIELD:
                    // add flame shield
                    break;
                case FLOAT:
                    // float so never falls
                    break;
                case FLAME:
                    // flame causes death
                    break;
            }
        }
    }
}
