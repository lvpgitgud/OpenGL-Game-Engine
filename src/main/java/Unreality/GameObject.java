package Unreality;

import Components.Component;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    private static int ID_COUNTER = 0;
    private int uid = -1;
    private String name;
    private List<Components.Component> components;
    public Transform transform;
    private int zIndex;


    public GameObject(String name, Transform transform, int zIndex) {
        this.name = name ;
        this.components = new ArrayList<>();
        this.transform = transform;
        this.zIndex = zIndex;
        this.uid = ID_COUNTER++;

    }
    public void imgui() {
        for (Components.Component c : components) {
            c.imgui();
        }
    }



    public <T extends Components.Component> T getComponent(Class<T> componentClass) {
        for (Components.Component C : components) {
            if (componentClass.isAssignableFrom(C.getClass())) {
                try {
                    return componentClass.cast(C);

                } catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error: Casing component error";
                }
            }
        }
        return null;
    }

    public <T extends Components.Component> void removeComponent(Class<T> componentClass) {
        for (int i = 0 ; i < components.size(); i++){
            Components.Component c = components.get(i);
            if (componentClass.isAssignableFrom(c.getClass())){
                components.remove(i);
                return;
            }
        }
    }
    public void addComponent(Component c) {
        c.generateId();
        this.components.add(c);
        c.gameObject = this;
    }



    public void update (float dt) {
        for (int i = 0; i<components.size(); i++){
            components.get(i).update(dt);
        }
    }
    public void start() {
        for (int i = 0; i<components.size(); i++){
            components.get(i).start();
        }
    }

    public static void init(int maxId) {
        ID_COUNTER = maxId;
    }
    public int getUid() {
        return this.uid;
    }

    public int zIndex(){
        return this.zIndex;
    }
    public List<Component> getAllComponents() {
        return this.components;
    }
}

