package ui;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Image;
import io.github.humbleui.types.Rect;

public class SpriteSheet {

    private int count;
    private int width;
    private int height;
    private final Image image;



    public SpriteSheet(String name) {
        image = ImageLoader.loadImage(name);
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public SpriteSheet(String name, int count) {
        image = ImageLoader.loadImage(name);
        if(count > 0) {
            width = image.getWidth() / count;
            height = image.getHeight();
        } else if(count < 0) {
            width = image.getWidth();
            height = image.getHeight();
        }
        this.count = count;
    }



    public void draw(Canvas canvas,int x, int y, int index) {
        Rect dst = Rect.makeXYWH(x, y, width, height);
        if(count > 0) {
            canvas.drawImageRect(image, Rect.makeXYWH(index * width, 0, width, height) , dst);
        } else if(count < 0) {
            canvas.drawImageRect(image, Rect.makeXYWH(0, index * height, width, height) , dst);
        }
    }



    public void spiltV(int block) {

    }



    public void spiltH(int block) {

    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
