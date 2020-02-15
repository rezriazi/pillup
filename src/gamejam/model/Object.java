package gamejam.model;

public abstract class Object implements Movable{

    private static final double GRAVITY = 0.98;

    private double x;
    private double y;
    private double xVel;
    private double yVel;

    public Object() {
        this.x = 0;
        this.y = 0;
        this.xVel = 0;
        this.yVel = 0;
    }

    public Object(double x,double y,double xVel,double yVel){
        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;
    }

    public boolean




}
