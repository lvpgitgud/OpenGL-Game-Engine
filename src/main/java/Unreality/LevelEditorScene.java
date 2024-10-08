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

        int xOffset = 10;
        int yOffset = 10;

        float totalWidth = (float)(600 - xOffset * 2);
        float totalHeight =  (float)(300 - yOffset * 2);
        float sizeX = totalWidth/100.0f;
        float sizeY = totalHeight/100.0f;
        float padding = 3;

        for (int x = 0; x<100; x++){
            for (int y = 0; y < 100; y++){
                float xPos = xOffset + (x*sizeX);
                float yPos = yOffset + (y * sizeY);

                GameObject go = new GameObject("Obj"+ x + "" +y,new Transform(new Vector2f(xPos,yPos), new Vector2f(sizeX, sizeY)));
                go.addComponent(new SpriteRenderer(new Vector4f(xPos / totalWidth, yPos/totalHeight,1,1)));
                this.addGameObjectToScene(go);

            }
        }
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
