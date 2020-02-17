package gamejam.ui;

import gamejam.model.interfaces.Drawer;
import gamejam.model.interfaces.Updatable;
import gamejam.model.managers.PlatformManager;
import gamejam.model.objects.Player;
import gamejam.model.utils.Background;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Game implements Drawer, Updatable {

    private static final String BACKGROUND_PATH =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/background_day.png";




    private static final String MP3_PATH =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/music.mp3";

    private Player player;
    private final Background background;
    private PlatformManager platformManager;
    private final GraphicsContext gc;
    private final Canvas canvas;

    //main menu
    private final MainMenu mainMenu;
    private final EscapeMenu escapeMenu;
    private GameOver gameOver;
    private final HowToPlay howToPlay;

    private boolean running = false;

    // TODO: could be final, we should find the correct number
    private long dt = 12;

    enum State {
        PLAYING,
        LOST,
        MAIN_MENU,
        ESC_MENU,
        HTP
    }

    private AnimationTimer at;
    private State state;

    private Media sound;
    private MediaPlayer mediaPlayer;

    public Game(GraphicsContext gc, Canvas c) throws FileNotFoundException {
        this.sound = new Media(new File(MP3_PATH).toURI().toString());
        this.mediaPlayer = new MediaPlayer(sound);
        this.background =
                new Background(new FileInputStream(BACKGROUND_PATH));
        this.player = new Player();
        this.platformManager = new PlatformManager(this.player);
        this.canvas  = c;
        this.mainMenu = new MainMenu(this::startGame,mediaPlayer);
        this.escapeMenu = new EscapeMenu(this::resume, this::restart, this::quitToMainMenu, mediaPlayer);

        this.gameOver = new GameOver(this::restart, this::quitToMainMenu, this.player);
        this.setupCanvas();
        this.gc = gc;
        this.howToPlay = new HowToPlay(this::startGame,this::quitToMainMenu);

        this.state = State.MAIN_MENU;


    }

    Player getPlayer() {
        return player;
    }

    State getState() {
        return this.state;
    }

    @Override
    public void draw() {
        //gc.setFill(Color.WHITE);
        background.draw(gc);
        player.draw(gc);
        platformManager.draw(gc);
    }

    @Override
    public <T> void update(T... obj) {
        player.update();
        platformManager.update();
    }


    private void checkGameOver() {
        if(platformManager.playerCollision()){
            this.state = State.LOST;
        }
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
                            case MAIN_MENU:
                                mainMenu.draw(gc);
                                break;
                            case LOST:
                                gameOver.draw(gc);
                                break;
                            case PLAYING:
                                gc.clearRect(0, 0, Main.CANVAS_WIDTH, Main.CANVAS_HEIGHT);
                                update();
                                draw();
                                checkGameOver();
                                break;
                            case ESC_MENU:
                                escapeMenu.draw(gc);
                                break;
                            case HTP:
                                howToPlay.draw(gc);
                                break;
                        }
                        lastUpdate = now;
                    }
                }
            }
        };
        at.start();
        mediaPlayer.play();
    }

    public void setupCanvas() {
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double mouseX = event.getSceneX();
                double mouseY = event.getSceneY();
                if(state == State.MAIN_MENU ) {
                    mainMenu.onClick(mouseX,mouseY,gc);
                }else if(state == State.ESC_MENU){
                    escapeMenu.onClick(mouseX,mouseY);
                }else if(state == State.LOST){
                    gameOver.onClick(mouseX,mouseY);
                }else if(state == State.HTP){
                    howToPlay.onClick(mouseX,mouseY);
                }


            }
        });
    }


    public void openEscapeMenu(){
        if(state == State.PLAYING) {
            state = State.ESC_MENU;
        }
    }

    public void quitToMainMenu() {
        this.state = State.MAIN_MENU;
    }

    public void restart(){
        this.player = new Player();
        this.platformManager = new PlatformManager(this.player);
        try {
            this.gameOver = new GameOver(this::restart, this::quitToMainMenu, this.player);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        state = State.PLAYING;

    }

    public void resume() {
        state = State.PLAYING;
    }

    public void startGame(){
        if(howToPlay.isAlreadyShown()){
            restart();
//            gameOver.setPlayer(this.player);
            state = State.PLAYING;
        }else{
            state = State.HTP;
        }

    }


}
