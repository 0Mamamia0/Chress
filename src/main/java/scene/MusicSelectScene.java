package scene;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.types.Rect;
import media.Sound;
import util.FontLib;
import util.Key;

public class MusicSelectScene extends BaseScene {

    private final FontLib font;



    Rect l_button = Rect.makeXYWH(0, 292, 40, 28);
    Rect r_button = Rect.makeXYWH(200, 292, 40, 28);
    Paint debug_paint = new Paint().setColor(0xFF00FF00).setStroke(true).setStrokeWidth(1);


    public MusicSelectScene() {
        font = new FontLib("/font.dat", true);
    }



    @Override
    public void draw(Canvas canvas) {
        this.font.drawFont(canvas,"－－是否开启音乐－－", 60, 150, -1);
        this.font.drawFont(canvas, "是", 5, 300, -1);
        this.font.drawFont(canvas, "否", 223, 300, -1);

        canvas.drawRect(l_button, debug_paint);
        canvas.drawRect(r_button, debug_paint);
    }

    @Override
    public void keyPressed(int key) {
        if(key == Key.KEY_L_SOFT) {
            Sound.setMute(false);
        } else if(key == Key.KEY_R_SOFT) {
            Sound.setMute(true);
        } else {
            return;
        }
        finish();
    }



    private boolean inRect(Rect rect, int x, int y) {
        return inRange(rect.getLeft(), rect.getRight(), x) && inRange(rect.getTop(), rect.getBottom(), y);
    }


    @Override
    public boolean pointerPressed(int x, int y) {
        if(inRect(l_button, x, y)) {
            Sound.setMute(false);
        } else if(inRect(r_button, x, y)) {
            Sound.setMute(true);
        }
        finish();
        return true;
    }
}
