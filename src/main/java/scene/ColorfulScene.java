package scene;

import io.github.humbleui.skija.Canvas;
import ui.Sprite;
import ui.Texture;

public class ColorfulScene extends BaseScene {


    private static final long DELAY_COLORFUL_GAME = 2000;

    private Texture background;

    public ColorfulScene() {
        addTask(DELAY_COLORFUL_GAME, () -> {
           finish();
           return true;
        });
        loadResource();
    }


    protected void loadResource() {
        background = new Texture("colourfulgame.png");
    }


    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.clear(0xFFFF9000);
        background.draw(canvas, (240 - background.getWidth()) / 2, (320 - background.getHeight()) / 2);
    }
}
