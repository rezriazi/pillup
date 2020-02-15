package gamejam.ui;

import gamejam.model.*;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;



public class Game implements Drawer, Updatable {

    private Player player;
    private PlatformManager platformManager;
    private ObstacleManager obstacleManager;
    private GraphicsContext gc;

    private boolean running = false;
    private long dt = 10;

    public Game(GraphicsContext gc) {
        this.player = new Player();
        this.platformManager = new PlatformManager();
        this.obstacleManager = new ObstacleManager();
        this.gc = gc;
    }

    @Override
    public void draw() {
        player.draw(gc);
        platformManager.draw(gc);
        obstacleManager.draw(gc);
    }

    @Override
    public <T> void update(T... obj) {
        player.update();
        platformManager.update();
        obstacleManager.update();
    }

    public void start() {
        running = true;
        new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (running) {
                    if ((now - lastUpdate) >= dt) {
                        // TODO: getting input
                        update();
                        draw();
                    }
                }
            }
        }.start();
    }
}
