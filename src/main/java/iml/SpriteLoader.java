package iml;

import io.github.humbleui.skija.Image;

/* loaded from: 中国象棋-水墨智能版_1.jar:af.class */
public abstract class SpriteLoader {
    /* renamed from: a */
    public abstract Image loadImage(String str, int i);

    /* renamed from: a */
    public final Sprite loadSprite(String name) {
        try {
            return Unkonw.a(name, false, ImageLoaderImpl.getInstance(), this);
        } catch (Exception e) {
            System.out.println(name);
            e.printStackTrace();
            return null;
        }
    }
}