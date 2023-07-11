package scene;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Image;
import io.github.humbleui.types.Rect;
import ui.ImageLoader;
import util.Key;

public class ChessRemnantsSelectScene extends BaseScene{

    private int cursor = 1;

    private Image png_menuBg;
    private Image png_tryFont;
    private Image png_bamboo;
    private Image png_bambooFont;


    public ChessRemnantsSelectScene() {
        loadResource();
    }

    @Override
    protected void loadResource() {
        super.loadResource();
        png_menuBg = ImageLoader.loadImage("menuBg.png");
        png_tryFont = ImageLoader.loadImage("tryFont.png");
        png_bamboo = ImageLoader.loadImage("bamboo.png");
        png_bambooFont = ImageLoader.loadImage("bambooFont.png");

    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawImage(png_menuBg, 0, 0);
        canvas.drawImage(png_tryFont, (240 - this.png_tryFont.getWidth()) >> 1, 34);


        int bamboo_width = png_bamboo.getWidth();
        int width = png_bambooFont.getWidth() / 6;
        int height = png_bambooFont.getHeight();

        for (int i = 0; i < 8; i++) {
            float y = i == cursor ? 80 : 108;
            canvas.drawImage(png_bamboo, (i * bamboo_width) + 23, y);
        }


        for (int i = 1; i < 7; i++) {
            float y = i == cursor ? 110 : 139;
            Rect src = Rect.makeXYWH((i -1) * width, 0, width , height );
            Rect dst = Rect.makeXYWH(47 + (i - 1) * bamboo_width + 0.5f, y, width, height);
            canvas.drawImageRect(png_bambooFont, src, dst);
        }

        //drawMenuButton(canvas);


    }


    @Override
    public void keyPressed(int key) {
        if(key == Key.KEY_LEFT) {
            if(cursor != 1) cursor --;
        } else if(key ==Key.KEY_RIGHT) {
            if (cursor != 6) cursor ++;
        }
    }
}
