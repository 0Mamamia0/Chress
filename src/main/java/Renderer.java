import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.DirectContext;
import ui.Display;

public class Renderer {

    DisplaySurface surface;
    DirectContext context;
    Display display;

    public Renderer() {
        display = Display.getDefaultDisplay();
        surface = new DisplaySurface(240, 320);
    }


    public void create() {
        context = surface.onSurfaceCreate();
    }


    public void onRefresh() {
    }

    public void onSizeChange(int width, int height) {

    }

    public void onFrameBufferSizeChange(int width, int height) {
        surface.onSizeChange(width, height);
    }

    public void onKeyCode(int key, int scancode, int action, int mods) {

    }


    public void update() {
        display.update();
    }


    public void draw(Canvas canvas) {
        display.draw(canvas);
    }

    public void render() {

        update();



        Canvas canvas = surface.lockCanvas();
        try {
            if(canvas != null) {
                draw(canvas);
            }
        } finally {
            surface.unlockCanvas();
        }
        context.flush();

    }


}
