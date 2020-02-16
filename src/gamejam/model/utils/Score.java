package gamejam.model.utils;

import gamejam.model.interfaces.Drawable;
import gamejam.model.interfaces.Updatable;
import javafx.scene.canvas.GraphicsContext;

public class Score implements Drawable, Updatable {

    private static final int TIME_CONSTANT = 1;

    private int score;

    public Score(){
        this.score = 0;
    }

    private void setScore(){
        this.score = this.score + TIME_CONSTANT;
    }
    @Override
    public <T> void draw(T... obj) {
        GraphicsContext gc = (GraphicsContext) obj[0];
        gc.fillText(score+"",(Integer)obj[0],(Integer)obj[1]);

    }

    @Override
    public <T> void update(T... obj) {
        setScore();
    }
}
