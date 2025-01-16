package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class StaticObject extends GameObject {
    protected static final Texture basictiles = new Texture(Gdx.files.internal("basictiles.png"));
    protected final Texture objectSheet = new Texture(Gdx.files.internal("objects.png"));


    public StaticObject(float x, float y) {
        super(x, y);
    }

    @Override
    public Rectangle getRectangle() {
        return super.getRectangle();
    }

}
