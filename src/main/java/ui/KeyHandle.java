package ui;

public interface KeyHandle {

    boolean isKeyEnable();
    void setKeyEnable(boolean enable);
    void keyPressed(int key);
    void keyRelease(int key);
    void keyRepeated(int key);
}
