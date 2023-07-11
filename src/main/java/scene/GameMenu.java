package scene;

import animation.Animation;
import animation.IntAnimation;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.types.Rect;
import ui.Sprite;
import ui.SpriteSheet;
import util.Key;

public class GameMenu extends Node {


    private int clip_l;
    private int clip_r;
    private int clip_t;
    private int clip_b;


    private int selectIndex;
    private int fan_select_height;


    private static final int L_PADDING = 47;
    private static final int T_PADDING = 32;
    public static final int ITEM_WIDTH = 32;
    private static final int SELECT_OFFSET = 8;



    private boolean backStack;

    private MenuSelectCallback callback;

    private final Sprite fan = new Sprite("fan.png");
    private final Sprite fanSc = new Sprite("fanSc.png");
    private final Sprite select = new Sprite("fanSelect.png");
    private final SpriteSheet fanFont = new SpriteSheet("fanFont.png", 6);


    private final Animation animation1 = new IntAnimation(120, 0) {
        @Override
        public void updateValue(int value) {
            updateClipRegion(value, 240 - value);
        }
    };

    private final Animation animation_select = new IntAnimation(0, select.height) {
        @Override
        public void updateValue(int value) {
            fan_select_height = value;
        }
    };




    GameMenu() {
        clip_l = 120;
        clip_r = 120;
        clip_t = 0;
        clip_b = fan.getHeight();


        int y = ((320 - fan.getHeight()) >> 1) - 7;
        setBound(0, y, 240, fan.getHeight());


        animation1.setDuration(500);
        animation_select.setDuration(250);
    }

    public void setSelectCallback(MenuSelectCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void show() {
        super.show();
        fan_select_height = select.height;
        animation1.reset();
        animation1.start();
        addAnimation(animation1);

    }

    @Override
    protected void hide() {
        super.hide();
        animation1.cancel();

    }


    private void updateClipRegion(int left, int right) {
        clip_l = Math.max(0, left);
        clip_r = Math.min(right, 240);
    }





    @Override
    public void draw(Canvas canvas) {
        if (isVisible()) {
            int save = canvas.save();
            canvas.clipRect(Rect.makeLTRB(clip_l, clip_t, clip_r, clip_b));
            fan.draw(canvas);

            drawMenuItems(canvas);

            if (clip_l != 0) {
                canvas.translate(clip_l, 0);
                fanSc.draw(canvas);
                canvas.translate(-clip_l, 0);
            }

            if (clip_r != 240) {
                canvas.translate(clip_r - fanSc.width, 0);
                fanSc.draw(canvas);
                canvas.translate(-(clip_r - fanSc.width), 0);
            }

            canvas.restoreToCount(save);
        }
    }

    private void drawMenuItems(Canvas canvas) {
        drawItem(canvas, 0, 0);
        drawItem(canvas, 1, 1);
        drawItem(canvas, 2, 2);
        drawItem(canvas, 3, 3);
        drawItem(canvas, 4, backStack ? 4 : 5);

    }

    private void drawItem(Canvas canvas, int index, int frame) {
        int x = index * ITEM_WIDTH + L_PADDING;
        int w = fanFont.getWidth();

        if (index == selectIndex) {
            int s_x = index * 32 + L_PADDING + SELECT_OFFSET;
            int s_w = s_x + select.getWidth();

            if (clip_r >= s_x || clip_l <= s_w) {
                select.draw(canvas, s_x, T_PADDING, select.width, fan_select_height);
            }

        }

        if (clip_r >= x || clip_l <= x + w) {
            fanFont.draw(canvas, x, T_PADDING, frame);
        }
    }


    private void updateMenuSelect() {
        animation_select.reset();
        animation_select.start();
        addAnimation(animation_select);
    }


    @Override
    public void keyPressed(int key) {
        if (key == Key.KEY_LEFT) {
            selectIndex--;
            if (selectIndex == -1) selectIndex = 4;
        } else if (key == Key.KEY_RIGHT) {
            selectIndex++;
            if (selectIndex == 5) selectIndex = 0;
        }
        updateMenuSelect();
    }

    @Override
    public boolean pointerPressed(int x, int y) {
        if (x > L_PADDING && x < L_PADDING + 5 * ITEM_WIDTH && y >= T_PADDING && y <= T_PADDING + fanFont.getHeight()) {
            int index = (x - L_PADDING) / ITEM_WIDTH;
            if (selectIndex == index) {
                if (callback != null) {
                    callback.onSelect(this, index);
                }
            }

            this.selectIndex = index;
            updateMenuSelect();
        }
        return true;
    }





//    if (this.skin_type == 0) {
//        graphics.setColor(13355962);
//    } else {
//        graphics.setColor(5655878);
//    }
//        graphics.setClip(5, 293, 39, 22);
//        graphics.fillRect(5, 293, 39, 22);
//        graphics.setClip(196, 293, 39, 22);
//        graphics.fillRect(196, 293, 39, 22);
//        Images.draw(graphics, this.yn_png, 0, 0, this.yn_png.getWidth(), this.yn_png.getHeight() >> 1, 0, 5, (320 - (this.yn_png.getHeight() >> 1)) - 5, 0);
//        Images.draw(graphics, this.yn_png, 0, this.yn_png.getHeight() >> 1, this.yn_png.getWidth(), this.yn_png.getHeight() >> 1, 0, (240 - this.yn_png.getWidth()) - 5, (320 - (this.yn_png.getHeight() >> 1)) - 5, 0);

}
