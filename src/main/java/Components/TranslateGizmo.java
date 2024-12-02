package Components;

import Unreality.GameObject;
import Unreality.MouseListener;
import Unreality.Prefabs;
import Unreality.Window;
import editor.PropertiesWindow;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.system.CallbackI;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

public class TranslateGizmo extends Gizmo {
    public TranslateGizmo(Sprite arrowSprite, PropertiesWindow propertiesWindow) {
        super(arrowSprite, propertiesWindow);
    }


    @Override
    public void editorUpdate(float dt) {
        if (activeGameObject != null) {
            if (xAxisActive && !yAxisActive) {
                activeGameObject.transform.position.x -= MouseListener.getWorldX();
            } else if (yAxisActive) {
                activeGameObject.transform.position.y -= MouseListener.getWorldY();
            }
        }

        super.editorUpdate(dt);

    }
}
