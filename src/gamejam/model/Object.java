package gamejam.model;


import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


public abstract class Object implements Movable, Drawable,Updatable{

    public static final double GRAVITY = 0.98;

    private double x;
    private double y;
    private double w;
    private double h;
    private double xVel;
    private double yVel;
    private Rectangle r;
    private Image image;

    public Object() {
        this.x = 0;
        this.y = 0;
        this.xVel = 0;
        this.yVel = 0;
        this.h = 1;
        this.w = 1;
        this.initRect();
        this.image = null;
    }

    public Object(double x,double y,double w,double h,double xVel,double yVel){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.xVel = xVel;
        this.yVel = yVel;
        this.initRect();
    }
    public Object(double x,double y,double w,double h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.xVel = 0;
        this.yVel = 0;
        this.initRect();
    }

    // init rectangle
    private void initRect(){
        this.r = new Rectangle((int)x,(int)y,(int)w,(int)h);
    }

    /**
     * returns if this object is intersecting another
     * @param o
     * @return
     */
    public boolean hits(Object o) {
        return this.r.intersects(o.getX(),o.getY(),o.getW(),o.getH());
    }

    protected void move() {
        this.x += this.xVel;
        this.y += this.yVel;
    }

    protected void moveX() {
        this.x += this.xVel;
    }

    protected void moveY(double ... vals) {
        this.y += this.yVel;
        for(double v:vals){
            this.y+=v;
        }
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getxVel() {
        return xVel;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public Rectangle getR() {
        return r;
    }

    public void setR(Rectangle r) {
        this.r = r;
    }

    public void setImage(String i){
        this.image = new Image(i);
    }
}
