package Unreality;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene {
    private boolean changingScene = false;
    private float  timeToChangScene = 2.0f;
    public LevelEditorScene() {
        System.out.println("inside level editor scene!");

    }
    @Override
    public void update(float dt){
        if (KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
            changingScene = true;
        }
        if (changingScene && timeToChangScene >0) {
            timeToChangScene -= dt;
            Window.get().r -= dt*5.0f;
            Window.get().g -= dt*5.0f;
            Window.get().b -= dt*5.0f;
            Window.get().a -= dt*5.0f;
        } else if (changingScene) {
            Window.changScene(1);
            
        }

    }
}
