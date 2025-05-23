package de.tum.cit.fop.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.tum.cit.fop.maze.screens.InstructionsScreen;
import de.tum.cit.fop.maze.screens.SelectMapScreen;
import de.tum.cit.fop.maze.screens.StoryScreen;

/**
 * The MenuScreen class is responsible for displaying the main menu of the game.
 * It extends the LibGDX Screen class and sets up the UI components for the menu.
 */
public class MenuScreen implements Screen {

    private final Stage stage;
    protected Texture background;
    protected Music music;

    /**
     * Constructor for MenuScreen. Sets up the camera, viewport, stage, and UI elements.
     *
     * @param game The main game class, used to access global resources and methods.
     */
    public MenuScreen(MazeRunnerGame game) {
        var camera = new OrthographicCamera();
        camera.zoom = 1.5f; // Set camera zoom for a closer view

        Viewport viewport = new ScreenViewport(camera); // Create a viewport with the camera
        stage = new Stage(viewport, game.getSpriteBatch()); // Create a stage for UI elements

        Table table = new Table(); // Create a table for layout
        table.setFillParent(true); // Make the table fill the stage
        stage.addActor(table); // Add the table to the stage


        //Music
        music = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);
        music.play();

        // Add a label as a title
        //table.add(new Label("Welcome TO Maze Runner ! ", game.getSkin(), "title")).padBottom(80).row();

        Label titleLabel = new Label("Welcome TO Maze Runner !",game.getSkin(),"title");
        table.add(titleLabel).padBottom(200).row();


        //Create and add a button to go to the map select screen
        TextButton selectMapButton = new TextButton("Go To Game", game.getSkin());
        table.add(selectMapButton).width(300).padBottom(10).row();
        selectMapButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SelectMapScreen(game)); // 跳转到新界面
            }
        });

        //Creat exit Button
        TextButton exitButton = new TextButton("Exit Game", game.getSkin());
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(exitButton).width(300).padBottom(10).row();

        //Creat instruction Button
        TextButton instructionsButton = new TextButton("Instruction", game.getSkin());
        instructionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InstructionsScreen(game));
            }
        });
        table.add(instructionsButton).width(300).padBottom(10).row();


        //Creat readMe Button
        TextButton readMeButton = new TextButton("Read Me", game.getSkin());
        readMeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StoryScreen(game));
            }
        });
        table.add(readMeButton).width(300).padBottom(20).row();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear the screen
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); // Update the stage
        stage.draw(); // Draw the stage
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true); // Update the stage viewport on resize
    }

    @Override
    public void dispose() {
        // Dispose of the stage when screen is disposed
        stage.dispose();
    }

    @Override
    public void show() {
        // Set the input processor so the stage can receive input events
        Gdx.input.setInputProcessor(stage);
    }

    // The following methods are part of the Screen interface but are not used in this screen.
    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        music.stop();
        music.dispose();
    }


}
