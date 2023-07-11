package util;

public class Key {

    public static final int ACTION_PRESS = 0;
    public static final int ACTION_RELEASE = 1;

    public static final int KEY_OK = 1;
    public static final int KEY_UP = 1 << 1;
    public static final int KEY_DOWN = 1 << 2;
    public static final int KEY_LEFT = 1 << 3;
    public static final int KEY_RIGHT = 1 << 4;
    public static final int KEY_L_SOFT = 1 << 5;
    public static final int KEY_R_SOFT = 1 << 6;



    private int keyState = 0;



    public void onKey(int key, int action) {

        if(action == ACTION_PRESS) {
            keyState |= key;
        } else  {
            keyState &= ~key;
        }

    }



    public int getKeyState() {
        return keyState;
    }
}
