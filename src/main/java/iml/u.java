package iml;



/* renamed from: u */
/* loaded from: 中国象棋-水墨智能版_1.jar:u.class */
public abstract class u {

    /* renamed from: a */
    private Sprite sprite;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int[] g;
    private boolean h;

    public u() {
        this.g = new int[4];
        this.h = false;
    }

    public u(Sprite sprite) {
        this.g = new int[4];
        this.h = false;
        this.sprite = sprite;
    }

    public final void a(int i) {
        this.b = 0;
        int i2 = this.b << 1;
        this.d = (this.sprite.a[i2 + 1] - this.sprite.a[i2]) + 1;
        c(0);
    }

    private void c(int i) {
        this.c = i;
        this.e = 0;
        this.f = this.sprite.b[(this.sprite.a[this.b << 1] + i) << 2];
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2, types: [int] */
    public final int[] b(int i) {
        int i2 = this.sprite.f[this.f << 1];
        short s = this.sprite.f[(this.f << 1) + 1];
        while (i2 < s) {
            char c = (char) i2;
            int i3 = i2 + 1;
            short s2 = this.sprite.c[c];
            int i4 = i3 + 1;
            short s3 = this.sprite.c[i3];
            int i5 = i4 + 1;
            short s4 = this.sprite.c[i4];
            i2 = i5 + 1;
            if (((byte) this.sprite.c[i5]) == 15) {
                int i6 = s2 << 1;
                this.g[2] = this.sprite.e[i6];
                this.g[3] = this.sprite.e[i6 + 1];
                byte d = d();
                if (d == 1) {
                    s3 = (short) ((-s3) - this.g[2]);
                } else if (d == 2) {
                    s4 = (short) ((-s4) - this.g[3]);
                }
                this.g[0] = s3;
                this.g[1] = s4;
                return this.g;
            }
        }
        return null;
    }

    public final void a() {
        if (this.h) {
            this.h = false;
            c(this.c);
            int i = this.sprite.a[this.b << 1] + this.c;
            short s = this.sprite.b[(i << 2) + 2];
            short s2 = this.sprite.b[(i << 2) + 3];
            a(d() == 1 ? -s : s, d() == 2 ? -s2 : s2);
        }
        if (this.e < this.sprite.b[((this.sprite.a[this.b << 1] + this.c) << 2) + 1]) {
            this.e++;
        } else if (this.c >= this.d - 1) {
            e();
            this.h = true;
            this.c = 0;
        } else {
            c(this.c + 1);
            int i2 = this.sprite.a[this.b << 1] + this.c;
            short s3 = this.sprite.b[(i2 << 2) + 2];
            short s4 = this.sprite.b[(i2 << 2) + 3];
            a(d() == 1 ? -s3 : s3, d() == 2 ? -s4 : s4);
            this.e++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v1, types: [int] */
//    public final void a(Graphics graphics) {
//        int i = this.sprite.f[this.f << 1];
//        short s = this.sprite.f[(this.f << 1) + 1];
//        int clipX = graphics.getClipX();
//        int clipY = graphics.getClipY();
//        int clipWidth = graphics.getClipWidth();
//        int clipHeight = graphics.getClipHeight();
//        while (i < s) {
//            char c = i;
//            int i2 = i + 1;
//            short s2 = this.sprite.c[c];
//            int i3 = i2 + 1;
//            short s3 = this.sprite.c[i2];
//            int i4 = i3 + 1;
//            short s4 = this.sprite.c[i3];
//            i = i4 + 1;
//            byte b = (byte) this.sprite.c[i4];
//            if ((b & 1) == 0) {
//                byte b2 = (byte) ((b & 248) >> 3);
//                byte b3 = (byte) (((byte) (b & 7)) >> 1);
//                int i5 = s2 << 2;
//                int i6 = i5 + 1;
//                short s5 = this.sprite.d[i5];
//                int i7 = i6 + 1;
//                short s6 = this.sprite.d[i6];
//                short s7 = this.sprite.d[i7];
//                short s8 = this.sprite.d[i7 + 1];
//                Image[] imageArr = (Image[]) this.sprite.g.elementAt(b2);
//                int b4 = s3 + b();
//                int c2 = s4 + c();
//                graphics.clipRect(b4, c2, s7, s8);
//                if (b3 == 1) {
//                    graphics.drawRegion(imageArr[0], s5, s6, s7, s8, 2, b4, c2, 20);
//                } else if (b3 == 2) {
//                    graphics.drawRegion(imageArr[0], s5, s6, s7, s8, 1, b4, c2, 20);
//                } else {
//                    graphics.drawImage(imageArr[0], b4 - s5, c2 - s6, 20);
//                }
//            }
//            graphics.setClip(clipX, clipY, clipWidth, clipHeight);
//        }
//    }

    protected abstract int b();

    protected abstract int c();

    protected abstract void a(int i, int i2);

    protected abstract byte d();

    protected abstract void e();
}