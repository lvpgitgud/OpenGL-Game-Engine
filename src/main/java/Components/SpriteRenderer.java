package Components;

import Unreality.Component;
import org.joml.Vector4f;

public class SpriteRenderer extends Component {
    private boolean firstTime = false;
    private Vector4f color;

    public SpriteRenderer(Vector4f color){
        this.color = color;
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
