package ui;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Image;
import io.github.humbleui.types.Rect;
import scene.Node;

public class Sprite extends Node {

    private int frameIndex;
    private Texture texture;
    private Texture[] frame;

    public Sprite() {

    }

    public Sprite(String name) {
        this(new Texture(name));
    }

    public Sprite(Image image, int frame_x, int frame_y, int frame_width, int frame_height) {
        this(new Texture(image, frame_x, frame_y, frame_width, frame_height));
    }

    public Sprite(Sprite other) {
        this(other.getTexture());
    }

    public Sprite(Texture texture) {
        super(0, 0, texture.getWidth(), texture.getHeight());
        this.texture = texture;
    }



    public void draw(Canvas canvas) {
        if(texture != null) {
            this.texture.draw(canvas, 0, 0, width, height);
        }
    }




    public void draw(Canvas canvas, int x, int y, int width, int height) {

    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
        setDimension(texture.getWidth(), texture.getHeight());
    }


    public void setFrame(int index) {
        this.frameIndex = index;
        setTexture(frame[index]);
    }

    public void setFrames(Texture[] frame) {
        this.frame = frame;
    }

    public int getFrameIndex() {
        return frameIndex;
    }
}
