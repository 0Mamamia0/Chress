package iml;

import io.github.humbleui.skija.Image;

/* loaded from: 中国象棋-水墨智能版_1.jar:af.class */
public abstract class SpriteLoader {
    /* renamed from: a */
    public abstract Image loadImage(String str, int i);

    /* renamed from: a */
    public final Sprite loadSprite(String name) {
        try {
            return a.a(name, false, ImageLoaderImpl.getInstance(), this);
        } catch (Exception e) {
            System.out.println(new StringBuffer("load sprite(").append(name).append(") data error.").toString());
            e.printStackTrace();
            return null;
        }
    }
}