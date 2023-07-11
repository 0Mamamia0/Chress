package scene;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.types.Rect;
import media.Sound;
import ui.Texture;
import util.Score;

public class ScoreScene extends BaseScene{


    private final Paint paint = new Paint().setColor(0xFFFFFF00);

    private Texture background;
    private Texture[] level;
    private Texture[] levelNum;


    public ScoreScene() {
        loadResource();
        Score.setScore(Integer.MAX_VALUE);
    }

    @Override
    protected void loadResource() {
        this.background = new Texture("list.png");
        this.level = new Texture("level.png").spiltV(14);
        this.levelNum = new Texture("levelNum.png").spiltH(10);
    }

    @Override
    public void draw(Canvas canvas) {
        background.draw(canvas, 0, 0);
        level[Score.getLevel()].draw(canvas,109 ,139 );

        int score = Score.getScore();
        int max = Score.getMaxInLevel();

        if((score * 150000 / max / 1000) <= 150) {
            canvas.drawRect(Rect.makeXYWH(45, 185, (score * 150000.f) / max / 1000.f, 9), paint);
        } else {
            canvas.drawRect(Rect.makeXYWH(45, 185, 150, 9), paint);
        }


        levelNum[score / 1000 % 10].draw(canvas,50 , 220 );
        levelNum[score / 100 % 10].draw(canvas,50 + levelNum[0].getWidth() , 220 );
        levelNum[score / 10 % 10].draw(canvas,50 + 2 * (levelNum[0].getWidth()) , 220 );
        levelNum[score % 10].draw(canvas,50 + 3 * (levelNum[0].getWidth()) , 220 );

        levelNum[max / 1000 % 10].draw(canvas,125 , 220 );
        levelNum[max / 100 % 10].draw(canvas,125 + levelNum[0].getWidth() , 220 );
        levelNum[max / 10 % 10].draw(canvas,125 + 2 * (levelNum[0].getWidth()) , 220 );
        levelNum[max % 10].draw(canvas,125 + 3 * (levelNum[0].getWidth()) , 220 );
    }
}
