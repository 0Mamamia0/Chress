package scene;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Codec;
import io.github.humbleui.skija.Data;
import io.github.humbleui.skija.Image;
import ui.Display;
import ui.ImageLoader;

public class ColorfulScene extends Scene {


    private static final long DELAY_COLORFUL_GAME = 2000;

    private Image png_bg;

    private boolean show = false;
    private long show_time = 0;

    public ColorfulScene() {
        loadResource();
    }


    private void loadResource() {
        png_bg = ImageLoader.loadImage("/images/colourfulgame.png");
    }


    @Override
    public void update() {
        if(!show) {
            show = true;
            show_time = System.currentTimeMillis();
        } else {
            long pass = System.currentTimeMillis() - show_time;

            if(pass > DELAY_COLORFUL_GAME) {
                Display.getDefaultDisplay().setScene(new MusicSelectScene());
            }


        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.clear(0xFFFF9000);
        canvas.drawImage(png_bg, (240 - png_bg.getWidth()) >> 1, (320 - png_bg.getHeight()) >> 1);
    }
}
