package com.unkowns.mind.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.unkowns.mind.MindGame;

public class LogoScreen implements Screen {

    final MindGame game;
    final Texture logo;

    public LogoScreen(MindGame game) {
        this.game = game;
        this.logo = new Texture("team_unknowns.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(logo, Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - logo.getHeight() / 2, 500, 500);
        game.batch.end();
//        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        logo.dispose();
    }
}
