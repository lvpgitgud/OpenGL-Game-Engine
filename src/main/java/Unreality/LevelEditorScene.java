package Unreality;

import java.awt.event.KeyEvent;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class LevelEditorScene extends Scene {
    private boolean changingScene = false;
    private float  timeToChangScene = 2.0f;

    private String vertexShaderSrc = "#version 330 core\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "layout (location = 1) in vec4 aColor;\n" +
            "\n" +
            "out vec4 fColor;\n" +
            "\n" +
            "void main() {\n" +
            "    fColor = aColor;\n" +
            "    gl_Position = vec4(aPos,1.0f);\n" +
            "\n" +
            "\n" +
            "}";
    private String fragmentShaderSrc = "#version 330 core\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main() {\n" +
            "    color = fColor;\n" +
            "}";
    private  int vertexID, fragmentID, shaderProgram;

    private float[] vertexArray = {
            //pos                       //color
            0.5f, -0.5f, 0.0f,          1.0f, 0.0f, 0.0f,1.0f,  //bottom right  0
            -0.5f, 0.5f, 0.0f,          0.0f, 1.0f, 0.0f,1.0f,  //top left      1
            0.5f, 0.5f, 0.0f,           0.0f, 0.0f, 1.0f,1.0f,  //top right     2
            -0.5f, -0.5f, 0.0f,         1.0f, 1.0f, 0.0f,1.0f,  //bottom left   3
    };
    private int[] elementArray = {
            2, 1, 0,    //top right triangle
            0, 1, 3     //bottom left triangle

    };

    private int vaoID, vboID, eboID;

    public LevelEditorScene() {
        System.out.println("inside level editor scene!");

    }

    @Override
    public void init() {
        //compile file and link them
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexID, vertexShaderSrc);
        glCompileShader(vertexID);
        int Success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if (Success == GL_FALSE) {
            int len = glGetShaderi(vertexID, GL_COMPILE_STATUS);
            System.out.println("ERROR: 'defaultShader.glsl'\n \tVertex Shader compilation failed.");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false: "";
        }

        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentID, fragmentShaderSrc);
        glCompileShader(fragmentID);
        Success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (Success == GL_FALSE) {
            int len = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
            System.out.println("ERROR: 'defaultShader.glsl'\n Fragment Shader compilation failed.");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false: "";
        }
        //link shader and check 4 error
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexID);
        glAttachShader(shaderProgram, fragmentID);
        glLinkProgram(shaderProgram);

        Success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if (Success == GL_FALSE) {
            int len = glGetShaderi(shaderProgram, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'defaultShader.glsl'\n \tProgram Shader compilation failed.");
            System.out.println(glGetShaderInfoLog(shaderProgram, len));
            assert false: "";
        }

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
        int floatSizeBytes = 4;
        int vertexSizeBytes = (positionSize + colorSize) * floatSizeBytes;
        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * floatSizeBytes);
        glEnableVertexAttribArray(1);

    }
    @Override
    public void update(float dt){
        glUseProgram(shaderProgram);

        glBindVertexArray(vaoID);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        glUseProgram(0);



    }
}
