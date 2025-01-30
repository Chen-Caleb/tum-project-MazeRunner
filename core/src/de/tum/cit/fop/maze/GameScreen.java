package de.tum.cit.fop.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.tum.cit.fop.maze.gameobject.*;

import java.util.Iterator;

/**
 * The GameScreen class is responsible for rendering the gameplay screen.
 * It handles the game logic and rendering of the game elements.
 */
public class GameScreen implements Screen {

    private final MazeRunnerGame game;
    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;
    private final BitmapFont font;
    private final Maze maze;
    private final HUD hud;
    public int currentHealth;
    private float invulnerabilityTimer;
    private boolean invulnerable;
    private int keysCollected = 0;
    private boolean doorUnlocked = false;

    private Music backgroundMusic;
    private Sound fireSound, ghostSound, takeDamageSound, collisionSound, keySound, heartSound , victorySound, defeatSound;
    private float soundTimer;


    /**
     * Constructor for GameScreen. Sets up the camera and font.
     *
     * @param game The main game class, used to access global resources and methods.
     */
    public GameScreen(MazeRunnerGame game, String level) {
        this.game = game;
        this.maze = new Maze(level);
        maze.readMoreProperties();
        hud = new HUD();
        currentHealth = 5;


        // Create and configure the camera for the game view
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.zoom = 0.75f;

        // Get the font from the game's skin
        font = game.getSkin().getFont("font");

    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(maze.getCharacter().getRectangle().x, maze.getCharacter().getRectangle().y, 0);
        camera.zoom = 0.5f;
        camera.update();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("dungeon.wav"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.3f);
        backgroundMusic.play();

        fireSound = Gdx.audio.newSound(Gdx.files.internal("sound/fire.wav"));
        ghostSound = Gdx.audio.newSound(Gdx.files.internal("sound/ghost.wav"));
        takeDamageSound = Gdx.audio.newSound(Gdx.files.internal("sound/takeDamage.mp3"));
        collisionSound = Gdx.audio.newSound(Gdx.files.internal("sound/collision.wav"));
        keySound = Gdx.audio.newSound(Gdx.files.internal("sound/key.wav"));
        heartSound = Gdx.audio.newSound(Gdx.files.internal("sound/heart.wav"));
        victorySound = Gdx.audio.newSound(Gdx.files.internal("sound/Victory.wav"));
        defeatSound = Gdx.audio.newSound(Gdx.files.internal("sound/defeat.mp3"));


    }


