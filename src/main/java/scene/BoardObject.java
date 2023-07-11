package scene;

import io.github.humbleui.skija.Image;
import ui.Sprite;

public class BoardObject extends Sprite {




    protected int col;
    protected int row;

    public BoardObject() {

    }

    public BoardObject(String name) {
        super(name);
    }

    public BoardObject(Sprite sprite) {
        super(sprite);
    }


    public BoardObject(Image image, int frame_x, int frame_y, int frame_width, int frame_height) {
        super(image, 0, 0, frame_width, frame_height);
    }


    public void setBoardPos(int col, int row) {
        this.col = col;
        this.row = row;
        setPosition(Board.PADDING + (col * Board.CELL_LENGTH) - getWidth() / 2, Board.PADDING + (row * Board.CELL_LENGTH) - getHeight() / 2);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }


    public int getAbsCol() {
        return col + 3;
    }

    public int getAbsRow() {
        return row + 3;
    }


}
