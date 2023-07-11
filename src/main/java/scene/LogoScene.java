package scene;

import iml.ColofulGame;
import iml.SpriteLoaderImpl;
import iml.Unkonw;
import io.github.humbleui.skija.Canvas;
import ui.ImageLoader;
import ui.Texture;

public class LogoScene extends BaseScene {


    private boolean finish;
    short[] pos;
    short[] f;
    Texture[] texture;
    int i = -1;


    public LogoScene() {
        loadResource();

        addTask(100, 100, () -> {
            i ++;


            return false;
        });

    }


    @Override
    protected void loadResource() {
        super.loadResource();
        SpriteLoaderImpl impl = new SpriteLoaderImpl();
        Unkonw m = new Unkonw(impl.loadSprite("/game.anu"), 0, 0);
        ColofulGame game = m.getColofulGame();

        pos = game.sprite.position;
        f = game.sprite.f;
        short[] coord = game.sprite.coord;


        texture = new Texture[25];
        for (int i = 0; i < 25; i++) {
            texture[i] = new Texture(ImageLoader.loadImage("1.png"), coord[i * 4], coord[i * 4+1], coord[i * 4+2], coord[i * 4+3]);
        }

    }


    @Override
    public void draw(Canvas canvas) {
        canvas.clear(0xFFFFFFFF);

        if(i < 0) return;
        if(i < f.length / 2) {
            int index = f[this.i << 1];
            short s = f[(this.i << 1) + 1];


            while (index < s) {

                int id = pos[index++];
                int x = pos[index++];
                int y = pos[index++];
                int zero = pos[index++];
                texture[id].draw(canvas, x, y);
            }
        } else {

            int index = f[f.length - 2];
            short s = f[f.length - 1];


            while (index < s) {

                int id = pos[index++];
                int x = pos[index++];
                int y = pos[index++];
                int zero = pos[index++];
                int offset = id << 2;

                texture[id].draw(canvas, x, y);
            }
            finish();

        }





    }

    @Override
    public void finish() {
        if(!finish) {
            finish = true;
            addTask(800, () -> {
                super.finish();
                return true;
            });
        }
    }
}



