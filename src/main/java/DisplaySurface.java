import io.github.humbleui.skija.*;
import io.github.humbleui.types.Rect;

import static org.lwjgl.opengl.GL11.glGetIntegerv;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_BINDING;

public class DisplaySurface {

    private float scaleX;
    private float scaleY;
    private int width;
    private int height;
    private int logicalWidth;
    private int logicalHeight;

    private DirectContext context;
    private BackendRenderTarget renderTarget;
    private Surface surface;


    private int sava = -1;
    private Canvas locked = null;
    private boolean fullRedrawNeed = true;


    public DisplaySurface(int width, int height) {
        this.logicalWidth = width;
        this.logicalHeight = height;
    }


    public DirectContext onSurfaceCreate() {
        context = DirectContext.makeGL();
        return context;
    }


    public void onSizeChange(int width, int height) {


        this.scaleX = (float) width/logicalWidth;
        this.scaleY = (float) height/logicalHeight;
        this.width = width;
        this.height = height;



        renderTarget = BackendRenderTarget.makeGL(
                width,
                height,
                /*samples*/0,
                /*stencil*/8,
                /*fbId*/0,
                FramebufferFormat.GR_GL_RGBA8);

        surface = Surface.makeFromBackendRenderTarget(
                context,
                renderTarget,
                SurfaceOrigin.BOTTOM_LEFT,
                SurfaceColorFormat.RGBA_8888,
                ColorSpace.getDisplayP3(),  // TODO load monitor profile
                new SurfaceProps(PixelGeometry.RGB_H));
    }



    public Canvas lockCanvas() {
        if(locked != null) {
            throw new IllegalStateException();
        }
        locked = surface.getCanvas();
        sava = locked.save();
        if(fullRedrawNeed) {
            locked.scale(scaleX, scaleY);
            locked.clipRect(Rect.makeXYWH(0, 0, getWidth(), getHeight()));
            locked.clear(0xff000000);
            return locked;
        } else {
            return locked;
        }


    }

    public void unlockCanvas() {
        if(locked == null) {
            throw new IllegalStateException();
        }

        locked.restoreToCount(sava);
        surface.flushAndSubmit();
        locked = null;
    }


    public int getWidth() {
        return logicalWidth;
    }

    public int getHeight() {
        return logicalHeight;
    }
}
