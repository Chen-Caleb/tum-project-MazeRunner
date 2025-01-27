package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Heart extends StaticObject {
    private Animation<TextureRegion> heartAnimation;

    public Heart(float x, float y) {
        super(x, y);
        rectangle = new Rectangle(position.x, position.y, width, height);

        int animationFrames = 4;
        Array<TextureRegion> heartFrames = new Array<>(TextureRegion.class);

        // Load frames for the heart animation
        for (int column = 0; column < animationFrames; column++) {
            heartFrames.add(new TextureRegion(objectSheet, column * width, 3 * height, width, height));
        }

        heartAnimation = new Animation<>(0.15f, heartFrames);
        stateTime = 0f;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = heartAnimation.getKeyFrame(stateTime, true);
        spriteBatch.draw(currentFrame, rectangle.x, rectangle.y);
    }
}
