package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Exit extends StaticObject{
    private TextureRegion exitTextureRegion;

    public Exit(float x, float y) {
        super(x, y);
        width = 16;
        height = 16;
        rectangle = new Rectangle(position.x, position.y, width, height);
        exitTextureRegion = new TextureRegion(basictiles, 2 * width, 6 * height, width, height);

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(exitTextureRegion, rectangle.x, rectangle.y);
    }
}
