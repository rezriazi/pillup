package gamejam.model;

import java.util.ArrayList;

/**
 * series of platforms
 * if goes out of the screen, gets deleted and a new platform is added with random position
 * */
public class PlatformManager {
    private ArrayList<Platform> platformList;
    public PlatformManager() {
        platformList = new ArrayList<>();
    }

}
