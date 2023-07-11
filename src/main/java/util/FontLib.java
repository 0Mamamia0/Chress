package util;

import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Paint;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Hashtable;



public final class FontLib {
    private int b;
    private int c;
    private int row;
    private boolean e = true;


    private final Hashtable<Short, byte[]> charmap = new Hashtable<>();

    public FontLib(String str, boolean z) {
        loadFont(str);
    }


    private void loadFont(String text) {
        DataInputStream dataInputStream = new DataInputStream(getClass().getResourceAsStream(text));
        try {
            int count = dataInputStream.readShort();
            this.b = dataInputStream.readByte();
            this.c = this.b / 2;
            this.row = this.b / 2;
            for (int i = 0; i < count; i++) {
                short sh = dataInputStream.readShort();
                byte[] bArr = new byte[this.b];
                dataInputStream.read(bArr);
                this.charmap.put(sh, bArr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawFont(Canvas canvas, String str, int x, int y, int color) {
        if (str != null) {
            char[] text = str.toCharArray();
            for (char value : text) {
                int x1 = x;
                byte[] lattice =  this.charmap.get(new Short((short) value));
                if (lattice != null) {
                    try(Paint p = new Paint().setColor(0xFF000000 | color).setAntiAlias(true).setStroke(false).setStrokeWidth(1)) {
                        for (int i = 0; i < this.row; i++) {
                            for (int j = 0; j < 2; j++) {
                                for (int bit = 0; bit < 8; bit++) {
                                    if ((lattice[(i << 1) + j] & (0x80 >> bit)) != 0) {

                                        //16 X row => one char
                                        // j = 0   []        |  j =  1
                                        // 0 0 0 0 0 0 0 0 | 0 0 0 0 0 0 0 0
                                        // 0 0 0 0 0 0 0 0 | 0 0 0 0 0 0 0 0
                                        // 0 0 0 0 0 0 0 0 | 0 0 0 0 0 0 0 0
                                        // 0 0 0 0 0 0 0 0 | 0 0 0 0 0 0 0 0
                                        // 0 0 0 0 0 0 0 0 | 0 0 0 0 0 0 0 0
                                        // 0 0 0 0 0 0 0 0 | 0 0 0 0 0 0 0 0
                                        // 0 0 0 0 0 0 0 0 | 0 0 0 0 0 0 0 0
                                        // 0 0 0 0 0 0 0 0 | 0 0 0 0 0 0 0 0
                                        canvas.drawPoint(x1 + (j << 3) + bit, y + i, p);
                                    }
                                }
                            }
                        }
                    }
                }
                x += this.c;
            }


        }
    }
}