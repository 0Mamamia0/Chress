package scene;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Image;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.types.RRect;
import io.github.humbleui.types.Rect;
import ui.Display;
import ui.ImageLoader;
import ui.Sprite;
import ui.Texture;
import util.CalendarController;
import util.FontLib;
import util.Key;
import util.Resource;

public class CalendarScene extends BaseScene {

    public static final int SELECT_MODE_MONTH = 1;
    public static final int SELECT_MODE_DAY = 2;


    private static final int[][] date_position = {
            {26, 106}, {57, 106}, {88, 106}, {119, 106}, {150, 106}, {181, 106},
            {212, 106}, {26, 130}, {57, 130}, {88, 130}, {119, 130}, {150, 130},
            {181, 130}, {212, 130}, {26, 154}, {57, 154}, {88, 154}, {119, 154},
            {150, 154}, {181, 154}, {212, 154}, {26, 178}, {57, 178}, {88, 178},
            {119, 178}, {150, 178}, {181, 178}, {212, 178}, {26, 202}, {57, 202},
            {88, 202}, {119, 202}, {150, 202}, {181, 202}, {212, 202}, {26, 226},
            {57, 226}, {88, 226}, {119, 226}, {150, 226}, {181, 226}, {212, 226}
    };

//    private float fontWidth;
//    private float fontHeight;

    private int selectMode;
    private boolean cursorVisible;

    private Sprite finger;

    private Texture background;
    private Texture[] numberFont;
    private Texture[] monthsNumberFont;


    private FontLib font = new FontLib("/font.dat", true);



    private final CalendarController calendar;


    public CalendarScene () {
        calendar = new CalendarController();

        //cursor blinking
        addTask(0, 500, () -> {
            cursorVisible = !cursorVisible;
            return false;
        });
        loadResource();
    }


    @Override
    protected void loadResource() {

        background = new Texture("calendar.png");
        numberFont = new Texture("number.png").spiltH(11);
        monthsNumberFont = new Texture("monthsNum.png").spiltH(12);

        finger = new Sprite("finger.png");
        finger.setPosition(0, 0);
        addNode(finger);


//        fontWidth = (float) png_number.getWidth() / 11;
//        fontHeight = png_number.getHeight();

        showCustomMenu();
    }







    @Override
    public void draw(Canvas canvas) {
        background.draw(canvas, 0, 0);
        drawDate(canvas);
        drawInfo(canvas);
        drawDialog(canvas);
        super.draw(canvas);
    }




    private void drawDate(Canvas canvas) {

        //year
        numberFont[2].draw(canvas, 95, 45);
        numberFont[0].draw(canvas, 105, 45);

        if (calendar.getYear() == 2009) {
            numberFont[0].draw(canvas, 115, 45);
            numberFont[9].draw(canvas, 125, 45);
        } else {
            numberFont[1].draw(canvas, 115, 45);
            numberFont[0].draw(canvas, 125, 45);
        }

        //month
        monthsNumberFont[calendar.getMonth() - 1].draw(canvas, 160, 43);



        //day
        int offset = calendar.getDayOffsetOfMonth();
        int dayOfMonth = calendar.getDayOfMonth();
        for(int i = 1; i <= dayOfMonth; i ++ ) {
            int index =  i - 1 + offset;
            int row = index / 7;
            int col = index % 7;

            int x = 17 + col * 31 + 10;
            int y = row * 24 + 102;

            int ten = i / 10;
            int one = i % 10;

            numberFont[ten].draw(canvas, x - 10, y);
            numberFont[one].draw(canvas, x , y);




        }





    }