    // Screen interface methods with necessary functionality
    @Override
    public void render(float delta) {

        soundTimer += Gdx.graphics.getDeltaTime();
        /**
         *  match input to functions
         */

        if (!game.isGamePaused()) {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
                game.goToMenu(); // Check for escape key press to go back to the menu
            if (currentHealth == 0) {
                playOneSound(defeatSound);
                game.goToDefeatScreen();

            }

            //ScreenUtils.clear(0, 0, 0, 1); // Clear the screen
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            spriteBatch.setProjectionMatrix(camera.combined);

            spriteBatch.begin();

            /**
             * put the correct sprite for walls, entries, and exit points
             * same for the mobs and traps
             */

            for (Wall wall : maze.getWalls()) {
                wall.render(spriteBatch);
            }
            maze.getEntrance().render(spriteBatch);
            for (Exit exit : maze.getExits()) {
                exit.render(spriteBatch);
            }
            for (Trap trap : maze.getTraps()) {
                trap.render(spriteBatch);
            }
            for (Mob mob : maze.getMobs()) {
                mob.render(spriteBatch);
            }

            /**
             * add all keys on the map
             * make key dissapear, and correctly count when player collects it
             */

            Iterator<Key> keyIterator = maze.getKeys().iterator();
            while (keyIterator.hasNext()) {
                Key key = keyIterator.next();

                if (maze.getCharacter().getRectangle().overlaps(key.getRectangle())) {
                    playOneSound(keySound);
                    keysCollected++;
                    hud.updateKeys(keysCollected);
                    if (keysCollected >= 1) {
                        doorUnlocked = true;
                    }
                    keyIterator.remove();
                } else {
                    key.render(spriteBatch);
                }
            }

            Iterator<Heart> heartIterator = maze.getHearts().iterator();
            while (heartIterator.hasNext()) {
                Heart heart = heartIterator.next();

                if (maze.getCharacter().getRectangle().overlaps(heart.getRectangle())) {
                    playOneSound(heartSound);
                    invulnerableForHeart(2f);
                    heartIterator.remove();
                } else {
                    heart.render(spriteBatch);
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                invulnerableForAttack(1f);
            }

            maze.getCharacter().render(spriteBatch);
            spriteBatch.end();






            /**
             * Character and wall collision
             */
            for (Wall wall : maze.getWalls()) {
                if (maze.getCharacter().getRectangle().overlaps(wall.getSides()[2]) && maze.getCharacter().getRectangle().overlaps(wall.getSides()[1])) {
                    playOneSound(collisionSound);
                    maze.getCharacter().setDownLeftInverse(true);
                } else if (maze.getCharacter().getRectangle().overlaps(wall.getSides()[2]) && maze.getCharacter().getRectangle().overlaps(wall.getSides()[3])) {
                    playOneSound(collisionSound);
                    maze.getCharacter().setDownRightInverse(true);
                } else if (maze.getCharacter().getRectangle().overlaps(wall.getSides()[0]) && maze.getCharacter().getRectangle().overlaps(wall.getSides()[1])) {
                    playOneSound(collisionSound);
                    maze.getCharacter().setUpLeftInverse(true);
                } else if (maze.getCharacter().getRectangle().overlaps(wall.getSides()[0]) && maze.getCharacter().getRectangle().overlaps(wall.getSides()[3])) {
                    playOneSound(collisionSound);
                    maze.getCharacter().setUpRightInverse(true);
                } else if (maze.getCharacter().getRectangle().overlaps(wall.getSides()[0])) {
                    playOneSound(collisionSound);
                    maze.getCharacter().setUpInverse(true);
                } else if (maze.getCharacter().getRectangle().overlaps(wall.getSides()[1])) {
                    //playOneSound(collisionSound);
                    maze.getCharacter().setLeftInverse(true);
                } else if (maze.getCharacter().getRectangle().overlaps(wall.getSides()[2])) {
                    //playOneSound(collisionSound);
                    maze.getCharacter().setDownInverse(true);
                } else if (maze.getCharacter().getRectangle().overlaps(wall.getSides()[3])) {
                    //playOneSound(collisionSound);
                    maze.getCharacter().setRightInverse(true);
                }
            }

            maze.getCharacter().update();
            maze.getCharacter().setDownLeftInverse(false);
            maze.getCharacter().setDownRightInverse(false);
            maze.getCharacter().setUpLeftInverse(false);
            maze.getCharacter().setUpRightInverse(false);
            maze.getCharacter().setUpInverse(false);
            maze.getCharacter().setLeftInverse(false);
            maze.getCharacter().setDownInverse(false);
            maze.getCharacter().setRightInverse(false);

            /**
             * if all keys are collected, unlock all exits and allow Character to escape
             * then open victory screen
             */
            if (doorUnlocked) {
                for (Exit exit : maze.getExits()) {
                    if (maze.getCharacter().getRectangle().overlaps(exit.getRectangle())) {
                        playOneSound(victorySound);
                        game.goToVictoryScreen();
                    }
                }
            }
            /**
             * when Character hits a trap or a mob, give them a short period of invulnerability
             */
            for (Trap trap : maze.getTraps()) {
                if (maze.getCharacter().getRectangle().overlaps(trap.getRectangle())) {
                    playTwoSounds(fireSound, takeDamageSound);
                    invulnerable(2f);
                }
            }


            /**
             * mob and wall collision, if it hits a wall then it goes in the opposite direction.
             * same is added for the exits
             */
            for (Mob mob : maze.getMobs()) {
                if (maze.getCharacter().getRectangle().overlaps(mob.getRectangle())) {
                    playTwoSounds(ghostSound, takeDamageSound);
                    invulnerable(1f);
                }

                /**
                 * mob and wall collision
                 * is mob hits a wall, go the opposite direction
                 */
                for (Wall wall : maze.getWalls()) {
                    if (mob.getRectangle().overlaps(wall.getSides()[0])
                            || mob.getRectangle().overlaps(wall.getSides()[1])
                            || mob.getRectangle().overlaps(wall.getSides()[2])
                            || mob.getRectangle().overlaps(wall.getSides()[3])) {
                        switch (mob.getCurrentDirection()) {
                            case 0:
                                mob.move(3);
                                break;
                            case 1:
                                mob.move(2);
                                break;
                            case 2:
                                mob.move(1);
                                break;
                            case 3:
                                mob.move(0);
                                break;
                        }
                    }
                }
                /**
                 * same collision logic for exits as well
                 */
                for (Mob mob1 : maze.getMobs()) {
                    for (Exit exit1 : maze.getExits()) {
                        if (mob1.getRectangle().overlaps(exit1.getRectangle())) {
                            switch (mob.getCurrentDirection()) {
                                case 0:
                                    mob.move(3);
                                    break;
                                case 1:
                                    mob.move(2);
                                    break;
                                case 2:
                                    mob.move(1);
                                    break;
                                case 3:
                                    mob.move(0);
                                    break;
                            }
                        }
                    }
                }
            }
            renderCamera();
            hud.updateTimer(delta);
            renderHUD();
            invulnerabilityTimer += Gdx.graphics.getDeltaTime();
            //game.getSpriteBatch().end(); // Important to call this after drawing everything
        }else {
            //pauseScreen.show();
            //pauseScreen.render(delta);
            //pauseScreen.hide();
        }
    }
    /**
     * set camera zoom
     * also allow player to view the whole map by pressing "M"
     */
    private void renderCamera() {
        camera.position.x = maze.getCharacter().getRectangle().x;
        camera.position.y = maze.getCharacter().getRectangle().y;

        float zoomX = Gdx.graphics.getWidth() / maze.getMapWidth() ;
        float zoomY = Gdx.graphics.getHeight() / maze.getMapHeight() ;
        float zoom = Math.min(zoomX, zoomY);
        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            camera.zoom = zoom / 3f; // 地图缩小，显示更多地形
            camera.position.set(maze.getMapWidth() / 2, maze.getMapHeight() / 2, 0);
        } else {
            camera.zoom = 0.2f;
        }
        camera.update();
    }


