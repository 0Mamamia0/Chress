import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.DirectContext;
import org.lwjgl.glfw.GLFW;
import ui.Display;
import util.Key;
import util.Time;

public class Renderer {





    Display display;
    DisplaySurface surface;
    DirectContext context;


    private int cursor_x;
    private int cursor_y;
    private long lastFrame = 0;
    private boolean pointerPressed;
    private float scale = 4.0f;



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

        int code = -1;

        switch (key) {
            case GLFW.GLFW_KEY_UP: code = Key.KEY_UP; break;
            case GLFW.GLFW_KEY_DOWN: code = Key.KEY_DOWN; break;
            case GLFW.GLFW_KEY_LEFT: code = Key.KEY_LEFT; break;
            case GLFW.GLFW_KEY_RIGHT: code = Key.KEY_RIGHT; break;
            case GLFW.GLFW_KEY_RIGHT_ALT: code = Key.KEY_L_SOFT; break;
            case GLFW.GLFW_KEY_RIGHT_CONTROL: code = Key.KEY_R_SOFT; break;
            case GLFW.GLFW_KEY_ENTER: code = Key.KEY_OK; break;
            default: break;
        }

        if(action == GLFW.GLFW_PRESS) {
            display.keyPressed(code);
        } else if (action == GLFW.GLFW_RELEASE) {
            display.keyRelease(code);
        } else if (action == GLFW.GLFW_REPEAT) {
            display.keyRepeated(code);
        }

    }

    public void update() {
        long now = Time.now();
        long last = lastFrame;
        long delta = now -last;

        if(last == 0) {
            display.update(0);
        } else {
            display.update(delta);
        }
        this.lastFrame = now;
    }


    public void draw() {
        Canvas canvas = surface.lockCanvas();
        try {
            if(canvas != null) {
                display.draw(canvas);
            }
        } finally {
            surface.unlockCanvas();
        }
        context.flush();
    }


    public void render() {
        update();
        draw();
    }


    public void destroy() {
        display.destroy();
    }


    public void onCursorPos(double xpos, double ypos) {
        this.cursor_x = (int)xpos;
        this.cursor_y = (int)ypos;
        if(pointerPressed) {
            int x = Math.round(cursor_x / scale);
            int y = Math.round(cursor_y / scale);
            if( x < 0 || x > 240 || y < 0 || y > 320 ) {
                pointerPressed = false;
                display.pointerReleased(x, y);
                return;
            }
            display.pointerDragged( x, y);
        }
    }

    public void onMouseButton(int button, int action, int mods) {
        if(button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            switch (action) {
                case GLFW.GLFW_PRESS:
                    if(!pointerPressed) {
                        display.pointerPressed(Math.round(cursor_x / scale), Math.round(cursor_y / scale));
                        pointerPressed = true;
                    }
                    break;
                case GLFW.GLFW_RELEASE:
                    if(pointerPressed) {
                        display.pointerReleased(Math.round(cursor_x / scale), Math.round(cursor_y / scale));
                        pointerPressed = false;
                    }
                    break;
            }
        }
    }
}
