package gamejam.model;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Action implements Updatable{

    private ArrayList<Image> images;
    private long time;
    private long startTime;
    private boolean isInAction;
    private Image currentImage;
    private int index;

    public Action(long t,String ... place){
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
        this.currentImage = this.images.get(0);
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.isInAction = false;
    }

    public void check(){
        if(this.startTime + time >= System.currentTimeMillis()){
           this.isInAction = false;
        }
    }

    @Override
    public <T> void update(T... obj) {
        if(isInAction){
            index++;

        }
    }

}
