package com.unkowns.mind.Screens;

import com.badlogic.gdx.Screen;
import com.unkowns.mind.MindGame;

public class DataScreen implements Screen {

    final MindGame game;

    public DataScreen(MindGame game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.display();
        game.polyBatch.begin();
        game.poly.draw(game.polyBatch);
        game.polyBatch.end();

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

