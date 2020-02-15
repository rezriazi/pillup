package gamejam.model.managers;

import gamejam.model.interfaces.Drawable;
import gamejam.model.interfaces.Updatable;
import gamejam.model.objects.Obstacle;
import gamejam.model.objects.Platform;

import java.util.ArrayList;

public class ObstacleManager implements Updatable, Drawable {
    private ArrayList<Obstacle> obstacleList;
    //private final PlatformManager PM;
    private static final int OBSTACLE_COUNT = 7;


    public ObstacleManager() {
        obstacleList = new ArrayList<>();
        //PM = platformManager;
    }

    private void fillObstacleList() {
        for (int i = 0; i < OBSTACLE_COUNT; i++) {

        }
    }

    @Override
    public <T> void update(T... obj) {

    }

    @Override
    public <T> void draw(T... obj) {

    }
}
