package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Key extends StaticObject{
    private Animation<TextureRegion> keyAnimation;

    public Key(float x, float y) {
        super(x, y);
        rectangle = new Rectangle(position.x, position.y, width, height);

        int animationFrames = 5;
        Array<TextureRegion> keyFrames = new Array<>(TextureRegion.class);
        for (int column = 0; column < animationFrames; column++) {
            keyFrames.add(new TextureRegion(objectSheet, (11 + column) * width, 3 * height, width, height));
        }
        for (int column = 0; column < 3 * animationFrames; column++ ) {
            keyFrames.add(new TextureRegion(objectSheet, 15 * width, 3 * height, width, height));
        }
        for (int column = 0; column < animationFrames; column++) {
            keyFrames.add(new TextureRegion(objectSheet, (15 - column) * width, 3 * height, width, height));
        }


        keyAnimation = new Animation<>(0.15f, keyFrames);
        stateTime = 0f;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = keyAnimation.getKeyFrame(stateTime, true);
        spriteBatch.draw(currentFrame, rectangle.x, rectangle.y);
    }
}
