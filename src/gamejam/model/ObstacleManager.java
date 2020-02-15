package gamejam.model;

import java.util.ArrayList;

public class ObstacleManager implements Updatable, Drawable {
    private ArrayList<Obstacle> obstacleList;
    private static final int OBSTACLE_COUNT = 7;


    public ObstacleManager() {
        obstacleList = new ArrayList<>();
    }

    @Override
    public <T> void update(T... obj) {

    }

    @Override
    public <T> void draw(T... obj) {

    }
}
