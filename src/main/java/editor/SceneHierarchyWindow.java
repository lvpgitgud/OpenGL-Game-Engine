package editor;

import Unreality.GameObject;
import Unreality.Window;
import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;

import java.util.List;

public class SceneHierarchyWindow {

    private static String payloadDraDropType = "SceneHierarchy";
    public void imgui() {
        ImGui.begin("Scene Hierarchy");
        List<GameObject> gameObjects = Window.getScene().getGameObjects();
        int index = 0;
        for (GameObject obj : gameObjects) {
            if (!obj.doSerialization()) {
                continue;
            }


            boolean treeNodeOpen = doTreeNode(obj,index);

            if (treeNodeOpen) {
                ImGui.treePop();
            }
            index++;
        }
        ImGui.end();
    }

    private boolean doTreeNode(GameObject obj, int index) {
        ImGui.pushID(index);
        boolean treeNodeOpen = ImGui.treeNodeEx(
                obj.name,
                ImGuiTreeNodeFlags.DefaultOpen | ImGuiTreeNodeFlags.FramePadding |
                        ImGuiTreeNodeFlags.OpenOnArrow | ImGuiTreeNodeFlags.SpanAvailWidth,
                obj.name
        );
        ImGui.popID();

        if (ImGui.beginDragDropSource()){
            ImGui.setDragDropPayloadObject(payloadDraDropType, obj );

            ImGui.text(obj.name);

            ImGui.endDragDropSource();
        }

        if (ImGui.beginDragDropTarget()){
            Object payloadObj = ImGui.acceptDragDropPayloadObject(payloadDraDropType);

            if(payloadObj != null){
                if (payloadObj.getClass().isAssignableFrom(GameObject.class)) {
                    GameObject playerGameObj = (GameObject)payloadObj;
                    System.out.println("payload accepted '" + playerGameObj.name + "'");
                }
            }


            ImGui.endDragDropTarget();

        }

        return treeNodeOpen;
    }
}
