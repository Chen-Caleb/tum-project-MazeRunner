package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;


public class Character extends Human {
    protected float runningSpeed;
    protected final Texture characterSheet = new Texture(Gdx.files.internal("character.png"));

    private boolean upInverse, leftInverse, downInverse, rightInverse;
    private boolean upLeftInverse, upRightInverse, downLeftInverse, downRightInverse;

    /**
     * running animation
     */
    protected Array<TextureRegion> runDownwardsFrames = new Array<>(TextureRegion.class);
    protected Array<TextureRegion> runRightwardsFrames = new Array<>(TextureRegion.class);
    protected Array<TextureRegion> runUpwardsFrames = new Array<>(TextureRegion.class);
    protected Array<TextureRegion> runLeftwardsFrames = new Array<>(TextureRegion.class);

    // running animations
    protected Animation<TextureRegion> characterDownRunningAnimation;
    protected Animation<TextureRegion> characterRightRunningAnimation;
    protected Animation<TextureRegion> characterUpRunningAnimation;
    protected Animation<TextureRegion> characterLeftRunningAnimation;

    /**
     * Attack animation
     */
    protected Array<TextureRegion> AttackDownwardsFrames = new Array<>(TextureRegion.class);
    protected Array<TextureRegion> AttackRightwardsFrames = new Array<>(TextureRegion.class);
    protected Array<TextureRegion> AttackUpwardsFrames = new Array<>(TextureRegion.class);
    protected Array<TextureRegion> AttackLeftwardsFrames = new Array<>(TextureRegion.class);

    // Attack animations
    protected Animation<TextureRegion> characterDownAttackAnimation;
    protected Animation<TextureRegion> characterRightAttackAnimation;
    protected Animation<TextureRegion> characterUpAttackAnimation;
    protected Animation<TextureRegion> characterLeftAttackAnimation;

    /**
     * jumping animation
     */
    protected Array<TextureRegion> jumpDownwardsFrames = new Array<>(TextureRegion.class);
    protected Array<TextureRegion> jumpRightwardsFrames = new Array<>(TextureRegion.class);
    protected Array<TextureRegion> jumpUpwardsFrames = new Array<>(TextureRegion.class);
    protected Array<TextureRegion> jumpLeftwardsFrames = new Array<>(TextureRegion.class);
    // jumping animations
    protected Animation<TextureRegion> characterJumpDownAnimation;
    protected Animation<TextureRegion> characterJumpRightAnimation;
    protected Animation<TextureRegion> characterJumpUpAnimation;
    protected Animation<TextureRegion> characterJumpLeftAnimation;



