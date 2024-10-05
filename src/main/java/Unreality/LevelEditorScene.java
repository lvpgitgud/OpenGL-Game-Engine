package Unreality;


import java.awt.event.KeyEvent;

import Components.FontRenderer;
import Components.SpriteRenderer;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import renderer.Shader;
import renderer.Texture;
import util.Time;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class LevelEditorScene extends Scene {

    private  int vertexID, fragmentID, shaderProgram;

    private float[] vertexArray = {
            //pos                       //color
            100.5f, 0.5f, 0.0f,          1.0f, 0.0f, 0.0f,1.0f,   1, 1, //bottom right  0
            0.5f, 100.5f, 0.0f,          0.0f, 1.0f, 0.0f,1.0f,   0, 0, //top left      1
            100.5f, 100.5f, 0.0f,        0.0f, 0.0f, 1.0f,1.0f,   1, 0, //top right     2
            0.5f, 0.5f, 0.0f,            1.0f, 1.0f, 0.0f,1.0f,   0, 1 //bottom left   3
    };
    private int[] elementArray = {
            2, 1, 0,    //top right triangle
            0, 1, 3     //bottom left triangle

    };

    private int vaoID, vboID, eboID;

    private Shader defaultShader ;

    private Texture TestTexture;

    GameObject testObj;
    private boolean fistTime = false;

    public LevelEditorScene() {


    }

    @Override
    public void init() {
        System.out.println("Creating test object");
        this.testObj = new GameObject("test object");
        this.testObj.addComponent(new SpriteRenderer());
        this.testObj.addComponent(new FontRenderer());
        this.addGameObjectToScene(this.testObj);

        this.camera = new Camera(new Vector2f(-200, -300));
        defaultShader = new Shader("assets/shaders/default.glsl");
        defaultShader.compile();
        this.TestTexture = new Texture("assets/images/testImage.jpg");

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        int positionSize = 3;
        int colorSize = 4;
        int uvSize = 2;
        int vertexSizeBytes = (positionSize + colorSize + uvSize) * Float.BYTES;
        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * Float.BYTES);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (positionSize + colorSize) * Float.BYTES);
        glEnableVertexAttribArray(2);

    }
    @Override
    public void update(float dt){

        //camera.position.x -= dt*50.0f;
        //camera.position.y -= dt * 20.0f;



        defaultShader.use();
        //upload texture to shader
        defaultShader.uploadTexture("TEX_SAMPLER", 0);
        glActiveTexture(GL_TEXTURE0);
        TestTexture.bind();


        defaultShader.uploadMat4f("uProjection", camera.getProjectionMatrix());
        defaultShader.uploadMat4f("uView", camera.getViewMatrix());
        defaultShader.uploadFloat("uTime", Time.getTime());




        glBindVertexArray(vaoID);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        defaultShader.detach();

        if (!fistTime) {
            System.out.println("Creating Game Object");
            GameObject go = new GameObject("Game test 2");
            go.addComponent(new SpriteRenderer());
            this.addGameObjectToScene(go);
            fistTime = true;
        }

        for(GameObject go : this.GameObjects){
            go.update(dt);
        }

    }
}
