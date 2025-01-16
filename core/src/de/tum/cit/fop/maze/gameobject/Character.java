package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Character extends Human{
    protected float runningSpeed;
    protected int characterFrameWidth = 16;
    protected int characterFrameHeight = 32;
    protected final Texture characterSheet = new Texture(Gdx.files.internal("character.png"));

    public Character(float x, float y) {
        super(x, y);
    }
}
