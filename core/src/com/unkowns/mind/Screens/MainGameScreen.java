package com.unkowns.mind.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.unkowns.mind.MindGame;

public class MainGameScreen implements Screen {

    //    private static PopupEntity badlogic;
    final MindGame game;
    private Texture bg;


    public MainGameScreen(final MindGame game) {
        this.game = game;
        this.game.font.getData().setScale(80);
//        badlogic = new PopupEntity(new Texture("badlogic.jpg"), 500);
        bg = new Texture("bg_main.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        game.batch.draw(bg, 0, 0);
        game.font.draw(game.batch, "Hello this is mind game", 60, 60, 1000, 500, true);
        game.batch.end();
//        badlogic.animate(delta);

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
        bg.dispose();
    }
}