    public Character(float x, float y, float speed, float runningSpeed) {
        super(x, y, speed);
        this.runningSpeed = runningSpeed;
        width = 16;
        height = 32;

        rectangle = new Rectangle(position.x, position.y, width, height - 16);

        /**
         * animation logic
         */
        int NumberOfDirections = 4;
        for (int column = 0; column < NumberOfDirections; column++) {
            walkDownwardsFrames.add(new TextureRegion(characterSheet, column * Character.width, 0, Character.width, Character.height));
            walkRightwardsFrames.add(new TextureRegion(characterSheet, column * Character.width, Character.height, Character.width, Character.height));
            walkUpwardsFrames.add(new TextureRegion(characterSheet, column * Character.width, 2 * Character.height, Character.width, Character.height));
            walkLeftwardsFrames.add(new TextureRegion(characterSheet, column * Character.width, 3 * Character.height, Character.width, Character.height));

            runDownwardsFrames.add(new TextureRegion(characterSheet, (9 + column) * width, 0, width, height));
            runRightwardsFrames.add(new TextureRegion(characterSheet, (9 + column) * width, height, width, height));
            runUpwardsFrames.add(new TextureRegion(characterSheet, (9 + column) * width, 2 * height, width, height));
            runLeftwardsFrames.add(new TextureRegion(characterSheet, (9 + column) * width, 3 * height, width, height));

            AttackDownwardsFrames.add(new TextureRegion(characterSheet, column * 32 + 7, 4 * height, 20, height));
            AttackUpwardsFrames.add(new TextureRegion(characterSheet, column * 32 + 7, 5 * height,20, height));
            AttackRightwardsFrames.add(new TextureRegion(characterSheet, column * 32 +7, 6 * height, 20, height));
            AttackLeftwardsFrames.add(new TextureRegion(characterSheet, column * 32 + 7, 7 * height, 20, height));

        }
        for (int column = 0; column < 3; column++) {
            jumpDownwardsFrames.add(new TextureRegion(characterSheet, (5 + column) * width, 0, width, height));
            jumpRightwardsFrames.add(new TextureRegion(characterSheet, (5 + column) * width, height, width, height));
            jumpUpwardsFrames.add(new TextureRegion(characterSheet, (5+ column) * width, 2 * height, width, height));
            jumpLeftwardsFrames.add(new TextureRegion(characterSheet, (5 + column) * width, 3 * height, width, height));
        }

        /**
         * gets the correct frames for animation
         */
        float frameDuration = 0.15f;
        walkDownAnimation = new Animation<>(frameDuration, walkDownwardsFrames);
        walkRightAnimation = new Animation<>(frameDuration, walkRightwardsFrames);
        walkUpAnimation = new Animation<>(frameDuration, walkUpwardsFrames);
        walkLeftAnimation = new Animation<>(frameDuration, walkLeftwardsFrames);

        currentAnimation = walkDownAnimation;
        stateTime = 0f;

        characterDownRunningAnimation = new Animation<>(frameDuration, runDownwardsFrames);
        characterRightRunningAnimation = new Animation<>(frameDuration, runRightwardsFrames);
        characterUpRunningAnimation = new Animation<>(frameDuration, runUpwardsFrames);
        characterLeftRunningAnimation = new Animation<>(frameDuration, runLeftwardsFrames);

        characterDownAttackAnimation =new Animation<>(0.05f, AttackDownwardsFrames);
        characterRightAttackAnimation =new Animation<>(0.05f, AttackRightwardsFrames);
        characterUpAttackAnimation =new Animation<>(0.05f, AttackUpwardsFrames);
        characterLeftAttackAnimation =new Animation<>(0.05f, AttackLeftwardsFrames);

        characterJumpDownAnimation = new Animation<>(0.1f, jumpDownwardsFrames);
        characterJumpRightAnimation = new Animation<>(0.1f, jumpRightwardsFrames);
        characterJumpUpAnimation = new Animation<>(0.1f, jumpUpwardsFrames);
        characterJumpLeftAnimation = new Animation<>(0.1f, jumpLeftwardsFrames);


        currentAnimation = walkDownAnimation;
        stateTime = 0f;
    }

    public void update() {
        handleInput();

        if (!Gdx.input.isKeyPressed(Input.Keys.DOWN) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                !Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            stateTime = 0f;
        }

        stateTime += Gdx.graphics.getDeltaTime();
    }


    private void handleInput() {
        if (upLeftInverse) {
            upLeftInverse();
        } else if (upRightInverse) {
            upRightInverse();
        } else if (downLeftInverse) {
            downLeftInverse();
        } else if (downRightInverse) {
            downRightInverse();
        } else if (upInverse) {
            upInverse();
        } else if (leftInverse) {
            leftInverse();
        } else if (downInverse) {
            downInverse();
        } else if (rightInverse) {
            rightInverse();
        } else {
            // walking Animations
            playerMoveDownwards();
            playerMoveRightwards();
            playerMoveUpwards();
            playerMoveLeftwards();

        //attack Animations
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            currentAnimation = characterDownAttackAnimation;
            rectangle.y -= speed * 0.01f;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            currentAnimation = characterRightAttackAnimation;
            rectangle.x += speed * 0.01f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            currentAnimation = characterUpAttackAnimation;
            rectangle.y += speed * 0.01f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.A)) {
            currentAnimation = characterLeftAttackAnimation;
            rectangle.x -= speed * 0.01f;
        }


