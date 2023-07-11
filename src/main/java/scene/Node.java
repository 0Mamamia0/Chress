package scene;

import animation.Animation;
import animation.AnimationCallback;
import animation.Animator;
import animation.IntAnimation;
import io.github.humbleui.skija.Canvas;
import ui.InputHandle;
import util.Time;

import java.util.ArrayList;
import java.util.List;

public class Node implements InputHandle {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    private boolean visible;
    private boolean showing;
    protected boolean touchEnable = true;
    protected boolean keyEnable = true;
    protected boolean inputEnable = true;


    private Group owner;
    private Object userObject;
    private OnClickCallBack onClickCallBack;


    private final IntAnimation move_x = new IntAnimation() {
        @Override
        public void updateValue(int value) {
            setX(value);
        }
    };
    private final IntAnimation move_y = new IntAnimation() {
        @Override
        public void updateValue(int value) {
            setY(value);
        }
    };

    public void setUserObject(Object obj) {
        this.userObject = obj;
    }

    public Object getUserObject() {
        return userObject;
    }




    public interface OnClickCallBack {
        void onClick(Node node);
    }


    public Node() {
        this(0,0);
    }


    public Node(int x, int y) {
        this(x, y, 0, 0);
    }

    public Node(int x, int y, int width, int height) {
        setBound(x, y, width, height);
        setVisible(true);
    }


    public void setOwner(Group owner) {
        this.owner = owner;
    }


    public Group getOwner() {
        return owner;
    }


    public boolean fireOnClick() {
        if(onClickCallBack != null) onClickCallBack.onClick(this);
        return onClickCallBack != null;
    }

    public void setOnClickCallBack(OnClickCallBack onClickCallBack) {
        this.onClickCallBack = onClickCallBack;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public void setHeight(int height) {
        this.height = height;
    }

    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setDimension(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setBound(int x, int y, int width, int height) {
        setPosition(x, y);
        setDimension(width, height);
    }



    public boolean inRange(int from, int to, int num) {
        return num >= from && num <= to;
    }
    public boolean inRange(float from, float to, float num) {
        return num >= from && num <= to;
    }


    @Override
    public boolean isTouchEnable() {
        return touchEnable;
    }

    @Override
    public void setTouchEnable(boolean enable) {
        this.touchEnable = enable;
    }




    public boolean adjustClick(int p_x, int p_y) {
        return inputEnable && touchEnable && visible && inRange(x, x + width, p_x) && inRange(y, y + height, p_y);
    }


    @Override
    public boolean pointerReleased(int x, int y) {
        return false;
    }

    @Override
    public boolean pointerPressed(int x, int y) {
        return fireOnClick();
    }

    @Override
    public boolean pointerDragged(int x, int y) {
        return false;
    }


    @Override
    public boolean isKeyEnable() {
        return keyEnable;
    }

    @Override
    public void setKeyEnable(boolean enable) {
        this.keyEnable = enable;
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyRelease(int key) {

    }

    @Override
    public void keyRepeated(int key) {

    }


    @Override
    public boolean isInputEnable() {
        return inputEnable;
    }

    @Override
    public void setInputEnable(boolean enable) {
        this.inputEnable = enable;
    }



    public void addAnimation(Animation animation) {
        Animator.getInstance().addAnimation(animation);
    }


    public void update() {

    }



    public void setVisible(boolean visible) {
        this.visible = visible;
        if(owner!= null && owner.isShowing()) {
            if(visible) {
                show();
            } else {
                hide();
            }
        }
    }

    public boolean isVisible() {
        return visible;
    }


    protected void show() {
        this.showing = true;
    }

    protected void hide() {
        this.showing = false;
    }

    public boolean isShowing() {
        return showing;
    }



    public void moveTo(int x, int y, long duration, AnimationCallback callback) {


        move_x.reset(this.x, x);
        move_x.setDuration(duration);
        move_x.setCallback(callback);

        move_y.reset(this.y, y);
        move_y.setDuration(duration);

        move_x.start();
        move_y.start();

        addAnimation(move_x);
        addAnimation(move_y);
    }

    public void moveTo(int x, int y, long duration) {
        this.moveTo(x, y, duration, null);
    }






    public void draw(Canvas canvas) {

    }


}
