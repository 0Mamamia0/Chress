package ui;

public class Theme {

    public static final int THEME_DEFAULT  = 0;
    public static final int THEME_WOOD  = 1;


    public static int style = THEME_DEFAULT;

    public static int getStyle() {
        return style;
    }


    public static void setStyle(int style) {
        if(style != 1 && style != 0) return;
        Theme.style = style;
    }
}
