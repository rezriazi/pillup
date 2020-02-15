package gamejam.ui;

import gamejam.model.Background;
import gamejam.model.interfaces.Drawer;
import gamejam.model.interfaces.Updatable;
import gamejam.model.managers.PlatformManager;
import gamejam.model.objects.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Game implements Drawer, Updatable {

    private static final String BACKGROUND_PATH = "";

    private final Player player;
    private final Background background;
    private final PlatformManager platformManager;
    private final GraphicsContext gc;

    private final int canvasWidth;
    private final int canvasHeight;

    private boolean running = false;

    // TODO: could be final, we should find the correct number
    private long dt = 10;

    public Game(GraphicsContext gc, int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        Image img = new Image(BACKGROUND_PATH);
        this.background = new Background(img, img.getWidth(), img.getHeight(), canvasWidth, canvasHeight);

        this.player = new Player();
        this.platformManager = new PlatformManager();
        this.gc = gc;
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
        new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (running) {
                    if ((now - lastUpdate) >= dt) {
                        // TODO: getting input
                        // TODO: set CANVAS_WIDTH & CANVAS_HEIGHT to public
                        // TODO: gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
                        update();
                        draw();

                        lastUpdate = now;
                    }
                }
            }
        }.start();
    }
}
