package gamejam.model.objects;

import gamejam.model.utils.Animation;
import gamejam.model.utils.Arrow;
import gamejam.model.utils.Score;
import gamejam.ui.Main;
import gamejam.ui.ReadWrite;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Player extends Object {

    private static final String FILE_NAME = "high_score_pill_up";

    private static final String LITTLE_BOY_PATH =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/littleboy1!.png";
    private static final String LITTLE_BOY_PATH_1 =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/littleboy2!.png";

    // adult age
    private static final String ADULT_PATH =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/adultage1!.png";
    private static final String ADULT_PATH_1 =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/adultage2!.png";


    private static final String OLD_PATH =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/oldage1!.png";
    private static final String OLD_PATH_1 =
            System.getProperty("user.dir") +
                    "/src/gamejam/assets/oldage2!.png";

    private static final long JUMP_ANIMATION = 250;
    private static final long X_ANIMATION = 100;
    private static final double PLAYER_HEIGHT = 60;
    private static final double PLAYER_WIDTH = 70;
    private static final double PLAYER_INIT_X = Main.WIDTH /2 - PLAYER_WIDTH/2;
    private static final double PLAYER_INIT_Y = Main.HEIGHT - 2 * PLAYER_HEIGHT;



    public static double dx = 9;

    private static final double RED_PILL_RATIO = 10;

    enum Age {
        YOUNG,
        ADULT,
        OLD
    }

    private Animation action;
    private Animation actionX;

    private Age age;
    int ageCount;
    private Score score;

    public Player() {
        super(PLAYER_INIT_X,PLAYER_INIT_Y, PLAYER_HEIGHT, PLAYER_WIDTH, 5,-5);
        changeCharacter(Age.YOUNG);
        dx = 9;
        score = new Score();
    }

    public Player(Age a){
        super(PLAYER_INIT_X,PLAYER_INIT_Y, PLAYER_HEIGHT, PLAYER_WIDTH, 5,-5);
        changeCharacter(a);
    }


    public void changeCharacter(Age a) {
        this.age = a;
        String[] paths = getPathsForAge();
        action = new Animation(JUMP_ANIMATION,paths);
        actionX = new Animation(X_ANIMATION,paths);
        try {
            this.setImage(paths[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String[] getPathsForAge() {
        String[] s = new String[2];
        switch (this.age){
            case YOUNG:
                s[0] =  LITTLE_BOY_PATH;
                s[1] = LITTLE_BOY_PATH_1;
                break;
            case ADULT:
                s[0] = ADULT_PATH;
                s[1] = ADULT_PATH_1;
                break;
            case OLD:
                s[0] = OLD_PATH;
                s[1] = OLD_PATH_1;
                break;
        }
        return s;
    }


    public int getScore(){return this.score.getScore();}

    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
       // gc.drawImage(this.getImage(),this.getX(),this.getY(),this.getW(),this.getH());
        if(actionX.isInAction()){
            gc.drawImage(this.actionX.getCurrentImage(),this.getX(),this.getY(),this.getW(),this.getH());
        }else{
            gc.drawImage(this.action.getCurrentImage(),this.getX(),this.getY(),this.getW(),this.getH());
        }

        score.draw(obj);

    }

    @Override
    public <T> void move(T... obj) {
        Arrow arrow = (Arrow) obj[0];
        this.setxVel(Math.abs(this.getxVel()) * arrow.getValue());
        actionX.start();
    }

    @Override
    public <T> void jump(T... obj) {
        action.start();
    }

    @Override
    public <T> void update(T... obj) {

        action.update(obj);
        actionX.update(obj);
        if(action.isInAction()) {
            this.moveY();
        } else {
            if (this.getY() + this.getH() < Main.GROUND) {
                this.moveY(Object.GRAVITY);
            }

        }

        if (actionX.isInAction()) {
            this.moveX();
        }

        this.updateCharacter();
        this.inBound();
        score.update();

    }


    public int updateHighScore() {
        try {
            String val = ReadWrite.readFile(FILE_NAME);
            if(!val.isEmpty()){
                int v = Integer.parseInt(val.trim());
                if(this.score.getScore()>v){
                    ReadWrite.saveFile(this.score.getScore()+"",FILE_NAME);
                    return this.score.getScore();
                }
                return v;
            }else{
                ReadWrite.saveFile(this.score.getScore()+"",FILE_NAME);
                return this.score.getScore();
            }

        } catch (IOException e) {
            try {
                ReadWrite.saveFile(this.score.getScore()+"",FILE_NAME);
            } catch (IOException ex) {

            }
            //e.printStackTrace();
        }
        return 0;
    }


    private void updateCharacter() {
        if(ageToInt()!=this.ageCount){
            setAgeFromInt(this.ageCount);
        }
    }

    private int ageToInt(){
        switch (age){
            case ADULT:
                return 1;
            case YOUNG:
                return 0;
            case OLD:
                return 2;
        }
        return 0;
    }

    private void setAgeFromInt(int age){
        switch (age) {
            case 0:
                this.changeCharacter(Age.YOUNG);
                break;
            case 1:
                this.changeCharacter(Age.ADULT);
                break;
            case 2:
                this.changeCharacter(Age.OLD);
                break;
        }
    }

    private void inBound() {
        //if (this.getY() + this.getH() >= Main.CANVAS_HEIGHT){
        //            this.setY(Main.CANVAS_HEIGHT - this.getH());
        //        }else
         if(this.getY() <= 0){
            this.setY(0);
        }
        if(this.getX() <= 0){
            this.setX(0);
        }else if(this.getX() + this.getW() >= Main.CANVAS_WIDTH){
            this.setX(Main.CANVAS_WIDTH - this.getW());
        }
    }


    // retruns whether they should loose or not after taking this pill
    public boolean pill(String type){
        switch (type){
            case "red":
                //bigger
                return redPill();
            case "blue":
                //older
                return bluePill();
            case "yellow":
                //younger
                return yellowPill();
        }
        return false;
    }

    private boolean redPill(){
        this.setW(this.getW() + RED_PILL_RATIO);
        this.setH(this.getH() + RED_PILL_RATIO);
        dx+= RED_PILL_RATIO/4;
        if (this.getY() + this.getH() >= Main.GROUND) {
            this.setY(Main.GROUND - this.getH());
        }
        return false;
    }

    private boolean bluePill() {
        //older
        this.ageCount++;
        if(this.ageCount>2) {
            // they should loose
            ageCount = 2;
            return true;
        }
        return false;
    }


    private boolean yellowPill(){
        //younger
        this.ageCount --;
        if(this.ageCount < 0){
            // should loose
            ageCount = 0;
            return true;
        }
        return false;
    }


}
