package physics2d.components;

import Components.Component;
import org.joml.Vector2f;
import renderer.DebugDraw;

public class CircleCollider extends Component {
    private float radius = 1f;
    protected Vector2f offset = new Vector2f();
    @Override
    public void editorUpdate(float dt) {
        Vector2f center = new Vector2f(this.gameObject.transform.position).add(this.offset);
        DebugDraw.addCircle(center, this.radius);
    }
    public void setOffset(Vector2f newOffset) { this.offset.set(newOffset); }

    public Vector2f getOffset() {
        return this.offset;
    }
    public float getRadius() {
        return radius;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }
}
