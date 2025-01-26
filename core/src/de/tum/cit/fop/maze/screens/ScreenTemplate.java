package de.tum.cit.fop.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import de.tum.cit.fop.maze.MazeRunnerGame;

public abstract class ScreenTemplate implements Screen {
    protected final MazeRunnerGame game;
    protected Stage stage;
    protected float width = Gdx.graphics.getWidth();
    protected float height = Gdx.graphics.getHeight();
    protected Table table;
    protected Texture background;
    protected SpriteBatch spriteBatch;
    protected final Skin skin;

    protected GlyphLayout titleLayout;
    protected GlyphLayout textLayout;
    protected GlyphLayout scoreLayout;

    protected BitmapFont titleFont;
    protected BitmapFont victoryFont;
    protected float titleWidth;
    protected float textWidth;


    protected ScreenTemplate(MazeRunnerGame game) {
        this.game = game;
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("craft/craftacular-ui.json"));


        titleFont = skin.getFont("title");
        int titleFontSize = 96;
        titleFont.getData().setScale(titleFontSize / titleFont.getCapHeight());

        victoryFont = skin.getFont("title");
        int victoryFontSize = 36;
        victoryFont.getData().setScale(victoryFontSize / victoryFont.getCapHeight());


    }


    public void returnToMenu() {
        TextButton returnToMenu = new TextButton("Return to Menu", game.getSkin());
        returnToMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.goToMenu();
            }
        });
        table.add(returnToMenu).padBottom(10).row();
    }

    public void playAgain() {
        TextButton returnToMenu = new TextButton("play again", game.getSkin());
        returnToMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SelectMapScreen(game));
            }
        });
        table.add(returnToMenu).padBottom(10).row();
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);


        table.setPosition(table.getX(),-Gdx.graphics.getHeight() * 0.05f);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // 清屏
        stage.act(delta); // 更新舞台
        stage.draw(); // 绘制舞台
    }

    @Override
    public void resize(int i, int i1) {

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
        stage.dispose(); // 释放舞台资源
    }

    public void initialTable() {
        if (table == null) {
            System.out.println("Initializing table...");
            table = new Table();
            table.setFillParent(true);
            stage.addActor(table);
        } else {
            System.out.println("Table is already initialized.");
        }
    }
}