        // running Animations
        /**
         * running logic; animation + speed increase
         */
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                currentAnimation = characterDownRunningAnimation;
                rectangle.y -= runningSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                currentAnimation = characterRightRunningAnimation;
                rectangle.x += runningSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                currentAnimation = characterUpRunningAnimation;
                rectangle.y += runningSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
                currentAnimation = characterLeftRunningAnimation;
                rectangle.x -= runningSpeed;
            }
        }

    }

        /**
         * jumping logic; animation + movement
         */
        // jumping
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            currentAnimation = characterJumpDownAnimation;
            rectangle.y -= speed * 0.25f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            currentAnimation = characterJumpRightAnimation;
            rectangle.x += speed * 0.25f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            currentAnimation = characterJumpUpAnimation;
            rectangle.y += speed * 0.25f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            currentAnimation = characterJumpLeftAnimation;
            rectangle.x -= speed * 0.25f;
        }


    }


    private void upInverse() {
        playerMoveDownwards();
        playerMoveRightwards();
        playerUpwards();
        playerMoveLeftwards();
    }

    private void leftInverse() {
        playerMoveDownwards();
        playerMoveRightwards();
        playerMoveUpwards();
        playerLeftwards();
    }

    private void downInverse() {
        playerDownwards();
        playerMoveRightwards();
        playerMoveUpwards();
        playerMoveLeftwards();
    }

    private void rightInverse() {
        playerMoveDownwards();
        playerRightwards();
        playerMoveUpwards();
        playerMoveLeftwards();
    }

    private void upLeftInverse() {
        playerMoveDownwards();
        playerMoveRightwards();
        playerUpwards();
        playerLeftwards();
    }

    private void upRightInverse() {
        playerMoveDownwards();
        playerRightwards();
        playerUpwards();
        playerMoveLeftwards();
    }


    private void downLeftInverse() {
        playerDownwards();
        playerMoveRightwards();
        playerMoveUpwards();
        playerLeftwards();
    }

    private void downRightInverse() {
        playerDownwards();
        playerRightwards();
        playerMoveUpwards();
        playerMoveLeftwards();
    }


    /**
     * normal movement logic through press Keys
     */

    private void playerMoveDownwards() {
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            currentAnimation = walkDownAnimation;
            this.rectangle.y -= speed;
        }
    }

    private void playerMoveRightwards() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentAnimation = walkRightAnimation;
            this.rectangle.x += speed;
        }
    }

    private void playerMoveUpwards() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            currentAnimation = walkUpAnimation;
            this.rectangle.y += speed;
        }
    }

    private void playerMoveLeftwards() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentAnimation = walkLeftAnimation;
            this.rectangle.x -= speed;
        }
    }

    private void playerDownwards() {
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            currentAnimation = walkDownAnimation;
        }
    }

    private void playerRightwards() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentAnimation = walkRightAnimation;
        }
    }

    private void playerUpwards() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            currentAnimation = walkUpAnimation;
        }
    }

    private void playerLeftwards() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentAnimation = walkLeftAnimation;
        }
    }


    public void render(SpriteBatch spriteBatch) {
        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);
        spriteBatch.draw(currentFrame, this.rectangle.x, this.rectangle.y);
    }

    public void setUpInverse(boolean upInverse) {
        this.upInverse = upInverse;
    }


    public void setLeftInverse(boolean leftInverse) {
        this.leftInverse = leftInverse;
    }


    public void setDownInverse(boolean downInverse) {
        this.downInverse = downInverse;
    }


    public void setRightInverse(boolean rightInverse) {
        this.rightInverse = rightInverse;
    }


    public void setUpLeftInverse(boolean upLeftInverse) {
        this.upLeftInverse = upLeftInverse;
    }


    public void setUpRightInverse(boolean upRightInverse) {
        this.upRightInverse = upRightInverse;
    }


    public void setDownLeftInverse(boolean downLeftInverse) {
        this.downLeftInverse = downLeftInverse;
    }


    public void setDownRightInverse(boolean downRightInverse) {
        this.downRightInverse = downRightInverse;
    }
}