    /**
     * displays the HUD on the screen
     */
    public void renderHUD() {
        hud.getStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1000 ));
        hud.updateHealth(currentHealth);
        hud.getStage().draw();
    }


    private void playOneSound(Sound sound) {
        if (soundTimer >= 1f) {
            sound.play();
            soundTimer = 0f;
        }
    }

    private void playTwoSounds(Sound sound1, Sound sound2) {
        if (soundTimer >= 1) {
            sound1.play();
            sound2.play();
            soundTimer = 0f;
        }
    }



    /**
     * invulnerability logic added here
     * @param time
     */
    public void invulnerable(float time) {
        if (!invulnerable) {
            currentHealth--;
            invulnerable = true;
        }

        if (invulnerabilityTimer >= time) {
            invulnerable = false;
            invulnerabilityTimer = 0f;
        }
    }

    public void invulnerableForHeart(float time) {
        if (!invulnerable) {
            currentHealth++;
            invulnerable = true;
        }

        if (invulnerabilityTimer >= time) {
            invulnerable = false;
            invulnerabilityTimer = 0f;
        }
    }

    public void invulnerableForAttack(float time) {
        if (!invulnerable) {
            invulnerable = true;
        }

        if (invulnerabilityTimer >= time) {
            invulnerable = false;
            invulnerabilityTimer = 0f;
        }
    }




    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }


    @Override
    public void hide() {
        backgroundMusic.stop();
        backgroundMusic.dispose();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        game.dispose();
    }
}
