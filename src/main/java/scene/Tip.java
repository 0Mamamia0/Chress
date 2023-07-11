package scene;

import animation.FrameAnimation;
import eleeye.Position;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Image;
import io.github.humbleui.types.Rect;
import ui.ImageLoader;
import ui.Sprite;

public class Tip extends Sprite {


    private Image image = ImageLoader.loadImage("eat.png");
    private int frame;
    private int[] moves;

    private static final long[] race = new long[] { 250, 150, 250, 250};

    private final FrameAnimation animation = new FrameAnimation(race, null) {
        @Override
        public void updateValue(int frame) {
            Tip.this.frame = frame;
        }
    };

    public Tip(String name) {
        super(name);
    }

    @Override
    protected void show() {
        super.show();
        animation.start();
        addAnimation(animation);
    }

    @Override
    protected void hide() {
        super.hide();
        animation.reset();
    }

    public void update(int[] moves) {
        this.moves = moves;
    }


    @Override
    public void draw(Canvas canvas) {
        int w = getWidth() / 2;
        int h = getHeight();
        if(moves == null) return;

        for (int sq : moves) {
            boolean eat = sq < 0;
            sq = Math.abs(sq);
            int col = Position.FILE_X(sq) - 3;
            int row = Position.RANK_Y(sq) - 3;
            int x = Board.PADDING + (col * Board.CELL_LENGTH) - w / 2;
            int y = Board.PADDING + (row * Board.CELL_LENGTH) - h / 2;

            int x1 = Board.PADDING + (col * Board.CELL_LENGTH) - this.image.getWidth()/4 / 2;
            int y2 = Board.PADDING + (row * Board.CELL_LENGTH) -  this.image.getHeight()  / 2;

//            Image image = getImage();
//            Rect dst = Rect.makeXYWH(x, y, w, h);
//            Rect dst2 = Rect.makeXYWH(x1, y2, this.image.getWidth() /4.f, this.image.getHeight());
//
//            if(eat) {
//                switch (frame) {
//                    case 0:
//                        canvas.drawImageRect(this.image, Rect.makeXYWH(0, 0, this.image.getWidth() / 4.f, this.image.getHeight()), dst2);
//                        break;
//                    case 1:
//                        canvas.drawImageRect(this.image, Rect.makeXYWH(this.image.getWidth() / 4.f, 0, this.image.getWidth() / 4.f, this.image.getHeight()), dst2);
//                        break;
//                    case 2:
//                        canvas.drawImageRect(this.image, Rect.makeXYWH(2 * (this.image.getWidth() / 4.f), 0, this.image.getWidth() / 4.f, this.image.getHeight()), dst2);
//                        break;
//                    case 3:
//                        canvas.drawImageRect(this.image, Rect.makeXYWH(3 * (this.image.getWidth() /4.f), 0, this.image.getWidth() / 4.f, this.image.getHeight()), dst2);
//                        break;
//                }
//
//            } else {
//                switch (frame) {
//                    case 0:
//                        canvas.drawImageRect(image, Rect.makeXYWH(0, 0, w, h), dst);
//                        break;
//                    case 1:
//                        canvas.drawImageRect(image, Rect.makeXYWH(0, 0, w, h), dst);
//                    case 2:
//                        canvas.drawImageRect(image, Rect.makeXYWH(w, 0, w, h), dst);
//                        break;
//                    default:
//                        break;
//                }
//            }

        }


    }
}
