package Unreality;


import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
public class Window {
    private int width, height;
    String title;
    private static Window window = null ;

    private long glfwWindow;
    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "idk";
    }
    public static Window get() {
        if (Window.window == null ) {
            Window.window = new Window();
        }
        return Window.window;
    }
    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        init();
        loop();

    }
    public void init(){
        GLFWErrorCallback.createPrint(System.err).set();

        //initialize glfw
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");

        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");

        }
        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(1);
        glfwShowWindow(glfwWindow); //make win dow visible

        GL.createCapabilities();


    }
    public void loop(){
        while (!glfwWindowShouldClose(glfwWindow)){
            glfwPollEvents();

            glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);
        }
    }
}
