package Unreality;


import java.awt.event.KeyEvent;

import Components.FontRenderer;
import Components.SpriteRenderer;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import renderer.Shader;
import renderer.Texture;
import util.AssetPool;
import util.Time;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class LevelEditorScene extends Scene {

    public LevelEditorScene() {


    }

    @Override
    public void init() {
        this.camera = new Camera(new Vector2f());

        GameObject obj1 = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        obj1.addComponent(new SpriteRenderer(AssetPool.getTexture("assets/images/testImage.png")));
        this.addGameObjectToScene(obj1);

        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)));
        obj2.addComponent(new SpriteRenderer(AssetPool.getTexture("assets/images/testImage2.png")));
        this.addGameObjectToScene(obj2);

        loadResource();
    }
    private void loadResource(){
        AssetPool.getShader("assets/shaders/default.glsl");
    }
    @Override
    public void update(float dt){

        //camera.position.x -= dt*50.0f;
        //camera.position.y -= dt * 20.0f;


        for(GameObject go : this.gameObjects){
            go.update(dt);
        }
        this.renderer.render();

    }
}
