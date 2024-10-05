package Unreality;

import renderer.RenderBatch;
import renderer.Renderer;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private Renderer renderer;
    protected Camera camera;
    private boolean isRunning = false;
    protected List<GameObject> GameObjects = new ArrayList<>();

    public Scene() {

    }
    public void start(){
        for (GameObject go : GameObjects){
            go.start();
            this.renderer.add(go);
        }
        isRunning = true;
    }

    public void addGameObjectToScene(GameObject go){
        if (!isRunning){
            GameObjects.add(go);
        } else {
            GameObjects.add(go);
            go.start();
            this.renderer.add(go);
        }
    }

    public void init() {

    }
    public abstract void update(float dt) ;

    public Camera camera(){
        return this.camera;
    }
}
