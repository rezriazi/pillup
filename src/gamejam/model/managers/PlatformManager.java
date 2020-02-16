package gamejam.model.managers;

import gamejam.model.interfaces.Drawable;
import gamejam.model.interfaces.Updatable;
import gamejam.model.objects.Platform;
import gamejam.ui.Main;

import java.util.ArrayList;
import java.util.Random;


/**
 * series of platforms
 * if goes out of the screen, gets deleted and a new platform is added with random position
 * */
public class PlatformManager  implements Updatable, Drawable {
    private Random random = new Random();
    private static final int PLATFORM_COUNT = 10;
    private static final int PLATFORM_HEIGHT = 15;
    private static final int MIN_PLATFORM_WIDTH = 30;
    private static final int PLATFORM_WIDTH_INTERVAL = 50;
    private static final int PLATFORM_VELOCITY = 5;
    private static final int HEIGHT_OFFSET = -20;

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
            platformList.add(makePlatform(i));

        }
    }

    /**
     * Generates a new platform
     * */
    private Platform makePlatform(int index) {
        int width = random.nextInt(PLATFORM_WIDTH_INTERVAL) + MIN_PLATFORM_WIDTH;
         return new Platform(random.nextInt(Main.WIDTH - width) ,
                 -(Main.HEIGHT/PLATFORM_COUNT) * index,
                width,
                PLATFORM_HEIGHT,
                PLATFORM_VELOCITY);
    }
    /**
     * if platforms is out of screen delete
     * add new platform with random position
     * */

    @Override
    public <T> void update(T... obj) {
        for (int i  = 0; i < PLATFORM_COUNT; i++) {
            Platform currentPlatform1 = platformList.get(i);
            currentPlatform1.update();
            if (currentPlatform1.getY() > Main.HEIGHT) {
                currentPlatform1.setY(-currentPlatform1.getY() % Main.HEIGHT + HEIGHT_OFFSET);
                currentPlatform1.setX(random.nextInt(Main.WIDTH));
            }

        }
    }

    @Override
    public <T> void draw(T... obj) {
        for (int i  = 0; i < PLATFORM_COUNT; i++) {
            Platform currentPlatform = platformList.get(i);
            //Platform currentPlatform2 = platformList2.get(i);
            currentPlatform.draw(obj);
            //currentPlatform2.draw(obj);
        }
    }
}
