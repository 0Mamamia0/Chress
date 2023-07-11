package ui;

public interface InputHandle extends KeyHandle, Touchable {
    boolean isInputEnable();
    void setInputEnable(boolean enable);
}
