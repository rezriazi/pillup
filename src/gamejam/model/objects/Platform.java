package gamejam.model.objects;

import gamejam.model.interfaces.Drawable;
import gamejam.model.interfaces.Updatable;
import gamejam.model.managers.PlatformManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.Random;

public class Platform extends Object implements Updatable, Drawable {

    private static final int PILL_WIDTH = 50;
    private static final int PILL_HEIGHT = 20;

    private static final int OVERALL_ODDS = 10;
    private static final int PILL_ODDS_INDEX = 9;

    enum types {
        PILL,
        SPIKE,
    }

    enum PillColors {
        BLUE,
        RED,
        YELLOW,
    }

    private types type = types.PILL;
    private PillColors pillColor = PillColors.BLUE;
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
        if (this.type == types.SPIKE) {
            gc.fillRect(getX(),getY(),getW(),getH());
        } else {
            switch (this.pillColor){
                case RED:
                    gc.drawImage(PlatformManager.redImg, this.getX(), this.getY(), PILL_WIDTH, PILL_HEIGHT);
                    break;
                case BLUE:
                    gc.drawImage(PlatformManager.blueImg, this.getX(), this.getY(), PILL_WIDTH, PILL_HEIGHT);
                    break;
                case YELLOW:
                    gc.drawImage(PlatformManager.yellowImg, this.getX(), this.getY(), PILL_WIDTH, PILL_HEIGHT);
                    break;
            }
        }

    }

    @Override
    public <T> void update(T... obj) {
        move(obj);
    }

    public void changeType() {
        Random random = new Random();
        int index = random.nextInt(OVERALL_ODDS) + 1;
        if (index > PILL_ODDS_INDEX) {
            this.type = types.PILL;
        } else {
            this.type = types.SPIKE;
        }
        if (this.type == types.PILL) {
            this.pillColor = PillColors.values()[random.nextInt(PillColors.values().length)];
        } else {
            this.pillColor = PillColors.BLUE;
        }
    }

}
