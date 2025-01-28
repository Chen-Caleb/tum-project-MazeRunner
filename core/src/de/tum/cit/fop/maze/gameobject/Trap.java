package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Trap extends StaticObject{
    private Animation<TextureRegion> trapAnimation;

    public Trap(float x, float y) {
        super(x, y);
        this.width = 16;
        this.height = 16;
        this.rectangle = new Rectangle(position.x, position.y, width /2, height/2);

        int animationFrames = 7;

        Array<TextureRegion> fireFrames = new Array<>(TextureRegion.class);
        for (int column = 0; column < animationFrames; column++) {
            fireFrames.add(new TextureRegion(objectSheet, (4 + column) * width, 3 * height, width, height));
        }

        trapAnimation = new Animation<>(0.15f, fireFrames);
        stateTime = 0f;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = trapAnimation.getKeyFrame(stateTime, true);
        spriteBatch.draw(currentFrame, this.rectangle.x, this.rectangle.y);
    }
}
