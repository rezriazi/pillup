package gamejam.model;

import gamejam.ui.Main;

import java.util.ArrayList;
import java.util.Random;


/**
 * series of platforms
 * if goes out of the screen, gets deleted and a new platform is added with random position
 * */
public class PlatformManager  implements Updatable{
    private Random random = new Random();
    private static final int PLATFORM_COUNT = 7;
    private static final int PLATFORM_HEIGHT = 20;
    private static final int MIN_PLATFORM_WIDTH = 30;
    private static final int PLATFORM_WIDTH_INTERVAL = 50;
    private static final int PLATFORM_VELOCITY = 30;

    private ArrayList<Platform> platformList;
    // constructor
    public PlatformManager() {
        platformList = new ArrayList<>();
        fillPlatformList();
    }

    /**
     * Initializes the platforms with random positions and adds them to the list
     * */
    private void fillPlatformList() {
        for (int i = 0; i < PLATFORM_COUNT; i++) {
            platformList.add(
                    new Platform(random.nextInt(Main.WIDTH),
                                random.nextInt(Main.HEIGHT),
                            random.nextInt(PLATFORM_WIDTH_INTERVAL) + MIN_PLATFORM_WIDTH,
                                PLATFORM_HEIGHT,
                                PLATFORM_VELOCITY));
        }
    }
    /**
     * if platforms is out of screen delete
     * add new platform with random position
     * */

    @Override
    public <T> void update(T... obj) {
        for (int i  = 0; i < PLATFORM_COUNT; i++) {
            Platform currentPlatform = platformList.get(i);
            currentPlatform.update();
            if (currentPlatform.getY() > Main.HEIGHT) {
                currentPlatform.setY(- random.nextInt(Main.HEIGHT));
            }
        }
    }
}
