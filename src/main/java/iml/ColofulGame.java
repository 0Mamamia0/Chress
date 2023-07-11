package iml;

public final class ColofulGame extends u {
    private Unkonw a;

    public ColofulGame(Sprite sprite, Unkonw aVar) {
        super(sprite);
        this.a = aVar;
    }

    @Override // defpackage.u
    public final void stop() {
        this.a.stop();
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
    public final int ox() {
        return this.a.b();
    }

    @Override // defpackage.u
    public final void a(int i, int i2) {
        this.a.a(i, i2);
    }
}