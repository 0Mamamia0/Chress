package scene;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.types.Rect;
import media.Sound;
import ui.Texture;
import ui.Theme;
import util.Key;

public class SettingScene extends BaseScene {

    private int cursor;
    private boolean enableTip;

    private Texture arrow;
    private Texture background;
    private Texture titleFont;
    private Texture volumeLevel;
    private Texture[] itemFont;
    private Texture[] styleFont;
    private Texture[] switchFont;



    public SettingScene() {
        loadResource();
    }


    @Override
    protected void loadResource() {
        super.loadResource();
        this.background = new Texture("menuBg.png");
        this.titleFont = new Texture("gameSetSign.png");
        this.volumeLevel = new Texture("music.png");
        this.arrow = new Texture("letSign.png");
        this.itemFont = new Texture("gameSetFont.png").spiltV(3);
        this.switchFont = new Texture("open_close.png").spiltH(2);
        this.styleFont = new Texture("styleFont.png").spiltV(2);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        background.draw(canvas, 0, 0);
        titleFont.draw(canvas, 46, 38);

        arrow.draw(canvas, 114, 128 + (34 * cursor));
        arrow.draw(canvas, 215, 128 + (34 * cursor), Texture.FILIP_X);

        int level = Sound.getVolumeLevel();
        if(level > 0) {
            volumeLevel.draw(canvas, 132, 109, level * volumeLevel.getWidth() / 5, volumeLevel.getHeight());
        } else {
            switchFont[1].draw(canvas, 161, 122);
        }


        canvas.drawRect(Rect.makeXYWH(114, 128, arrow.getWidth(), arrow.getHeight()), new Paint().setColor(0xFF00FF00).setStroke(true));

        switchFont[enableTip ? 0 : 1].draw(canvas, 161, 156);
        styleFont[Theme.getStyle()].draw(canvas, 131, 190);

        for (int i = 0; i < 3; i++) {
            itemFont[i].draw(canvas,  29, 120 + (i * 36));
        }

//        Images.draw(graphics, this.letSign_png, 0, 0, this.letSign_png.getWidth(), this.letSign_png.getHeight(), 0, 114, 128 + (34 * this.aU), 0);
//        Images.draw(graphics, this.letSign_png, 0, 0, this.letSign_png.getWidth(), this.letSign_png.getHeight(), 2, 215, 128 + (34 * this.aU), 0);

    }


    @Override
    public boolean pointerPressed(int x, int y) {
//
//        if(inRange(110, 118 + arrow.getWidth() , x)
//                && (inRange(124, 132 + arrow.getHeight(), y)
//                || inRange(124 + 34, 132 + 34 + arrow.getHeight(), y)
//                || inRange(124 + (34 * 2), 132 + (34 * 2) + arrow.getHeight(), y))) {
//            doLeft();
//        }
        return false;
    }

    @Override
    public void keyPressed(int key) {
        switch (key) {
            case Key.KEY_UP:
                cursor --;
                if(cursor == -1) {
                    cursor = 2;
                }
                break;
            case Key.KEY_DOWN:
                cursor ++;
                if(cursor == 3) {
                    cursor = 0;
                }
                break;
            case Key.KEY_LEFT:
                doLeft();
                break;
            case Key.KEY_RIGHT:
                doRight();
                break;
        }
    }

    private void doRight() {
        switch (cursor) {
            case 0:
                int level = Sound.getVolumeLevel();
                if(level < 5) {
                    level ++;
                    Sound.setVolumeLevel(level);
                }
                break;
            case 1:
                this.enableTip = true;
                break;
            case 2:
                Theme.setStyle(1 - Theme.getStyle());
                break;
        }

    }

    private void doLeft() {
        switch (cursor) {
            case 0:
                int level = Sound.getVolumeLevel();
                if(level > 0) {
                    level --;
                    Sound.setVolumeLevel(level);
                }
                break;
            case 1:
                this.enableTip = false;
                break;
            case 2:
                Theme.setStyle(1 - Theme.getStyle());
                break;
        }
    }
}
