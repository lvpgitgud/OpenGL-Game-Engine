package Unreality;

import static org.lwjgl.glfw.GLFW.*;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private boolean moustButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener(){
        this.scrollX =0.0;
        this.scrollY = 0.0;
        this.lastX = 0.0 ;
        this.lastY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;


    }

    public static MouseListener get() {
        if (MouseListener.instance == null) {
            instance = new MouseListener();
        }
        return  instance;
    }
    public static void mousePosCallback(long window, double xpos, double ypos) {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;
        get().isDragging = get().moustButtonPressed[0] || get().moustButtonPressed[1] || get().moustButtonPressed[2] ;

    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < get().moustButtonPressed.length) {
                get().moustButtonPressed[button] = true;
            } else if (action == GLFW_RELEASE) {
                if (button < get().moustButtonPressed.length) {
                    get().moustButtonPressed[button] = false;
                    get().isDragging = false;
                }
            }

        }
    }

    public static void mouseScrollCallback(long window,double xOffset,double yOffset){
            get().scrollX = xOffset;
            get().scrollY = yOffset;
    }

    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }






        public static float getX() {
        return (float)get().xPos;
    }

    public static float getY(){
        return (float) get().yPos;

    }

    public static float getDx() {
        return (float)(get().lastX-get().xPos);

    }

    public static float getDy() {
        return (float)(get().lastY - get().yPos);
    }

    public static float getScrollX() {
        return (float)get().scrollX;
    }
    public static float getScrollY() {
        return (float)get().scrollY;
    }

    public static boolean mouseButtonDown(int button) {
        if (button < get().moustButtonPressed.length) {
            return get().moustButtonPressed[button];
        }
        else {
            return false;
        }
    }




}
