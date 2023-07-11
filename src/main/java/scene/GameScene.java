package scene;

import animation.Animation;
import animation.AnimationCallback;
import animation.FrameAnimation;
import animation.IntAnimation;
import eleeye.Position;
import eleeye.Search;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Image;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.types.Rect;
import media.Sound;
import ui.ImageLoader;
import ui.Sprite;
import ui.Texture;
import util.Key;
import util.Time;
import util.Utils;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameScene extends BaseScene implements MenuSelectCallback{



    private int player;
    private int round;
    private int theme = 0;
    private long time;
    private int mod = 0;
    private int board_pos_index = 0;
    private int y_t = 10;
    private int frame_shall = 0;
    private int mv_current;
    private int mv_computer;

    private boolean inSelect;
    private boolean timeBlink;



    private Image png_mexico;
    private Image png_bigShall;
    private Image png_playing_menu;
    private Image png_smallShall;


    private Sprite think;
    private Sprite dialogEnd;
    private Cursor cursor;
    private Cursor cursor_select;
    private Tip link;


    private Group board;
    private GameMenu menu;

    private Search search;
    private Position position;
    private Piece pieceInSelect;


    private final Piece[][] pieces = new Piece[9][10];




    private byte[][] board_pos = {new byte[]{0, 0, 1, 0, -1}, new byte[]{0, -1, 0, 1}};
    private boolean shall_frame_end;


    ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    Future<Integer> future;


    private FrameAnimation checkFrame = new FrameAnimation(new long[] {100L, 100L}, new int[] { 1, 2}) {
        @Override
        public void updateValue(int frame) {

        }

    };





    private long thinkStartTime;
    private Sprite dialogEnd_bg;

    private Texture background;
    private Texture shou;
    private Texture[] msInformations;
    private Texture[] number;
    private Texture[] redNumber;




    public GameScene() {
        addTask(0, 100, ()-> {
            frame_shall ++;
            if(frame_shall > 14) {
                frame_shall = 0;
            }
            return shall_frame_end;
        });
        loadResource();
        loadGame();

    }

    private void loadGame() {
        position.setIrrev();
        this.player = position.sdPlayer;
    }

    @Override
    protected void loadResource() {

        background = new Texture("board.png");




        board = new Group();
        board.setBound(0, 26, 240, 320 - 26 * 2);



        Texture[] tex_select = new Texture("select.png").spiltH(6);
        cursor = new Cursor();
        cursor_select = new Cursor();

        cursor.setFrames(tex_select);
        cursor_select.setFrames(tex_select);

        cursor_select.setVisible(false);
        cursor.setBoardPos(7, 7);

        link = new Tip("lnk.png");




        png_playing_menu = ImageLoader.loadImage("playing_menu.png");
        png_mexico = ImageLoader.loadImage("mexico.png");
        png_bigShall = ImageLoader.loadImage("bigShall.png");
        png_smallShall = ImageLoader.loadImage("smallShall.png");

        this.shou = new Texture("shou.png");
        this.msInformations = new Texture("msInformation.png").spiltV(6);
        this.number = new Texture("number.png").spiltH(11);
        this.redNumber = new Texture("redNumber.png").spiltH(11);





        addNode(board);
        position = new Position();
        search = new Search(position, 4);
        Utils.loadCanJv(position, 2009, 10, 3);
        initBoard();






        showCustomMenu();
        checkFrame.start();
        checkFrame.setDuration(200);
        addAnimation(checkFrame);


    }

    @Override
    public void update() {
        super.update();

        if(isSearching()) {
            idleSearching();
        }
    }


    private void clearBoard() {
        this.board.clear();
        for (Piece[] pieces : pieces) {
            Arrays.fill(pieces, null);
        }
    }

    private void initBoard() {
        clearBoard();

        int pc = -1;
        for (int sq = 0; sq < 255; sq++) {
            if (Position.IN_BOARD(sq) && (pc =  position.squares[sq]) > 0) {
                Piece piece = new Piece(pc);
                int col = (Position.FILE_X(sq) - 3);
                int row = (Position.RANK_Y(sq) - 3);
                piece.setBoardPos(col, row);
                board.addNode(piece);
                pieces[col][row] = piece;
            }
        }
        board.addNode(cursor);
        board.addNode(cursor_select);
        board.addNode(link);
    }











    @Override
    public void draw(Canvas canvas) {

        //背景
        background.draw(canvas, 0, 0);

        //模式
        msInformations[0].draw(canvas, 8, 7);
        msInformations[mod].draw(canvas, 48, 7);

        //回合
        redNumber[round / 100].draw(canvas, 98, 9);
        redNumber[round / 10].draw(canvas, 110, 9);
        redNumber[round].draw(canvas, 122, 9);

        shou.draw(canvas, 135, 7);

        //时间
        msInformations[5].draw(canvas, 155, 7);
        number[Time.minute(time)].draw(canvas, 190, 9);
        number[10].draw(canvas, 200, 9);
        number[Time.ten_second(time)].draw(canvas, 210, 9);
        number[Time.second(time)].draw(canvas, 221, 9);


        if(mv_computer > 0) {
            int src_col = Position.FILE_X(Position.SRC(mv_computer)) - 3;
            int src_row = Position.RANK_Y(Position.SRC(mv_computer)) - 3;
            int dst_col = Position.FILE_X(Position.DST(mv_computer)) - 3;
            int dst_row = Position.RANK_Y(Position.DST(mv_computer)) - 3;

            int x_src = board.getX() + Board.PADDING + src_col * Board.CELL_LENGTH;
            int y_src = board.getY() + Board.PADDING + src_row * Board.CELL_LENGTH;

            int x_dst = board.getX() + Board.PADDING + dst_col * Board.CELL_LENGTH;
            int y_dst = board.getY() + Board.PADDING + dst_row * Board.CELL_LENGTH;

            try (Paint p = new Paint().setStroke(false).setStrokeWidth(2).setColor( 0xFF757DD4)) {
                canvas.drawArc( x_src - 4, y_src - 4, x_src + 4, y_src + 4 , 0, 360, true, p);
                canvas.drawLine(x_src,  y_src, x_dst, y_dst, p);
            }

        }
        super.draw(canvas);

    }





    private void drawShall(Canvas canvas) {
        canvas.drawImage(this.png_mexico, (240 - this.png_mexico.getWidth()) >> 1, (320 - this.png_mexico.getHeight()) >> 1);
        if(frame_shall <= 4 ) {
            return;
        } else if(frame_shall <= 9) {
            canvas.drawImage(this.png_bigShall, (240 - this.png_bigShall.getWidth()) >> 1, (320 - this.png_bigShall.getHeight()) >> 1);
        } else if(frame_shall <= 14) {
            canvas.drawImage(this.png_smallShall, (240 - this.png_smallShall.getWidth()) >> 1, (320 - this.png_smallShall.getHeight()) >> 1);
        }
        if(frame_shall == 14) {
            this.shall_frame_end = true;
        }
    }



    private void hideGameMenu() {
        if(menu != null) menu.setVisible(false);
    }


    private void showGameMenu() {
        if(menu == null) {
            menu = new GameMenu();
            menu.setSelectCallback(this);
            addNode(menu);
        }
        menu.setVisible(true);
    }








    public void cancelSelect() {
        this.inSelect = false;
        this.pieceInSelect = null;

        link.update(null);
    }

    public void setSelected(Piece piece) {
        this.inSelect = true;
        this.pieceInSelect = piece;

        int[] moves = new int[32];
        int num = position.generateMoves(moves, piece.getPc(), piece.getSq());

        if(num > 0) {
            int[] sq = new int[num];
            for (int i = 0; i < num;i++ ) {
                int s = Position.DST(moves[i]);
                sq[i] = position.squares[s] > 0 ? -s : s;
            }
            link.update(sq);
        }

    }

    private void onSelect(int col, int row) {

        Piece select = pieces[col][row];
        if(inSelect) {
            if(select != null && (select.getPc() & Position.SIDE_TAG(player)) != 0) {
                setSelected(select);
                System.out.println("选中: " + select.getName());
            } else {
                if(move(pieceInSelect, col, row)) {
                    if(position.inCheck()) {
                        if(select == null) {
                            System.out.println("[将]移动: " + pieceInSelect.getName() + " [" + col + ", " + row + "]");
                        } else {
                            System.out.println("[将]吃子: " + pieceInSelect.getName() + " => " + select.getName());
                        }
                    } else {
                        if(select == null) {
                            System.out.println("移动: " + pieceInSelect.getName() + " [" + col + ", " + row + "]");
                        } else {
                            System.out.println("吃子: " + pieceInSelect.getName() + " => " + select.getName());
                        }
                    }

                } else {
                    if(select == null) {
                        System.out.println("点击: [" + col + ", " + row + "]");
                    } else {
                        System.out.println("点击: " + select.getName());
                    }
                }
                cancelSelect();
            }
        } else {
            if(select != null) {
                if(isSelectable(select)) {
                    setSelected(pieces[col][row]);
                    System.out.println("选中: " + select.getName());
                } else {
                    System.out.println("点击: " + select.getName());
                }
            } else {
                System.out.println("点击: [" + col + ", " + row + "]");
            }
        }
    }

    private boolean isSelectable(Piece select) {
        return (select.getPc() & Position.SIDE_TAG(player)) != 0;
    }


    private void move(int mv) {

        int sq_src = Position.SRC(mv);
        int sq_dst = Position.DST(mv);

        int src_col = Position.FILE_X(sq_src) - 3;
        int src_row = Position.RANK_Y(sq_src) - 3;
        int dst_col = Position.FILE_X(sq_dst) - 3;
        int dst_row = Position.RANK_Y(sq_dst) - 3;


        if(tryMove(mv)) {
            Piece src = pieces[src_col][src_row];
            onMove(src, dst_col, dst_row);
            src.moveToBoardPos( dst_col, dst_row, new AnimationCallback(){
                @Override
                public void onAnimationEnd(Animation animation) {
                    Sound.play("walk.wav");
                    if(position.inCheck()) {

                    }
                }
            });
            this.mv_computer = mv;
            if (position.isMate()) {
               onGameEnd(false);
            }
        }
        this.player = position.sdPlayer;
    }

    private boolean move(Piece src, int col, int row) {

        int sqSrc = src.getSq();
        int sqDst = Position.COORD_XY(col + 3, row + 3);

        if(tryMove(Position.MOVE(sqSrc, sqDst))) {
            onMove(src, col, row);
            src.moveToBoardPos(col, row, new AnimationCallback(){
                @Override
                public void onAnimationEnd(Animation animation) {
                    Sound.play("walk.wav");
                    onMoveFinish();
                }
            });
            return true;
        };
        return false;
    }

    private void onMoveFinish() {

        if(position.inCheck()) {
            Sound.play("jiang.wav");
        }

        if (position.isMate()) {
            onGameEnd(true);
            executor.shutdown();
            return;
        }
        changeSide();

    }



    private void changeSide() {
        this.player = position.sdPlayer;
        onPCMove();
    }

    private void onPCMove() {
        beginSearch();
    }


    private void beginSearch() {

        if(think == null) {
            think = new Sprite("think.png");
            think.setPosition((240 - think.getWidth()) / 2, (320 - think.getHeight()) / 2 - 10);
            think.setVisible(false);
            this.addNode(think);
        }
        think.setVisible(true);

        future = executor.submit(() ->{
            long startTime = Time.now();
            int mv = search.searchMain(5, 1000);
            long passTime = Time.now() - startTime;
            if(passTime < 2000) {
                TimeUnit.MILLISECONDS.sleep(2000 - passTime);
            }
            return mv;
        });
        this.thinkStartTime = Time.now();
    }


    private boolean isSearching() {
        return future != null;
    }

    private void idleSearching() {
        if(future.isDone()) {
            endSearch();
        }
    }

    private void endSearch() {
        int mv = 0;
        try {
            mv = future.get();
        } catch (Exception ignored) { }

        if(mv > 0) {
            move(mv);
        }
        future = null;
        think.setVisible(false);
    }



    private boolean tryMove(int mv) {
        return position.legalMove(mv) && position.makeMove(mv);
    }

    private void onMove(Piece src, int col, int row) {
        if(pieces[col][row] != null) {
            board.removeNode(pieces[col][row]);
            pieces[col][row] = null;

        }
        pieces[src.getCol()][src.getRow()] = null;
        pieces[col][row] = src;
    }



    private void delPiece( Piece piece) {

    }



    private void onGameEnd(boolean win) {
        dialogEnd_bg = new Sprite("blackBg.png");
        dialogEnd_bg.setPosition(0, (320 - dialogEnd_bg.getHeight()) / 2 );
        dialogEnd_bg.setVisible(true);
        addNode(dialogEnd_bg);

        dialogEnd = win ? new Sprite("win.png") : new Sprite("lose.png");
        dialogEnd.setPosition(240, (320 - dialogEnd.getHeight()) / 2);
        dialogEnd.moveTo(40, dialogEnd.getY(), 1000);


        addNode(dialogEnd);
    }


    @Override
    protected void onRightSoftButton() {

    }

    @Override
    protected void onLeftSoftButton() {
        if(isMenuShowing()) {
            hideGameMenu();
        } else {
            showGameMenu();
        }
    }


    @Override
    public boolean pointerPressed(int x, int y) {
        boolean dispatch = false;
        if(isMenuShowing() && menu.adjustClick(x, y)) {
            dispatch = firePointerPressed(menu, x - menu.x, y - menu.y);
        }
        if(!dispatch && board.adjustClick(x, y)) {
            dispatch = onBoardPointerPressed(x - board.x, y - board.y);
            dispatch = true;
        }
        if(!dispatch) {
            return super.pointerPressed(x, y);
        }
        return true;
    }

    private boolean onBoardPointerPressed(int x, int y) {
        x -= Board.PADDING - Board.CELL_LENGTH_HALF;
        y -= Board.PADDING - Board.CELL_LENGTH_HALF;

        if(x < 0 || y < 0 || x >= Board.CELL_LENGTH * 9 || y >= Board.CELL_LENGTH * 10) return false;

        int col = x / Board.CELL_LENGTH;
        int row = y / Board.CELL_LENGTH;

        x = x % Board.CELL_LENGTH;
        y = y % Board.CELL_LENGTH;

        int o_1 = (Board.CELL_LENGTH - Piece.SIZE) / 2;
        int o_2 = o_1 + Piece.SIZE;
        if(inRange(o_1, o_2, x) && inRange(o_1, o_2, y) ) {
            cursor.setBoardPos(col, row);
            onSelect(col, row);
            return true;
        }
        return false;
    }


    @Override
    public void keyPressed(int key) {
        if(key == Key.KEY_L_SOFT) {
            onLeftSoftButton();
        } else if (key == Key.KEY_R_SOFT){
            onRightSoftButton();
        }

        if(isMenuShowing()) {
            menu.keyPressed(key);
            return;
        }
        int col_delta = 0;
        int row_delta = 0;
        switch (key) {
            case Key.KEY_LEFT: col_delta = -1; break;
            case Key.KEY_RIGHT: col_delta = 1; break;
            case Key.KEY_UP: row_delta = -1; break;
            case Key.KEY_DOWN: row_delta = 1; break;
            case Key.KEY_OK: keyOk(); return;
        }

        int col_to = cursor.getCol() + col_delta;
        int row_to = cursor.getRow() + row_delta;

        cursor.setBoardPos(col_to == -1 ? 9 : col_to == 10 ? 0 : col_to, row_to == -1 ? 8 : row_to == 9 ? 0 : row_to);

    }

    private void keyOk() {
//        int col = this.cursor_col;
//        int row = this.cursor_row;
//
//        onSelect(col, row);
    }



    public void resetGame() {
        Utils.loadCanJv(position, 2009, 10, 2);
        initBoard();
    }


    public void loadCanjv(int year, int month, int day) {
        Utils.loadCanJv(position, year, month, day);
        initBoard();
    }


    private void undoMovePiece() {
        if(position.moveNum > 1) {
            position.undoMakeMove();
            position.undoMakeMove();
            initBoard();
        }

    }



    @Override
    public void onSelect(GameMenu menu, int index) {
        switch (index) {
            case 0:
                hideGameMenu();
                break;
            case 1:
                undoMovePiece();
                hideGameMenu();
                break;
            case 2:
                resetGame();
                hideGameMenu();
                break;
            case 3:

            case 4:
                if(display != null) {
                    display.toMenuScene();
                }
                hideGameMenu();
                break;

        }

    }




    public boolean isMenuShowing() {
        return menu!= null && menu.isVisible();
    }


    private class Computer extends Thread {

        private boolean thinkRequest;

        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();

        public Computer() {
            setDaemon(true);
        }


        @Override
        public void run() {
            lock.lock();

            try {
                while (!Thread.interrupted()) {
                    try {
                        TimeUnit.MILLISECONDS.timedWait(lock, 1000);
                    } catch (InterruptedException ignored) {

                    }
                    if(thinkRequest) {
                        thinkRequest = false;
                        think();
                    }
                }

            } finally {
                lock.unlock();
            }

        }


        private void think() {
            Search s = GameScene.this.search;
            int mv = s.searchMain(5, 1000);
        }




        public  void startThinking() {
            try {
                if(lock.tryLock(10, TimeUnit.MILLISECONDS)) {
                    try {
                        thinkRequest = true;
                        condition.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
            } catch (InterruptedException ignored) {

            }
        }

        public void exit() {

        }


    }



}
