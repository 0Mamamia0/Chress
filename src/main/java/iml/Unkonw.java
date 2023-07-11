package iml;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Unkonw {
    private int a;
    private int b;
    private boolean finished;
    private ColofulGame colofulGame;

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public void a(int i, int i2) {
        this.a += i;
        this.b += i2;
    }

    public void stop() {
        this.finished = true;
    }

    public Unkonw(Sprite sprite, int i, int i2) {
        this.colofulGame = new ColofulGame(sprite, this);
        this.colofulGame.a(0);
        this.a = 0;
        this.b = 0;
        this.finished = false;
        this.colofulGame.b(0);
    }

    public boolean finished() {
        if (this.finished) {
            this.finished = false;
            return true;
        }
        return false;
    }


    public ColofulGame getColofulGame() {
        return colofulGame;
    }

    //    /* renamed from: a */
    public void paint() {
        this.colofulGame.update();
        this.colofulGame.paint(null);
    }

    public Unkonw() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.DataInputStream] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r19v5, types: [int] */
    /* JADX WARN: Type inference failed for: r22v6, types: [int] */
    public static Sprite a(String str, boolean z, ImageLoader imageLoader, SpriteLoader spriteLoader) throws IOException {
        if (imageLoader == null) {
            throw new IllegalArgumentException("Image Loader cannot be null");
        }
        Sprite sprite = new Sprite(false);
        InputStream inputStream = Unkonw.class.getResourceAsStream(str);
        Objects.requireNonNull(inputStream);



        DataInputStream in = new DataInputStream(inputStream);

        short[] data1;
        short[] data2;
        short[] data3;
        short[] data4;
        short[] data5;
        short[] data6;

        try {
            try {
                in.readShort();
                in.readUTF();


                //
                byte len = in.readByte();
                data1 = new short[len * 2];
                for (int i = 0; i < len; i++) {
                    data1[i * 2] = in.readShort();
                    data1[(i * 2) + 1] = in.readShort();
                }

                //
                short lens = in.readShort();
                data2 = new short[lens * 4];
                for (int i = 0; i < lens; i++) {
                    data2[i * 4] = in.readShort();
                    data2[(i * 4) + 1] = in.readByte();
                    data2[(i * 4) + 2] = in.readShort();
                    data2[(i * 4) + 3] = in.readShort();
                }


                data3 = new short[in.readShort()];


                short len3 = in.readShort();

                data4 = new short[len3 * 2];

                short s = 0;
                for (int i = 0; i < len3; i++) {
                    data4[i * 2] = s;
                    short len4 = in.readShort();
                    for (short j = 0; j < len4; j++) {
                        short s3 = s;
                        short s4 = (short) (s3 + 1);
                        data3[s3] = in.readShort();
                        short s5 = (short) (s4 + 1);
                        data3[s4] = in.readShort();
                        short s6 = (short) (s5 + 1);
                        data3[s5] = in.readShort();
                        s = (short) (s6 + 1);
                        data3[s6] = in.readByte();
                    }
                    data4[(i * 2) + 1] = (short) (s - 1);
                }
                short readShort4 = in.readShort();
                int readByte2 = in.readByte();
                short[] sArr5 = new short[readShort4 << 2];
                short s7 = 0;
                short[] sArr6 = new short[readByte2];
                short s8 = 0;
                for (int i4 = 0; i4 < readByte2; i4++) {
                    sArr6[i4] = s8;
                    short readShort5 = in.readShort();
                    for (short s9 = 0; s9 < readShort5; s9++) {
                        short s10 = s7;
                        short s11 = (short) (s10 + 1);
                        sArr5[s10] = in.readShort();
                        short s12 = (short) (s11 + 1);
                        sArr5[s11] = in.readShort();
                        short s13 = (short) (s12 + 1);
                        sArr5[s12] = in.readShort();
                        s7 = (short) (s13 + 1);
                        sArr5[s13] = in.readShort();
                    }
                    s8 = (short) (s8 + readShort5);
                    sprite.images.addElement(imageLoader.loadImage(str, i4, spriteLoader));
                }
                short readShort6 = in.readShort();
                int[] iArr = new int[readShort6 * 5];
                for (int i5 = 0; i5 < readShort6; i5++) {
                    iArr[i5 * 5] = in.readShort();
                    iArr[(i5 * 5) + 1] = in.readShort();
                    iArr[(i5 * 5) + 2] = in.readShort();
                    iArr[(i5 * 5) + 3] = in.readShort();
                    iArr[(i5 * 5) + 4] = in.readInt();
                }
                short readShort7 = in.readShort();
                int[] iArr2 = new int[readShort7 * 3];
                for (int i6 = 0; i6 < readShort7; i6++) {
                    iArr2[i6 * 3] = in.readShort();
                    iArr2[(i6 * 3) + 1] = in.readShort();
                    iArr2[(i6 * 3) + 2] = in.readInt();
                }
                short readShort8 = in.readShort();
                int[] iArr3 = new int[readShort8 * 3];
                for (int i7 = 0; i7 < readShort8; i7++) {
                    iArr3[i7 * 3] = in.readShort();
                    iArr3[(i7 * 3) + 1] = in.readShort();
                    iArr3[(i7 * 3) + 2] = in.readInt();
                }
                short readShort9 = in.readShort();
                int[] iArr4 = new int[readShort9 * 5];
                for (int i8 = 0; i8 < readShort9; i8++) {
                    iArr4[i8 * 5] = in.readShort();
                    iArr4[(i8 * 5) + 1] = in.readShort();
                    iArr4[(i8 * 5) + 2] = in.readShort();
                    iArr4[(i8 * 5) + 3] = in.readShort();
                    iArr4[(i8 * 5) + 4] = in.readInt();
                }
                short readShort10 = in.readShort();
                short[] sArr7 = new short[readShort10 << 1];
                for (int i9 = 0; i9 < readShort10; i9++) {
                    sArr7[i9 * 2] = in.readShort();
                    sArr7[(i9 * 2) + 1] = in.readShort();
                }
                in.close();
                sprite.a = data1;
                sprite.b = data2;
                sprite.f = data4;
                sprite.position = data3;
                sprite.coord = sArr5;
                sprite.e = sArr7;
                return sprite;
            } catch (Exception e) {
                throw e;
            }
        } catch (Throwable th) {
            throw th;
        } finally {
            try {
                in.close();
            } catch (IOException ignored) {

            }
        }
    }
}