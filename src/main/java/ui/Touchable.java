package ui;

public interface Touchable {

    boolean isTouchEnable();
    void setTouchEnable(boolean enable);
    boolean adjustClick(int x, int y);

    boolean pointerPressed(int x, int y);
    boolean pointerDragged(int x, int y);
    boolean pointerReleased(int x, int y);
}
