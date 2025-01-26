package de.tum.cit.fop.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.tum.cit.fop.maze.MazeRunnerGame;

public abstract class GameOverScreen extends ScreenTemplate{
    protected float scoreWidth;
    protected String victory = "Victory";
    protected String winner = "You are the Winner!";
    protected String defeat = "Defeat";
    protected String loser = "you lose the game";
    protected int playerScore;
    protected String score;



    protected GameOverScreen(MazeRunnerGame game) {
        super(game);
        playerScore = 5;
        score = "Your score is: " + playerScore;
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {
        super.show();

        table.setPosition(0, -Gdx.graphics.getHeight() * 0.2f);
    }

    @Override
    public void render(float delta) {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    protected void drawText(SpriteBatch spriteBatch, String victoryOrDefeat, String winnerOrLoser, String score) {
        titleLayout = new GlyphLayout(titleFont, victoryOrDefeat);
        titleWidth = titleLayout.width;

        textLayout = new GlyphLayout(victoryFont, winnerOrLoser);
        textWidth = textLayout.width;

        scoreLayout = new GlyphLayout(victoryFont, score);
        scoreWidth = scoreLayout.width;

        spriteBatch.begin();
        titleFont.draw(spriteBatch, victoryOrDefeat, (width - titleWidth) / 2f, height * 0.9f);
        victoryFont.draw(spriteBatch, winnerOrLoser, (width - textWidth) / 2f, height * 0.7f);
        victoryFont.draw(spriteBatch, score, (width - scoreWidth) / 2f, height * 0.6f);
        spriteBatch.end();
    }





}
