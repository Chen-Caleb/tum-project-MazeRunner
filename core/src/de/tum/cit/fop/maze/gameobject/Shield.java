package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Shield extends StaticObject{
    private Animation<TextureRegion> shieldAnimation;

    public Shield(float x, float y) {
        super(x, y);
        rectangle = new Rectangle(position.x, position.y, width, height);

        int animationFrames = 4;
        Array<TextureRegion> shieldFrames = new Array<>(TextureRegion.class);

        // Load frames for the shield animation
        for (int column = 0; column < animationFrames; column++) {
            shieldFrames.add(new TextureRegion(objectSheet, column * width, 4 * height, width, height));
        }

        shieldAnimation = new Animation<>(0.15f, shieldFrames);
        stateTime = 0f;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = shieldAnimation.getKeyFrame(stateTime, true);
        spriteBatch.draw(currentFrame, rectangle.x, rectangle.y);
    }
}
