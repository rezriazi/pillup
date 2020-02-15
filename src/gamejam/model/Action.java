package gamejam.model;

import gamejam.model.interfaces.Updatable;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Action implements Updatable {

    private ArrayList<Image> images;
    private double time;
    private long startTime;
    private boolean isInAction;
    private int index;

    public Action(double t,String ... place){
        initImages(place);
        this.time = t;
        this.isInAction = false;
        this.index = 0;
    }

    private void initImages(String ... place){
        for(String s: place){
            Image i = new Image(s);
            this.images.add(i);
        }
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.isInAction = true;
    }

    public void check(){
        if( System.currentTimeMillis() - startTime >= time){
           this.isInAction = false;
        }
    }

    public Image getCurrentImage(){
        return this.images.get(index);
    }

    public boolean isInAction(){
        return this.isInAction;
    }

    @Override
    public <T> void update(T... obj) {
        if(isInAction){
            index++;
            this.check();
        }
    }

}