    private void drawInfo(Canvas canvas) {
        //cursor
        if(cursorVisible && selectMode == 1) {
            try(Paint p = new Paint().setColor(0xFF808080).setStroke(false)) {
                canvas.drawRect(Rect.makeXYWH(166, 60, 20, 2) ,p);
            }
        }




        int level = 12;

        this.font.drawFont(canvas,"名称：", 20, 255, 0);
        this.font.drawFont(canvas,Resource.level_names[level], 60, 255, 0);
        this.font.drawFont(canvas,"难度：", 152, 255, 0);
        if (Resource.level_lv[level] == 1) {
            this.font.drawFont(canvas, "初级", 190, 255, 0 );
        } else if (Resource.level_lv[level] == 2) {
            this.font.drawFont(canvas, "中级", 190, 255, 0 );
        } else if (Resource.level_lv[level] == 3) {
            this.font.drawFont(canvas, "高级", 190, 255, 0 );
        }

    }


    private void drawDialog(Canvas canvas) {

        if(selectMode == 0) {
            try (Paint p = new Paint().setColor(0xFF000000)){
                canvas.drawRRect(RRect.makeXYWH(40, 70, 160, 160, 20, 20), p);
            }
            for (int i = 0; i < 6; i++) {
                this.font.drawFont(canvas, Resource.text_canjv[i], 48, 90 + (i * 20), 0xFFFFFFFF);
            }
        }
    }



    private void updateFingerPosition(){
        int offset = calendar.getDayOffsetOfMonth() + calendar.getDay() - 1;
        System.out.println(calendar.getDay());
        System.out.println(calendar.getDayOffsetOfMonth());
        finger.setPosition(date_position[offset][0], date_position[offset][1]);
    }



    @Override
    public void keyPressed(int key) {
        int mode = this.selectMode;

        switch (key) {
            case Key.KEY_OK:
                if(mode == SELECT_MODE_DAY) {
                    Game.loadCanjvGame(calendar.getYear(), calendar.getMonth(), calendar.getDay());
                }
                break;

            case Key.KEY_L_SOFT:
                if(mode == 0) {
                    this.selectMode = SELECT_MODE_MONTH;
                } else if (selectMode == SELECT_MODE_MONTH) {
                    this.selectMode = SELECT_MODE_DAY;
                }
                break;
            case Key.KEY_R_SOFT:
                if(mode == SELECT_MODE_MONTH) {
                    this.selectMode = 0;
                } else if (selectMode == SELECT_MODE_DAY) {
                    this.selectMode = SELECT_MODE_MONTH;
                }
                break;

            case Key.KEY_LEFT:
                if(mode == SELECT_MODE_MONTH) {
                    calendar.previousMonth();
                } else if(mode == SELECT_MODE_DAY) {
                    calendar.left();
                    updateFingerPosition();
                }
                break;
            case Key.KEY_RIGHT:
                if(mode == SELECT_MODE_MONTH) {
                    calendar.nextMonth();
                } else if(mode == SELECT_MODE_DAY) {
                    calendar.right();
                    updateFingerPosition();
                }
                break;
            case Key.KEY_UP:
                if(mode == SELECT_MODE_MONTH) {
                    calendar.previousMonth();
                } else if(mode == SELECT_MODE_DAY) {
                    calendar.up();
                    updateFingerPosition();
                }
                break;
            case Key.KEY_DOWN:
                if(mode == SELECT_MODE_MONTH) {
                    calendar.nextMonth();
                } else if(mode == SELECT_MODE_DAY) {
                    calendar.down();
                    updateFingerPosition();
                }
                break;
            default:
                break;
        }


    }

    @Override
    public void keyRepeated(int key) {
        keyPressed(key);
    }


    @Override
    public boolean pointerPressed(int x, int y) {
//        int mod = this.selectMode;
//        switch (mod) {
//            case 0:
//                this.selectMode = SELECT_MODE_MONTH;
//                break;
//
//        }

        return super.pointerPressed(x, y);



    }


    @Override
    protected void onRightSoftButton() {
        Display.getDefaultDisplay().backScene();
    }


    @Override
    protected void onLeftSoftButton() {
        super.onLeftSoftButton();
    }
}
