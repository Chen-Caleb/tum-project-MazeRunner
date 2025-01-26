package de.tum.cit.fop.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.tum.cit.fop.maze.MazeRunnerGame;

public class VictoryScreen extends GameOverScreen{

    public VictoryScreen(MazeRunnerGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show(); // 调用父类的 show 方法
        returnToMenu(); // 添加返回菜单按钮
        playAgain(); // 添加重玩按钮

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        drawText(spriteBatch, victory, winner, score);
    }



}
