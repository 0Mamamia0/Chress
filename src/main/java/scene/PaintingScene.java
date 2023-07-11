package scene;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.ClipMode;
import io.github.humbleui.skija.Image;
import io.github.humbleui.types.Rect;
import media.Sound;
import ui.ImageLoader;

public class PaintingScene extends BaseScene{


    private float shipX;
    private float speed = 0.5f;
    private float a;

    private boolean textVisible;
    private boolean moveLeft;

    private Image png_bg01;
    private Image png_bg02;
    private Image png_ship;
    private Image png_anykey;


    public PaintingScene() {
        addTask(0, 400, () -> {
            textVisible = !textVisible;
            return false;
        });

//        addTask(0, 10, () -> {
//            if(moveLeft) {
//                shipX--;
//                if (shipX < 10) moveLeft = false;
//            } else {
//                shipX++;
//                if (shipX > 100) moveLeft = true;
//            }
//            return false;
//        });
        loadResource();
    }


    @Override
    public void update() {
        super.update();
        if(moveLeft) {
            shipX -= speed ;
            if (shipX < 10) moveLeft = false;
        } else {
            shipX += speed;
            if (shipX > 100) moveLeft = true;
        }
    }

    @Override
    public void show() {
        super.show();
        Sound.play("menu.mid");
    }

    @Override
    public void hide() {
        super.hide();
        Sound.stop("menu.mid");
    }

    @Override
    protected void loadResource() {
        png_bg01 = ImageLoader.loadImage("bg01.png");
        png_bg02 = ImageLoader.loadImage("bg02.png");
        png_ship = ImageLoader.loadImage("ship.png");
        png_anykey = ImageLoader.loadImage("anykey.png");
    }


    @Override
    public void draw(Canvas canvas) {
        //mountain
        canvas.drawImage(png_bg01, 0, 0);
        //water
        drawRipple(canvas, png_bg02, false, png_bg02.getWidth(), png_bg02.getHeight(), 120, 220);
        //ship
        canvas.drawImageRect(png_ship, Rect.makeWH(png_ship.getWidth(), png_ship.getHeight() -2 ), Rect.makeXYWH(shipX, 228, png_ship.getWidth(), png_ship.getHeight() -2));
        //reflection
        drawRipple(canvas, png_ship, true, png_ship.getWidth(), png_ship.getHeight(), (int) (shipX + 34), 225 + png_ship.getHeight());

        if(textVisible) canvas.drawImage(png_anykey,  (240 - png_anykey.getWidth()) >> 1, 300 - png_anykey.getHeight());

    }





    private void drawRipple(Canvas canvas, Image image, boolean flip, int width, int height, int center_x, int center_y) {
        int i8 = width >> 3;
        int x = center_x - (width >> 1);
        int y = center_y;
        if (width < 0 || height < 0 ||  width > image.getWidth() || height > image.getHeight()) {
            return;
        }
        a+= 0.1f;
        for (int row = 0; row < height; row++) {
            int sin  = (int) (((i8 * row) * Math.sin(((i8 * (height - row)) / (row + 1)) + a )) / height);
            if (row + sin < 0) {
                sin = -row;
            } else if (row + sin >= height) {
                sin = (height - row) - 1;
            }
            canvas.save();
            canvas.clipRect(Rect.makeXYWH(x, center_y + row, width, 1), ClipMode.INTERSECT);

            canvas.drawImage(image, x, flip ? (center_y - height) + 1 + (row << 1) + sin : center_y-sin);
            canvas.restore();
//            canvas.setClip(x, center_y + row, i3, 1);
//            if (flip) {
//                canvas.drawImage(image, x, (center_y - height) + 1 + (row << 1) + sin);
//            } else {
//                canvas.drawImage(image, x ,center_y - sin);
//            }
        }

    }


    @Override
    public void keyPressed(int key) {
        finish();
    }


    @Override
    public boolean pointerPressed(int x, int y) {
        finish();
        return true;
    }
}
