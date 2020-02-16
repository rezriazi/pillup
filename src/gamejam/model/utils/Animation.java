package gamejam.model.utils;

import gamejam.model.interfaces.Updatable;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Animation implements Updatable {

    private ArrayList<Image> images;
    private double time;
    private long startTime;
    private boolean isInAction;
    private int index;

    public Animation(double t, String ... place){
        initImages(place);
        this.time = t;
        this.isInAction = false;
        this.index = 0;
    }

    private void initImages(String ... place){
        this.images = new ArrayList<>();
        for(String s: place){
            Image i = null;
            try {
                i = new Image(new FileInputStream(s));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
            if(index + 1 >= this.images.size()){
                this.index = 0;
            }else{
                index++;
            }

            this.check();
        }
    }

}
