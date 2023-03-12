package iml;

public class Test {
    public static void main(String[] args) {
        SpriteLoaderImpl impl = new SpriteLoaderImpl();
        a m = new a(impl.loadSprite("/game.anu"), 0, 0);
    }
}
