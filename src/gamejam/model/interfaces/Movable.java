package gamejam.model.interfaces;

public interface Movable {
    <T> void move(T ... obj);
    <T> void jump(T ... obj);
}