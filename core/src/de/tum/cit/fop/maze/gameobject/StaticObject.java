package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class StaticObject extends GameObject {
    protected final Texture basictiles = new Texture(Gdx.files.internal("basictiles.png"));
    protected final Texture objectSheet = new Texture(Gdx.files.internal("objects.png"));


    public StaticObject(float x, float y) {
        super(x, y);
    }
}
