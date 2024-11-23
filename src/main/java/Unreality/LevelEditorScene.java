package Unreality;


import java.awt.event.KeyEvent;

import Components.FontRenderer;
import Components.Sprite;
import Components.SpriteRenderer;
import Components.Spritesheet;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import renderer.Shader;
import renderer.Texture;
import util.AssetPool;
import util.Time;
import imgui.ImGui;
import imgui.ImGui;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class LevelEditorScene extends Scene {

    private GameObject obj1;
    private Spritesheet sprites;

    public LevelEditorScene() {


    }

    @Override
    public void init() {
        loadResources();


        this.camera = new Camera(new Vector2f(-250, 0));

        sprites = AssetPool.getSpritesheet("assets/images/spritesheet.png");

        obj1 = new GameObject("Object 1", new Transform(new Vector2f(200, 100),
                new Vector2f(256, 256)), 2);
        obj1.addComponent(new SpriteRenderer(new Vector4f(1, 0, 0, 1)));
        this.addGameObjectToScene(obj1);
        this.activeGameObject = obj1;

        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)), 3);
        obj2.addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture("assets/images/blendImage2.png"))));
        this.addGameObjectToScene(obj2);


    }
    private void loadResources(){
        AssetPool.getShader("assets/shaders/default.glsl");
        AssetPool.addSpritesheet("assets/images/spritesheet.png",
                new Spritesheet(AssetPool.getTexture("assets/images/spritesheet.png"), 16, 16, 26, 0));


    }
    private int spriteIndex = 0;
    private float spriteFlipTime = 0.2f;
    private float spriteFlipTimeLeft = 0.0f;
    @Override
    public void update(float dt){
        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }
    public void imgui() {
        ImGui.begin("Test window");
        ImGui.text("Some random text");
        ImGui.end();
    }
}
