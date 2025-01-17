package de.tum.cit.fop.maze.gameobject;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    protected Vector2 position;
    protected  Rectangle rectangle;
    protected static int width = 16;
    protected static int height = 16;
    protected float stateTime;

    public GameObject(float x, float y) {
        this.position = new Vector2(x, y);
        this.rectangle = new Rectangle(position.x, position.y, width, height);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public abstract void render(SpriteBatch spriteBatch);
}

