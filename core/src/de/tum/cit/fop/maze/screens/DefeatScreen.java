package de.tum.cit.fop.maze.screens;

import de.tum.cit.fop.maze.MazeRunnerGame;

public class DefeatScreen extends GameOverScreen{
    private int playerScore;
    private String score;

    public DefeatScreen(MazeRunnerGame game) {
        super(game);
        playerScore = 0;
        score = "Your score is: " + playerScore;
    }

    @Override
    public void show() {
        super.show();
        returnToMenu(); // 添加返回菜单按钮
        playAgain(); // 添加重玩按钮

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        drawText(spriteBatch, defeat, loser, score);
    }
}
