package iml;

public final class ah extends u {
    private a a;

    public ah(Sprite sprite, a aVar) {
        super(sprite);
        this.a = aVar;
    }

    @Override // defpackage.u
    public final void e() {
        this.a.c();
    }

    @Override // defpackage.u
    public final byte d() {
        return (byte) 0;
    }

    @Override // defpackage.u
    public final int b() {
        return this.a.a();
    }

    @Override // defpackage.u
    public final int c() {
        return this.a.b();
    }

    @Override // defpackage.u
    public final void a(int i, int i2) {
        this.a.a(i, i2);
    }
}