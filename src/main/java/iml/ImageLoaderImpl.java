package iml;

import io.github.humbleui.skija.Image;

public final class ImageLoaderImpl implements ImageLoader {

    /* renamed from: a */
    private static ImageLoaderImpl instance;

    /* renamed from: a */
    public static ImageLoaderImpl getInstance() {
        if (instance == null) {
            instance = new ImageLoaderImpl();
        }
        return instance;
    }

    @Override // defpackage.ImageLoader
    /* renamed from: a */
    public final Image[] loadImage(String str, int i, SpriteLoader spriteLoader) {
        return new Image[]{spriteLoader.loadImage(str, i)};
    }
}