package de.tum.cit.fop.maze.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.LinkedHashMap;
import java.util.Map;

public class Character extends Human {
    protected float runningSpeed;
    protected final Texture characterSheet = new Texture(Gdx.files.internal("character.png"));
    protected int NumberOfDirections = 4;

    private boolean upInverse, leftInverse, downInverse, rightInverse;
    private boolean upLeftInverse, upRightInverse, downLeftInverse, downRightInverse;


    public Character(float x, float y, float speed, float runningSpeed) {
        super(x, y, speed);
        this.runningSpeed = runningSpeed;

        /**
         * animation logic
         */
        for (int column = 0; column < NumberOfDirections; column++) {
            walkDownwardsFrames.add(new TextureRegion(characterSheet, column * Character.width, 0, Character.width, Character.height));
            walkRightwardsFrames.add(new TextureRegion(characterSheet, column * Character.width, Character.height, Character.width, Character.height));
            walkUpwardsFrames.add(new TextureRegion(characterSheet, column * Character.width, 2 * Character.height, Character.width, Character.height));
            walkLeftwardsFrames.add(new TextureRegion(characterSheet, column * Character.width, 3 * Character.height, Character.width, Character.height));
        }

        /**
         * gets the correct frames for animation
                */
        float frameDuration = 0.1f;
        walkDownAnimation = new Animation<>(frameDuration, walkDownwardsFrames);
        walkRightAnimation = new Animation<>(frameDuration, walkRightwardsFrames);
        walkUpAnimation = new Animation<>(frameDuration, walkUpwardsFrames);
        walkLeftAnimation = new Animation<>(frameDuration, walkLeftwardsFrames);

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


//    private void handleInput() {
//        /**
//         * movement logic
//         */
//        // 用 Map 存储状态变量和对应方法
//        Map<Boolean, Runnable> inverseActions = new LinkedHashMap<>();
//        inverseActions.put(upLeftInverse, this::upLeftInverse);
//        inverseActions.put(upRightInverse, this::upRightInverse);
//        inverseActions.put(downLeftInverse, this::downLeftInverse);
//        inverseActions.put(downRightInverse, this::downRightInverse);
//        inverseActions.put(upInverse, this::upInverse);
//        inverseActions.put(leftInverse, this::leftInverse);
//        inverseActions.put(downInverse, this::downInverse);
//        inverseActions.put(rightInverse, this::rightInverse);
//
//        // 遍历 Map，找到第一个满足条件的状态并执行对应方法
//        for (Map.Entry<Boolean, Runnable> entry : inverseActions.entrySet()) {
//            if (entry.getKey()) {
//                entry.getValue().run();
//                return; // 执行完毕后退出
//            }
//        }
//
//        // 如果没有任何逆向状态触发，执行默认的行走动画
//        handleWalkingAnimations();
//    }
//
//    /**
//     * Handles walking animations for all directions.
//     */
//    private void handleWalkingAnimations() {
//        playerMoveDownwards();
//        playerMoveRightwards();
//        playerMoveUpwards();
//        playerMoveLeftwards();
//    }

    private void handleInput(){
        if (upLeftInverse){
            upLeftInverse();
        }else if (upRightInverse) {
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
        }else {
            // walking Animations
            playerMoveDownwards();
            playerMoveRightwards();
            playerMoveUpwards();
            playerMoveLeftwards();
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
}

