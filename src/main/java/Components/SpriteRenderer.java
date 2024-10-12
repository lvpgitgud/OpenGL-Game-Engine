package Components;

import Unreality.Component;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;

public class SpriteRenderer extends Component {
    private boolean firstTime = false;
    private Vector4f color;
    private Vector2f[] texCoords;
    private Texture texture;

    public SpriteRenderer(Vector4f color){
        this.color = color;
        this.texture = null;
    }
    public SpriteRenderer(Texture texture){
        this.texture = texture;
        this.color = new Vector4f(1,1,1,1);
    }
    public Texture getTexture(){
        return this.texture;
    }

    public Vector2f[] getTexCoords(){
        Vector2f[] texCoords = {
                new Vector2f(1,1),
                new Vector2f(1,0),
                new Vector2f(0,1),
                new Vector2f(0,0)
        };
        return texCoords;
    }
    @Override
    public void start() {

    }


    @Override
    public void update(float dt) {
        if (!firstTime) {

            firstTime = true;
        }

    }
    public Vector4f getColor(){
        return this.color;
    }
}
