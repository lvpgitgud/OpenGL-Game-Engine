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
    private Sprite sprite;

    public SpriteRenderer(Vector4f color) {
        this.color = color;
        this.sprite = new Sprite(null);
    }
    public SpriteRenderer(Sprite sprite) {
        this.sprite = sprite;
        this.color = new Vector4f(1, 1, 1, 1);
    }
    public Texture getTexture(){
        return sprite.getTexture();
    }

    public Vector2f[] getTexCoords() {
        return sprite.getTexCoords();
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
