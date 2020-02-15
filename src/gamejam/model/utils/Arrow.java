package gamejam.model.utils;

public enum Arrow {
    LEFT(-1),
    RIGHT(+1),
    UP(-1),
    SPACE(-1);

    private int value;

    Arrow(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
