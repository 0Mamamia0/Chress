package iml;


import io.github.humbleui.skija.Image;
import ui.ImageLoader;

/* renamed from: ab */
/* loaded from: 中国象棋-水墨智能版_1.jar:ab.class */
public final class SpriteLoaderImpl extends SpriteLoader {
    @Override // defpackage.SpriteLoader
    /* renamed from: a */
    public final Image loadImage(String str, int i) {
        Image image = null;
        if (str.equals("/game.anu") && i == 0) {
            image = ImageLoader.loadImage("/1.png"); // logo
        }
        return image;
    }
}