package de.tum.cit.fop.maze.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.tum.cit.fop.maze.MazeRunnerGame;

public class SelectMapScreen extends ScreenTemplate {

    public SelectMapScreen(MazeRunnerGame game) {
        super(game);
        var camera = new OrthographicCamera();
        camera.zoom = 1.5f; // Set camera zoom for a closer view

        Viewport viewport = new ScreenViewport(camera); // Create a viewport with the camera
        stage = new Stage(viewport, game.getSpriteBatch()); // Create a stage for UI elements

        Label titleLabel = new Label("Choose Level", game.getSkin(), "title");
        initialTable();
        table.add(titleLabel).padBottom(200).row();

        TextButton level1 = new TextButton("Level 1", game.getSkin());
        level1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.goToGameLevel1();
            }
        });
        table.add(level1).padBottom(10).row();

        TextButton level2 = new TextButton("Level 2", game.getSkin());
        level2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.goToGameLevel2();
            }
        });
        table.add(level2).padBottom(10).row();

        TextButton level3 = new TextButton("Level 3", game.getSkin());
        level3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.goToGameLevel3();
            }
        });
        table.add(level3).padBottom(10).row();

        TextButton level4 = new TextButton("Level 4", game.getSkin());
        level4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.goToGameLevel4();
            }
        });
        table.add(level4).padBottom(10).row();

        TextButton level5 = new TextButton("Level 5", game.getSkin());
        level5.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor){
                game.goToGameLevel5();
            }
        });
        table.add(level5).padBottom(10).row();


        returnToMenu();

    }

}
