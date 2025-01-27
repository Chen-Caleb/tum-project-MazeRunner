package de.tum.cit.fop.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.tum.cit.fop.maze.MazeRunnerGame;
import de.tum.cit.fop.maze.screens.ScreenTemplate;

public class StoryScreen extends ScreenTemplate {

    private final MazeRunnerGame game;
    private final Stage stage;

    public StoryScreen(MazeRunnerGame game) {
        super(game);
        this.game = game;
        OrthographicCamera camera = new OrthographicCamera();
        camera.zoom = 1.5f;
        stage = new Stage(new ScreenViewport(camera), game.getSpriteBatch());

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // 添加标题
        Label titleLabel = new Label("The Legend and Origins", game.getSkin(), "title");
        table.add(titleLabel).padBottom(20).row();

        Label storyLabel = new Label(
                "Under the Technical University of Munich lies a hidden facility called the \"Labyrinth of Mist.\" \n" +
                "It was once a famous research center for time and space physics, led by the brilliant Professor Klaus von Wehlmann. \n" +
                "He made great discoveries, but one day an experiment went wrong. The lab was swallowed by a strange mist and \n" +
                "turned into a maze that constantly changes. At the center of the maze is the \"Key of Time,\" a powerful object \n" +
                "that can unlock the secrets of time.\n\n" +
                "Many years later, students at the university found old files about the maze. As one of these students, \n" +
                "your curiosity leads you to explore it. But you quickly learn this is no simple adventure. The maze is full " +
                "of deadly traps and dangerous creatures. It also changes its paths over time. If you don’t find the \"Key of Time\" \n" +
                "in time, you will be trapped in the maze forever.", game.getSkin());
        storyLabel.setWrap(true);

        table.add(storyLabel).width(Gdx.graphics.getWidth() * 0.5f).padBottom(50).row();

        // 添加返回按钮
        TextButton backButton = new TextButton("Return to Menu", game.getSkin());
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.goToMenu();
            }
        });
        table.add(backButton).width(400).padTop(20);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

