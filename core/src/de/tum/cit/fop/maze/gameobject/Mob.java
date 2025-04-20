package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Mob extends Human {
    protected final Texture mobSheet = new Texture(Gdx.files.internal("mobs.png"));
    private int currentDirection;
    private float elapsedTimeForMovement;
    private float elapsedTimeForDirection;
    private float movementInterval;
    private float directionInterval;


    private final Character character;

    public Mob(float x, float y, float speed, Character character) {
        super(x, y, speed);
        this.character = character;
        this.width = 16;
        this.height = 16;
        this.rectangle = new Rectangle(position.x, position.y, 14, 14);

        for (int column = 6; column <= 9; column++) {
            walkDownwardsFrames.add(new TextureRegion(mobSheet, column * width, 4 * height, width, height));
            walkLeftwardsFrames.add(new TextureRegion(mobSheet, column * width, 5 * height, width, height));
            walkRightwardsFrames.add(new TextureRegion(mobSheet, column * width, 6 * height, width, height));
            walkUpwardsFrames.add(new TextureRegion(mobSheet, column * width, 7 * height, width, height));
        }


        float frameDuration = 0.2f;
        walkDownAnimation = new Animation<>(frameDuration, walkDownwardsFrames);
        walkRightAnimation = new Animation<>(frameDuration, walkRightwardsFrames);
        walkUpAnimation = new Animation<>(frameDuration, walkUpwardsFrames);
        walkLeftAnimation = new Animation<>(frameDuration, walkLeftwardsFrames);

        currentAnimation = walkDownAnimation;
        stateTime = 0f;

        currentDirection = MathUtils.random(0, 3);
        elapsedTimeForDirection = 0f;
        elapsedTimeForMovement = 0f;
        movementInterval = 0.2f;
        directionInterval = 5f;

    }

    public void update() {
        elapsedTimeForMovement += Gdx.graphics.getDeltaTime();
        elapsedTimeForDirection += Gdx.graphics.getDeltaTime();
        directionInterval = MathUtils.random(4, 8);

        if (elapsedTimeForDirection >= directionInterval) {
            currentDirection = MathUtils.random(0, 3);
            elapsedTimeForDirection = 0f;
        }

        if (elapsedTimeForMovement >= movementInterval) {
            move(currentDirection);
            elapsedTimeForMovement = 0f;
        }
        stateTime += Gdx.graphics.getDeltaTime();


    }

    /**
     * movement logic, and plays correct animation for each direction
     *
     * @param direction
     */
    public void move(int direction) {
        switch (direction) {
            case 0:
                currentAnimation = walkDownAnimation;
                rectangle.y -= speed;
                break;
            case 1:
                currentAnimation = walkLeftAnimation;
                rectangle.x -= speed;
                break;
            case 2:
                currentAnimation = walkRightAnimation;
                rectangle.x += speed;
                break;
            case 3:
                currentAnimation = walkUpAnimation;
                rectangle.y += speed;
                break;
        }
        stateTime = 0f;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);
        spriteBatch.draw(currentFrame, rectangle.x, rectangle.y);
        update();
    }

    public int getCurrentDirection() {
        return currentDirection;
    }
}
