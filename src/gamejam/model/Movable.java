package gamejam.model;

public interface Movable {
    <T> void move(T ... obj);
    <T> void jump(T ... obj);
    <T> void moh();
}