package Components;

import editor.JImGui;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;
import Unreality.Transform;
import imgui.ImGui;
public class SpriteRenderer extends Component {
    private boolean firstTime = false;
    private Vector4f color = new Vector4f(1, 1, 1, 1);
    private Sprite sprite = new Sprite();
    private transient Transform lastTransform;
    private transient boolean isDirty = true;
    private Vector2f[] texCoords;
    private Texture texture;



//    public SpriteRenderer(Vector4f color) {
//        this.color = color;
//        this.sprite = new Sprite(null);
//        this.isDirty = true;
//    }
//    public SpriteRenderer(Sprite sprite) {
//        this.sprite = sprite;
//        this.color = new Vector4f(1, 1, 1, 1);
//        this.isDirty = true;
//    }

    @Override
    public void start() {
        this.lastTransform = gameObject.transform.copy();

    }


    @Override
    public void update(float dt) {
        if (!this.lastTransform.equals(this.gameObject.transform)) {
            this.gameObject.transform.copy(this.lastTransform);
            isDirty = true;
        }

    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public Vector2f[] getTexCoords() {
        return sprite.getTexCoords();
    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        this.isDirty = true;
    }
    public void setColor(Vector4f color) {
        if (!this.color.equals(color)) {
            this.isDirty = true;
            this.color.set(color);
        }
    }
    @Override
    public void imgui() {
        if (JImGui.colorPicker4("Color Pickier", this.color)) {
            this.isDirty = true;
        }
    }
    public boolean isDirty() {
        return this.isDirty;
    }
    public void setClean() {
        this.isDirty = false;
    }
    public Vector4f getColor(){
        return this.color;
    }
    public void setTexture(Texture texture) {
        this.sprite.setTexture(texture);
    }
}
