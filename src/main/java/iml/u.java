package iml;


import io.github.humbleui.skija.Image;

/* renamed from: u */
/* loaded from: 中国象棋-水墨智能版_1.jar:u.class */
public abstract class u {

    /* renamed from: a */
    public  Sprite sprite;
    public  int b;
    public  int frame;
    public  int count;
    public  int index;
    public  int f;
    public  int[] g;
    public  boolean h;

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
        this.count = (this.sprite.a[1] - this.sprite.a[0]) + 1;
        setFrame(0);
    }

    private void setFrame(int i) {
        this.frame = i;
        this.index = 0;
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
            short s2 = this.sprite.position[c];
            int i4 = i3 + 1;
            short s3 = this.sprite.position[i3];
            int i5 = i4 + 1;
            short s4 = this.sprite.position[i4];
            i2 = i5 + 1;
            if (((byte) this.sprite.position[i5]) == 15) {
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

    public final void update() {
        if (this.h) {
            this.h = false;
            setFrame(this.frame);
            int i = this.sprite.a[this.b << 1] + this.frame;
            short s = this.sprite.b[(i << 2) + 2];
            short s2 = this.sprite.b[(i << 2) + 3];
            a(d() == 1 ? -s : s, d() == 2 ? -s2 : s2);
        }
        if (this.index < this.sprite.b[((this.sprite.a[this.b << 1] + this.frame) << 2) + 1]) {
            this.index++;
        } else if (this.frame >= this.count - 1) {
            stop();
            this.h = true;
            this.frame = 0;
        } else {
            setFrame(this.frame + 1);
            int i2 = this.sprite.a[this.b << 1] + this.frame;
            short s3 = this.sprite.b[(i2 << 2) + 2];
            short s4 = this.sprite.b[(i2 << 2) + 3];
            a(d() == 1 ? -s3 : s3, d() == 2 ? -s4 : s4);
            this.index++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v1, types: [int] */
    public final void paint(Object graphics) {
        int i = this.sprite.f[this.f << 1];
        short s = this.sprite.f[(this.f << 1) + 1];
//        int clipX = graphics.getClipX();
//        int clipY = graphics.getClipY();
//        int clipWidth = graphics.getClipWidth();
//        int clipHeight = graphics.getClipHeight();
        while (i < s) {
            short offset = this.sprite.position[i ++];
            short x = this.sprite.position[i ++];
            short y = this.sprite.position[i ++];
            byte b = (byte) this.sprite.position[i ++];
            if (b  == 0) {
                byte index = (byte) ((b & 248) >> 3);
                byte flag = (byte) (((byte) (b & 7)) >> 1);
                int coord_offset = offset << 2;
                short src_x = this.sprite.coord[coord_offset];
                short src_y = this.sprite.coord[coord_offset + 1];
                short src_width = this.sprite.coord[coord_offset + 2];
                short src_height = this.sprite.coord[coord_offset + 3];
                Image[] img = (Image[]) this.sprite.images.elementAt(index);
                int dst_x = x + b();
                int dst_y = y + ox();
//                graphics.clipRect(dst_x, dst_y, src_width, src_height);
                if (flag == 1) {
                    throw new RuntimeException();
                    //graphics.drawRegion(img[0], src_x, src_y, src_width, src_height, 2, dst_x, dst_y, 20);
                } else if (flag == 2) {
                    throw new RuntimeException();
                    //graphics.drawRegion(img[0], src_x, src_y, src_width, src_height, 1, dst_x, dst_y, 20);
                } else {

                    //graphics.drawImage(img[0], dst_x - src_x, dst_y - src_y, 20);
                }
            } else {
                throw new RuntimeException();
            }
            //graphics.setClip(clipX, clipY, clipWidth, clipHeight);
        }
    }



    protected abstract int b();

    protected abstract int ox();

    protected abstract void a(int i, int i2);

    protected abstract byte d();

    protected abstract void stop();
}