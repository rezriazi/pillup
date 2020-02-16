package gamejam.ui;

import gamejam.model.interfaces.Drawer;
import gamejam.model.interfaces.Updatable;
import gamejam.model.managers.PlatformManager;
import gamejam.model.objects.Player;
import gamejam.model.utils.Animation;
import gamejam.model.utils.Background;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Game implements Drawer, Updatable {

    private static final String BACKGROUND_PATH =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/background.png";

    private final Player player;
    private final Background background;
    private final PlatformManager platformManager;
    private final GraphicsContext gc;
    private AnimationTimer at;

    private enum States { MENU , PLAYING, LOST};

    private States state;

    private boolean running = false;

    // TODO: could be final, we should find the correct number
    private long dt = 12;

    public Game(GraphicsContext gc) throws FileNotFoundException {
        this.background =
                new Background(new FileInputStream(BACKGROUND_PATH));
        this.player = new Player();
        this.platformManager = new PlatformManager(this.player);
        this.gc = gc;
        // todo : change playing to menu
        this.state = States.PLAYING;
    }

    Player getPlayer() {
        return player;
    }

    @Override
    public void draw() {
        background.draw(gc);
        player.draw(gc);
        platformManager.draw(gc);
    }

    @Override
    public <T> void update(T... obj) {
        background.draw(gc);
        player.update();
        platformManager.update();
    }

    public void start() {
        running = true;
        at = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (running) {
                    if ((now - lastUpdate) >= dt) {
                        switch (state){
                            case LOST:
                                at.stop();
                                break;

                            case MENU:
                                break;

                            case PLAYING:
                                gc.clearRect(0, 0, Main.CANVAS_WIDTH, Main.CANVAS_HEIGHT);
                                update();
                                draw();
                                if (platformManager.playerCollision()){
                                    state = States.LOST;
                                };
                                lastUpdate = now;
                                break;
                        }
                    }
                }
            }
        };
        at.start();

    }
}
