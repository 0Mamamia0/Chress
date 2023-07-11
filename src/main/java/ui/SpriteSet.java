package ui;

import io.github.humbleui.skija.Canvas;
import scene.Group;
import scene.Node;

import java.util.List;
import java.util.Objects;

public class SpriteSet extends Node {



    private int currentIndex;
    private Sprite currentFrame;
    private final Sprite[] frames;



    public SpriteSet(Sprite[] frames) {
        Objects.requireNonNull(frames);
        this.frames = frames;
    }


    public void setFrame(int index) {
        this.currentFrame = frames[index];
    }

    public void nextFrame() {
        if(currentIndex == frames.length - 1) currentIndex = 0;
        setFrame(currentIndex);
    }


    public void previousFrame() {
        if(currentIndex == 0) currentIndex = frames.length - 1;
        setFrame(currentIndex);
    }


    public Sprite getFrame() {
        return currentFrame;
    }

    public Sprite getFrame(int index) {
        return frames[index];
    }


    public int getFameSize() {
        return frames.length;
    }


    @Override
    public void draw(Canvas canvas) {
        currentFrame.draw(canvas);
    }
}
