package gamejam.model.managers;

import gamejam.model.interfaces.Drawable;
import gamejam.model.interfaces.Updatable;
import gamejam.model.objects.Platform;
import gamejam.model.objects.Player;
import gamejam.ui.Main;

import java.util.ArrayList;
import java.util.Random;


/**
 * series of platforms
 * if goes out of the screen, gets deleted and a new platform is added with random position
 * */
public class PlatformManager  implements Updatable, Drawable {
    private Random random = new Random();
    private static final int PLATFORM_COUNT = 7;
    private static final int PLATFORM_HEIGHT = 15;
    private static final int MIN_PLATFORM_WIDTH = 30;
    private static final int PLATFORM_WIDTH_INTERVAL = 50;
    private static final int PLATFORM_VELOCITY = 5;
    private static final int HEIGHT_OFFSET = -20;

    private ArrayList<Platform> platformList;
    private Player player;

    // constructor
    public PlatformManager(Player player) {
        platformList = new ArrayList<>();
        fillPlatformList();
        this.player = player;
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
            Platform currentPlatform = platformList.get(i);
            currentPlatform.update();
            if (currentPlatform.getY() > Main.HEIGHT) {
                currentPlatform.setY(-currentPlatform.getY() % Main.HEIGHT + HEIGHT_OFFSET);
                currentPlatform.setX(Math.abs(random.nextInt(Main.WIDTH) - currentPlatform.getW()));
            }
        }

        playerCollision();
    }

    @Override
    public <T> void draw(T... obj) {
        for (int i  = 0; i < PLATFORM_COUNT; i++) {
            Platform currentPlatform = platformList.get(i);
            currentPlatform.draw(obj);
        }
    }

    //TODO
    public boolean playerCollision() {
        for (int i = 0; i < PLATFORM_COUNT; i++) {
            if (platformList.get(i).hits(this.player)) {

            }
        }
        return false;
    }
}
