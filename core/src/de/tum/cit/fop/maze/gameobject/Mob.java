package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Mob extends Human {
    protected final Texture mobSheet = new Texture(Gdx.files.internal("mobs.png"));

    public Mob(float x, float y, float speed) {
        super(x, y);
        this.speed = speed;


    }
}
