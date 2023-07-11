package scene;

import animation.IntAnimation;
import io.github.humbleui.skija.Canvas;
import ui.Sprite;
import ui.Texture;
import util.Key;

public class MenuScene extends BaseScene implements Node.OnClickCallBack {


    private int cursor;


    private Texture background;
    private Texture[] smallMenuFont;
    private Texture[] bigMenuFont;

    private Sprite select;
    private Sprite[] menuItem;


    private OnMenuSelectCallBack callBack;



    private IntAnimation selectAnimation;



    public interface OnMenuSelectCallBack {
        void onSelect(int index);
    }



    public MenuScene() {
        loadResource();
    }

    public void setOnSelectCallback(OnMenuSelectCallBack callback) {
        this.callBack = callback;
    }


    @Override
    protected void loadResource() {
        background = new Texture("menuBg.png");
        smallMenuFont = new Texture("menuFont.png").spiltV(11);
        bigMenuFont = new Texture("bigMenuFont.png").spiltV(11);
        select = new Sprite("menuSelect.png");
        select.setInputEnable(false);

        selectAnimation = new IntAnimation(0, select.getWidth()) {
            @Override
            public void updateValue(int value) {
                select.setWidth(value);
            }
        };
        selectAnimation.setDuration(400);


        addNode(select);

        menuItem = new Sprite[11];

        for (int i = 0; i < menuItem.length; i++) {
            Sprite sprite = new Sprite();
            menuItem[i] = sprite;
            setDefault(i);

            sprite.setUserObject(i);
            sprite.setOnClickCallBack(this);
            addNode(sprite);
        }

        onSelectChange(-1, cursor);
    }

    public void setDefault(int index) {
        menuItem[index].setPosition(index % 2 == 0 ? 38 : 20, 16 + (index * 27));
        menuItem[index].setTexture(smallMenuFont[index]);
    }


    public void setSelect(int index) {
        menuItem[index].setPosition(index % 2 == 0 ? 33 : 15, 13 + (index * 27));
        menuItem[index].setTexture(bigMenuFont[index]);
    }




    @Override
    public void draw(Canvas canvas) {
        background.draw(canvas, 0,0 );
        super.draw(canvas);
    }


    @Override
    public void keyPressed(int key) {
        int oldCursor = cursor;
        if(key == Key.KEY_UP) {
            cursor --;
            if(cursor == -1) cursor = 10;
        } else if (key == Key.KEY_DOWN) {
            cursor ++;
            if(cursor == 11) cursor = 0;
        } else if(key == Key.KEY_OK) {
            fireOnSelect(cursor);
            return;
        }

        if(oldCursor != cursor) {
            onSelectChange(oldCursor, cursor);
        }


    }

    private void onSelectChange(int oldCursor, int cursor) {
        if (oldCursor >= 0) setDefault(oldCursor);
        setSelect(cursor);
        select.setPosition(cursor % 2 == 0 ? 36 : 18, 17 + (cursor * 27));
        selectAnimation.reset();
        selectAnimation.start();
        addAnimation(selectAnimation);
    }

    @Override
    public void onClick(Node node) {
        int oldCursor = this.cursor;
        cursor = (int) node.getUserObject();
        if(oldCursor != cursor) {
            System.out.println("old:" + oldCursor + " new" + cursor);
            onSelectChange(oldCursor, cursor);
        } else {
            System.out.println("gg");
            fireOnSelect(cursor);
        }

    }

    private void fireOnSelect(int index) {
        if(callBack != null) {
            callBack.onSelect(index);
        }
    }
}
