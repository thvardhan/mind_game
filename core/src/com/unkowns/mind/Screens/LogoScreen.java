package com.unkowns.mind.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.unkowns.mind.MindGame;

public class LogoScreen implements Screen {

    final MindGame game;
    final Texture logo;
    final Rectangle rect;
    private float alpha;
    private boolean trigger;

    public LogoScreen(MindGame game) {
        this.game = game;
        this.logo = game.assetManager.get("texture/team_unknowns.png");
        this.rect = new Rectangle();
        rect.set(0, 0, 1366, 768);
        alpha = 1;
        trigger = false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        game.display();

        game.batch.begin();
        game.batch.draw(logo, game.camera.position.x - (logo.getWidth() / 2),
                game.camera.position.y - (logo.getWidth() / 2));
        game.batch.end();


        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        game.shape.begin(ShapeRenderer.ShapeType.Filled);
        game.shape.setColor(new Color(1, 1, 1, alpha));
        game.shape.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.shape.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        if (alpha <= -1 || trigger) {
            alpha += delta / 2;
            trigger = true;
        } else if (alpha > -1 && !trigger) alpha -= delta / 2;
        if (alpha > 1) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && !trigger)
            alpha = -1;
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && trigger)
            alpha = 5;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
