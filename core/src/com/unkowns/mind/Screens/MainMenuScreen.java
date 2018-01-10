package com.unkowns.mind.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.unkowns.mind.MindGame;
import com.unkowns.mind.entity.MainMenuEntity;

public class MainMenuScreen implements Screen {

    final MindGame game;
    OrthographicCamera camera;
    private MainMenuEntity me;

    public MainMenuScreen(final MindGame game) {
        this.game = game;
        me = MainMenuEntity.getInstance();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1366, 768);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//      Clears the screen
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//      Updates the camera
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

//      Begins the batch and collects the orders
        game.batch.begin();
//      Draws the main screen
        game.batch.draw(me.getMainMenu(), 0, 0, 1366, 768);

//      Draws based on the selector variable form MainMenu#
        if (me.getSelector() == 1)
            game.batch.draw(me.getStar(), Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 6,
                    Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 35,
                    me.getStar().getWidth() / 2, me.getStar().getHeight() / 2, 78, 78,
                    1, 1, me.getAngle(), 0, 0, 78, 78, false, false);
        else if (me.getSelector() == 2)
            game.batch.draw(me.getStar(), Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 6,
                    Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 5.9f,
                    me.getStar().getWidth() / 2, me.getStar().getHeight() / 2, 78, 78,
                    1, 1, me.getAngle(), 0, 0, 78, 78, false, false);
        else if (me.getSelector() == 3)
            game.batch.draw(me.getStar(), Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 6,
                    Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight() / 3.2f,
                    me.getStar().getWidth() / 2, me.getStar().getHeight() / 2, 78, 78,
                    1, 1, me.getAngle(), 0, 0, 78, 78, false, false);


//      Rotates the star
        me.rotate();
//      Starts drawing as the orders end
        game.batch.end();

//      Handles the input
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (me.getSelector() == 3) me.setSelector(1);
            else {
                me.setSelector(me.getSelector() + 1);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (me.getSelector() == 1) me.setSelector(3);
            else {
                me.setSelector(me.getSelector() - 1);
            }
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && me.getSelector() == 1) {
            game.setScreen(new LogoScreen(game));
            dispose();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && me.getSelector() == 2) {
            game.setScreen(new RecordScreen(game));
            dispose();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && me.getSelector() == 3) {
            game.setScreen(new OptionScreen(game));
            dispose();
        }

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
//        mainMenu.dispose();
        me.dispose();
    }

}
