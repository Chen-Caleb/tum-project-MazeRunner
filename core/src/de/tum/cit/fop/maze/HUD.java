package de.tum.cit.fop.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class HUD {
    private final Stage stage;
    private final Array<Image> hearts;
    private final Label timer;
    private final Label numberOfKeys;
    private Skin skin;
    private BitmapFont font;
    private float elapsedTime;

    /**
     * gets the skin from correct file
     * sets font, font size, etc.
     */

    public HUD() {
        skin = new Skin(Gdx.files.internal("craft/craftacular-ui.json"));
        font = skin.getFont("font");
        int fontSize = 24;
        font.getData().setScale(fontSize / font.getCapHeight());
        elapsedTime = 0f;
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        Table heartTable = new Table();
        heartTable.setFillParent(true);
        heartTable.top().left().padTop(Gdx.graphics.getHeight() * 0.05f);

        /**
         * adds maximum amount of hearts allowed
         * also shows how to display all hearts on the screen
         */
        hearts = new Array<>();

        int maxHearts = 10;

        for (int i = 0; i < maxHearts; i++) {
            TextureRegion heartTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("objects.png")), 4 * 16, 0, 16, 16);
            Image heartImage = new Image(heartTextureRegion);
            heartImage.setScale(4f);
            hearts.add(heartImage);
            heartTable.add(heartImage).pad(Gdx.graphics.getWidth() * 0.02f);
        }

        stage.addActor(heartTable);

        /**
         * does the same for the keys
         */

        Table keyTable = new Table();
        keyTable.setFillParent(true);
        keyTable.top().left().padTop(Gdx.graphics.getHeight() * 0.15f);
        TextureRegion keyTextureRegion = new TextureRegion(new Texture(Gdx.files.internal("objects.png")), 11 * 16, 3 * 16, 16, 16);
        Image keyImage = new Image(keyTextureRegion);
        keyImage.scaleBy(4f);
        keyTable.add(keyImage).pad(Gdx.graphics.getWidth() * 0.015f);
        numberOfKeys = new Label("0", new Label.LabelStyle(font, Color.WHITE));
        keyTable.add(numberOfKeys).padLeft(Gdx.graphics.getWidth() * 0.01f);
        stage.addActor(keyTable);

        Table timerTable = new Table();
        timerTable.setFillParent(true);
        timerTable.top().right().padTop(Gdx.graphics.getHeight() * 0.05f);
        // i want to increase the font size, but to do that we have to import the new skin
        timer = new Label("Score: 0", new Label.LabelStyle(font, Color.WHITE));
        timerTable.add(timer).pad(30f);
        stage.addActor(timerTable);


    }

    /**
     * logic for health system, timer, and keys system
     * @param currentHealth
     */
    public void updateHealth(int currentHealth) {
        for (int i = 0; i < hearts.size; i++) {
            hearts.get(i).setVisible(i < currentHealth);
        }
    }

    /**
     * updates the current score
     * @param delta indicates how much time has passed
     */
    public void updateTimer(float delta) {
        elapsedTime += delta * 100;
        timer.setText("Time: " + (int) elapsedTime);
    }

    public void updateKeys(int keysCollected) {
        numberOfKeys.setText(keysCollected);
    }

    /**
     * gets the stage
     * @return
     */

    public Stage getStage() {
        return stage;
    }
}
