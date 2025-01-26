package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Wall extends StaticObject{
    private static final TextureRegion WALL_TEXTURE = new TextureRegion(basictiles, 64, 0, width, height);
    //private static final TextureRegion H_MARKER = new TextureRegion(basictiles, 9, 9, 14, 1);
    //private static final TextureRegion V_MARKER = new TextureRegion(basictiles, 9, 9, 1, 7);

    private final Rectangle[] sides = new Rectangle[4];

    private static final float OFFSET = 1.0f; // 偏移量常量



    public Wall(float x, float y) {
        super(x, y);
        this.rectangle.setSize(width, height);

        // Initialize sides for collision logic with comments for clarity
        // Top horizontal side
        sides[0] = createHSide(position.x + OFFSET, position.y + height * 0.5f - OFFSET);
        // Right vertical side
        sides[1] = createVSide(position.x + width - OFFSET, position.y + height * 0.5f);
        // Bottom horizontal side
        sides[2] = createHSide(position.x + OFFSET, position.y + height * 0.9f);
        // Left vertical side
        sides[3] = createVSide(position.x, position.y + height * 0.5f);
    }

    /**
     * Renders the wall texture.
     * @param spriteBatch the SpriteBatch used for rendering
     */

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.draw(WALL_TEXTURE, rectangle.x, rectangle.y);
    }

    /**
     * Renders the sides as markers.
     * @param spriteBatch the SpriteBatch used for rendering
     */
//    public void renderMarker(SpriteBatch spriteBatch) {
//        spriteBatch.draw(H_MARKER, sides[0].x, sides[0].y);
//        spriteBatch.draw(V_MARKER, sides[1].x, sides[1].y);
//        spriteBatch.draw(H_MARKER, sides[2].x, sides[2].y);
//        spriteBatch.draw(V_MARKER, sides[3].x, sides[3].y);
//    }

    /**
     * Gets the sides for collision detection.
     * @return an array of rectangles representing the sides
     */
    public Rectangle[] getSides() {
        return sides;
    }

    private Rectangle createHSide(float x, float y) {
        // Create a horizontal side
        return new Rectangle(x, y, 14, OFFSET);
    }

    private Rectangle createVSide(float x, float y) {
        // Create a vertical side
        return new Rectangle(x, y, OFFSET, 7);
    }
}
