package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Human extends GameObject {
    protected float speed;
    protected Animation<TextureRegion> currentAnimation;

    /**
     * walking animation
     */
    protected Array<TextureRegion> walkDownwardsFrames = new Array<>(TextureRegion.class);
    protected Array<TextureRegion> walkRightwardsFrames = new Array<>(TextureRegion.class);
    protected Array<TextureRegion> walkUpwardsFrames = new Array<>(TextureRegion.class);
    protected Array<TextureRegion> walkLeftwardsFrames = new Array<>(TextureRegion.class);

    protected Animation<TextureRegion> walkDownAnimation;
    protected Animation<TextureRegion> walkRightAnimation;
    protected Animation<TextureRegion> walkUpAnimation;
    protected Animation<TextureRegion> walkLeftAnimation;



    public Human(float x, float y, float speed) {
        super(x, y);
        this.speed = speed;
    }
}
