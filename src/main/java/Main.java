import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallbackI;
import org.lwjgl.glfw.GLFWWindowSizeCallbackI;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.Arrays;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;






public class Main {



    public static final int scale = 4;
    public static final int WIDTH = 240 * scale;
    public static final int HEIGHT = 320 * scale;


    public static void glfwInvoke(long window, GLFWWindowSizeCallbackI windowSizeCB, GLFWFramebufferSizeCallbackI framebufferSizeCB) {
        try (MemoryStack stack = stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);

            if (windowSizeCB != null) {
                glfwGetWindowSize(window, w, h);
                windowSizeCB.invoke(window, w.get(0), h.get(0));
            }

            if (framebufferSizeCB != null) {
                glfwGetFramebufferSize(window, w, h);
                framebufferSizeCB.invoke(window, w.get(0), h.get(0));
            }
        }



    }

    public static void main(String[] args) {

        GLFWErrorCallback.createPrint(System.err).set();
        if (glfwInit()) {
            glfwDefaultWindowHints();
//            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
            glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
            glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
            glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6);
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
            long window = glfwCreateWindow(WIDTH, HEIGHT, "中国象棋-水墨智能版", NULL, NULL);
            if(window != 0) {
                Renderer renderer = new Renderer();



                glfwSetWindowRefreshCallback(window, (w)-> renderer.onRefresh());
                glfwSetWindowSizeCallback(window, (w, width, height) -> renderer.onSizeChange(width, height));
                glfwSetFramebufferSizeCallback(window, (w, width, height)-> renderer.onFrameBufferSizeChange(width, height));
                glfwSetKeyCallback(window, (w, key, scancode, action, mods)-> renderer.onKeyCode(key, scancode, action, mods));
                glfwSetCursorPosCallback(window, (w, xpos, ypos) -> renderer.onCursorPos(xpos, ypos));
                glfwSetMouseButtonCallback(window, (w, button, action ,mods) -> renderer.onMouseButton(button, action, mods));


                glfwMakeContextCurrent(window);
                glfwSwapInterval(2);
                GL.createCapabilities();

                renderer.create();

                glfwInvoke(window, (w, width, height) -> renderer.onSizeChange(width, height), (w, width, height)-> renderer.onFrameBufferSizeChange(width, height));


                while (!glfwWindowShouldClose(window)) {
                    glfwPollEvents();
                    renderer.render();
                    glfwSwapBuffers(window);
                }

                renderer.destroy();

                glfwFreeCallbacks(window);
                glfwDestroyWindow(window);

            }


            glfwTerminate();
        }
        glfwSetErrorCallback(null).free();
    }
}
