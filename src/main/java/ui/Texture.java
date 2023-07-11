package ui;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Image;
import io.github.humbleui.types.Rect;

public class Texture {


    public static final int FILIP_X = 1;
    private Image image;

    private int x;
    private int y;
    private int width;
    private int height;



    public Texture(String name) {
        this(ImageLoader.loadImage(name));
    }


    public Texture(Image image) {
        this(image, image.getWidth(), image.getHeight());
    }

    public Texture(Image image, int width, int height) {
        this(image, 0, 0, width, height);
    }


    public Texture(Image image, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }


    public Texture[] spiltH(int block) {
        Texture[] textures = new Texture[block];
        int w = this.width / block;
        for (int i = 0; i < textures.length; i++) {
            textures[i] = new Texture(this.image, i * w + this.x,  this.y, w , this.height);
        }
        return textures;
    }

    public Texture[] spiltV(int block) {
        Texture[] textures = new Texture[block];
        int h = this.height / block;
        for (int i = 0; i < textures.length; i++) {
            textures[i] = new Texture(this.image, this.x,  i * h + this.y, this.width, h);
        }
        return textures;
    }

    public Texture[][] spiltHV(int col, int row) {
        Texture[][] res = new Texture[col][row];
        int w = this.width / col;
        int h = this.height / row;

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                res[i][j] = new Texture(this.image, i * w + this.x, j * h + this.y, w, h);
            }
        }
        return res;
    }




    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Canvas canvas, int x, int y) {
        draw(canvas, x, y, this.width, this.height);
    }

    public void draw(Canvas canvas, int x, int y, int filip) {
        draw(canvas, x, y, this.width, this.height, filip);
    }

    public void draw(Canvas canvas, int x, int y, int width, int height) {
        canvas.drawImageRect(image, Rect.makeXYWH(this.x, this.y, width, height), Rect.makeXYWH(x, y, width, height));
    }

    public void draw(Canvas canvas, int x, int y, int width, int height, int filip) {
        if(filip == FILIP_X) {
            canvas.save();
            canvas.translate( x + width, y);
            canvas.scale(-1, 1);
            canvas.drawImageRect(image, Rect.makeXYWH(this.x, this.y, width, height), Rect.makeWH(width, height));
            canvas.restore();
        }

    }
}
