package de.tum.cit.fop.maze.gameobject;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    protected Vector2 position;
    protected Rectangle rectangle;
    protected int width, height;
    protected float stateTime;

    public GameObject(float x, float y) {
        this.position = new Vector2(x, y);;
    }
}
