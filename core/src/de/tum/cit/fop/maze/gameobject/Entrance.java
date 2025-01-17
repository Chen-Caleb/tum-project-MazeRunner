package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Entrance extends StaticObject {
    private final TextureRegion entranceTextureRegion;

    public Entrance(float x, float y) {
        super(x, y);
        rectangle = new Rectangle(position.x, position.y, width, height);
        entranceTextureRegion = new TextureRegion(basictiles, 0, 6 * height, width, height);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(entranceTextureRegion, rectangle.x, rectangle.y);
    }
}
