package de.tum.cit.fop.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.tum.cit.fop.maze.MazeRunnerGame;

public class InstructionsScreen extends ScreenTemplate {

    private final MazeRunnerGame game;
    private final Stage stage;

    public InstructionsScreen(MazeRunnerGame game) {
        super(game);
        this.game = game;
        stage = new Stage(new ScreenViewport());

        // 创建布局表格
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // 添加标题
        Label titleLabel = new Label("Instruction", game.getSkin(), "title");
        table.add(titleLabel).padBottom(20).row();

        // 添加操作说明
        Label instructions = new Label(
                "Key Instructions:\n" +
                        "- Press M: Zoom view\n" + "- Use Arrow keys: Move the character\n" +
                        "- Hold LeftShift/RightShift + Arrow keys: Run\n" + "- Press Space + Arrow keys: Jump\n" +
                        "- Press A + Arrow keys: Attack\n" + "- Press ESC: Return to main menu"
                ,
                game.getSkin()
        );
        table.add(instructions).padBottom(50).row();

        // 添加返回按钮
        TextButton backButton = new TextButton("Return to Menu", game.getSkin());
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.goToMenu();
            }
        });
        table.add(backButton).width(400).padTop(20);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
