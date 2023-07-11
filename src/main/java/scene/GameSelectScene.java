package scene;

import io.github.humbleui.skija.Canvas;
import ui.Texture;

public class GameSelectScene extends BaseScene{


    private Texture background;
    private Texture menuBackground;
    private Texture scroll;

    public GameSelectScene() {
        loadResource();
    }


    @Override
    protected void loadResource() {
        background = new Texture("menuBg.png");
        menuBackground = new Texture("newChessBoard.png");
        scroll = new Texture("scroll.png");
        showCustomMenu();
    }


    @Override
    public void draw(Canvas canvas) {
        background.draw(canvas, 0, 0);
        menuBackground.draw(canvas, (240 - menuBackground.getWidth()) / 2, (320 - menuBackground.getHeight() - scroll.getHeight()) / 2);



        super.draw(canvas);
    }
}
