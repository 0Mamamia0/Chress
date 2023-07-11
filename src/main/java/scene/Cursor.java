package scene;

import animation.FrameAnimation;
import io.github.humbleui.skija.Image;

public class Cursor extends BoardObject {

    public static final int CURSOR_WIDTH = 28;
    public static final int CURSOR_SIZE_HALF =  CURSOR_WIDTH / 2;
    public static final int BOARD_CELL_SIZE = 26;
    public static final int BOARD_CELL_SIZE_HALF = BOARD_CELL_SIZE / 2;


    private static final long[] rate = new long[] { 400, 400};
    private static final int[] frame = new int[] { 0, 1};



    public FrameAnimation animation = new FrameAnimation(rate, frame) {
        @Override
        public void updateValue(int frame) {
            setFrame(frame);
        }
    };


    public Cursor() {

    }


    @Override
    protected void show() {
        super.show();
        animation.start();
        addAnimation(animation);
    }


    @Override
    protected void hide() {
        super.hide();
        animation.cancel();
    }



    @Override
    public void setFrame(int frame) {
        if(frame == 0 || frame == 1) {
            super.setFrame(frame);
        }
    }
}
