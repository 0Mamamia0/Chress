package iml;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class a {
    private int a;
    private int b;
    private boolean c;
    private ah d;

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

    public void c() {
        this.c = true;
    }

    public a(Sprite sprite, int i, int i2) {
        this.d = new ah(sprite, this);
        this.d.a(0);
        this.a = 0;
        this.b = 0;
        this.c = false;
        this.d.b(0);
    }

    public boolean d() {
        if (this.c) {
            this.c = false;
            return true;
        }
        return false;
    }

//    /* renamed from: a */
//    public void paint(Graphics graphics) {
//        this.d.a();
//        this.d.a(graphics);
//    }

    public a() {
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
        InputStream inputStream = a.class.getResourceAsStream(str);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            try {
                dataInputStream.readShort();
                dataInputStream.readUTF();
                byte readByte = dataInputStream.readByte();
                short[] sArr = new short[readByte << 1];
                for (int i = 0; i < readByte; i++) {
                    sArr[i * 2] = dataInputStream.readShort();
                    sArr[(i * 2) + 1] = dataInputStream.readShort();
                }
                short readShort = dataInputStream.readShort();
                short[] sArr2 = new short[readShort << 2];
                for (int i2 = 0; i2 < readShort; i2++) {
                    sArr2[i2 * 4] = dataInputStream.readShort();
                    sArr2[(i2 * 4) + 1] = dataInputStream.readByte();
                    sArr2[(i2 * 4) + 2] = dataInputStream.readShort();
                    sArr2[(i2 * 4) + 3] = dataInputStream.readShort();
                }
                short[] sArr3 = new short[dataInputStream.readShort()];
                short readShort2 = dataInputStream.readShort();
                short s = 0;
                short[] sArr4 = new short[readShort2 << 1];
                for (int i3 = 0; i3 < readShort2; i3++) {
                    sArr4[i3 * 2] = s;
                    short readShort3 = dataInputStream.readShort();
                    for (short s2 = 0; s2 < readShort3; s2++) {
                        short s3 = s;
                        short s4 = (short) (s3 + 1);
                        sArr3[s3] = dataInputStream.readShort();
                        short s5 = (short) (s4 + 1);
                        sArr3[s4] = dataInputStream.readShort();
                        short s6 = (short) (s5 + 1);
                        sArr3[s5] = dataInputStream.readShort();
                        s = (short) (s6 + 1);
                        sArr3[s6] = dataInputStream.readByte();
                    }
                    sArr4[(i3 * 2) + 1] = (short) (s - 1);
                }
                short readShort4 = dataInputStream.readShort();
                int readByte2 = dataInputStream.readByte();
                short[] sArr5 = new short[readShort4 << 2];
                short s7 = 0;
                short[] sArr6 = new short[readByte2];
                short s8 = 0;
                for (int i4 = 0; i4 < readByte2; i4++) {
                    sArr6[i4] = s8;
                    short readShort5 = dataInputStream.readShort();
                    for (short s9 = 0; s9 < readShort5; s9++) {
                        short s10 = s7;
                        short s11 = (short) (s10 + 1);
                        sArr5[s10] = dataInputStream.readShort();
                        short s12 = (short) (s11 + 1);
                        sArr5[s11] = dataInputStream.readShort();
                        short s13 = (short) (s12 + 1);
                        sArr5[s12] = dataInputStream.readShort();
                        s7 = (short) (s13 + 1);
                        sArr5[s13] = dataInputStream.readShort();
                    }
                    s8 = (short) (s8 + readShort5);
                    sprite.g.addElement(imageLoader.loadImage(str, i4, spriteLoader));
                }
                short readShort6 = dataInputStream.readShort();
                int[] iArr = new int[readShort6 * 5];
                for (int i5 = 0; i5 < readShort6; i5++) {
                    iArr[i5 * 5] = dataInputStream.readShort();
                    iArr[(i5 * 5) + 1] = dataInputStream.readShort();
                    iArr[(i5 * 5) + 2] = dataInputStream.readShort();
                    iArr[(i5 * 5) + 3] = dataInputStream.readShort();
                    iArr[(i5 * 5) + 4] = dataInputStream.readInt();
                }
                short readShort7 = dataInputStream.readShort();
                int[] iArr2 = new int[readShort7 * 3];
                for (int i6 = 0; i6 < readShort7; i6++) {
                    iArr2[i6 * 3] = dataInputStream.readShort();
                    iArr2[(i6 * 3) + 1] = dataInputStream.readShort();
                    iArr2[(i6 * 3) + 2] = dataInputStream.readInt();
                }
                short readShort8 = dataInputStream.readShort();
                int[] iArr3 = new int[readShort8 * 3];
                for (int i7 = 0; i7 < readShort8; i7++) {
                    iArr3[i7 * 3] = dataInputStream.readShort();
                    iArr3[(i7 * 3) + 1] = dataInputStream.readShort();
                    iArr3[(i7 * 3) + 2] = dataInputStream.readInt();
                }
                short readShort9 = dataInputStream.readShort();
                int[] iArr4 = new int[readShort9 * 5];
                for (int i8 = 0; i8 < readShort9; i8++) {
                    iArr4[i8 * 5] = dataInputStream.readShort();
                    iArr4[(i8 * 5) + 1] = dataInputStream.readShort();
                    iArr4[(i8 * 5) + 2] = dataInputStream.readShort();
                    iArr4[(i8 * 5) + 3] = dataInputStream.readShort();
                    iArr4[(i8 * 5) + 4] = dataInputStream.readInt();
                }
                short readShort10 = dataInputStream.readShort();
                short[] sArr7 = new short[readShort10 << 1];
                for (int i9 = 0; i9 < readShort10; i9++) {
                    sArr7[i9 * 2] = dataInputStream.readShort();

                    sArr7[(i9 * 2) + 1] = dataInputStream.readShort();
                }
                dataInputStream.close();
                sprite.a = sArr;
                sprite.b = sArr2;
                sprite.f = sArr4;
                sprite.c = sArr3;
                sprite.d = sArr5;
                sprite.e = sArr7;
                return sprite;
            } catch (Exception e) {
                throw e;
            }
        } catch (Throwable th) {
            throw th;
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException ignored) {

            }
        }
    }
}