package gamejam.model;

import java.awt.*;

public abstract class Object implements Movable{

    private static final double GRAVITY = 0.98;

    private double x;
    private double y;
    private double w;
    private double h;
    private double xVel;
    private double yVel;

    public Object() {
        this.x = 0;
        this.y = 0;
        this.xVel = 0;
        this.yVel = 0;
        this.h = 1;
        this.w = 1;
    }

    public Object(double x,double y,double w,double h,double xVel,double yVel){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.xVel = xVel;
        this.yVel = yVel;
    }
    public Object(double x,double y,double w,double h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.xVel = 0;
        this.yVel = 0;
    }

    //
    public boolean hits(Object o) {
       // Rectangle rectangle = new Rectangle(this.x,this.y,this.w,this.h);
        return false;
    }




}
