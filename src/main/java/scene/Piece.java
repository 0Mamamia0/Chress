package scene;

import animation.AnimationCallback;
import eleeye.Position;
import io.github.humbleui.skija.Image;
import ui.ImageLoader;
import ui.Sprite;
import ui.Texture;

public class Piece extends BoardObject {

    public static final int  SIZE = 22;


    private int pc;

    private static final Texture[][] pieces = new Texture("piece.png").spiltHV(7, 2);






    public Piece(int pc) {
        this.pc = pc;
        setTexture(pc >= 16 ? pieces[pc-16][1] : pieces[pc-8][0]);
    }




    public int getPc() {
        return pc;
    }

    public int getSq() {
        return Position.COORD_XY(getCol() + 3, getRow() + 3);
    }

    public String getName() {
        switch (pc) {
            case 8: return "帅";
            case 9: return "士";
            case 10: return "相";
            case 11: return "马";
            case 12: return "车";
            case 13: return "炮";
            case 14: return "兵";

            case 16: return "将";
            case 17: return "士";
            case 18: return "象";
            case 19: return "马";
            case 20: return "车";
            case 21: return "炮";
            case 22: return "卒";

            default: return "";
        }

    }


    public void moveToBoardPos(int col, int row) {
        this.moveToBoardPos(col, row, null);
    }

    public void moveToBoardPos(int col, int row, AnimationCallback callback) {

        long duration = 0;


        if(this.col == col) {
            duration = Math.abs(this.row - row) * 100L;
        } else if(this.row == row) {
            duration = Math.abs(this.col - col) * 100L;
        } else {
            int a = Math.abs(this.row - row);
            int b = Math.abs(this.col - col);

            duration = a > b ? a * 100L : b * 100L;
        }

        this.col = col;
        this.row = row;


        super.moveTo(Board.PADDING + (col * Board.CELL_LENGTH) - getWidth() / 2, Board.PADDING + (row * Board.CELL_LENGTH) - getHeight() / 2, duration, callback);
    }
}
