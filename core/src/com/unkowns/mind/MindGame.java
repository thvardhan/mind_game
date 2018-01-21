package com.unkowns.mind;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.unkowns.mind.Screens.LogoScreen;
import com.unkowns.mind.Screens.MainGameScreen;

public class MindGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public ShapeRenderer shape;
	public OrthographicCamera camera;
    public static final boolean DEBUG = true;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		shape = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1366, 768);
        if (!MindGame.DEBUG)
            this.setScreen(new LogoScreen(this));
        else
            this.setScreen(new MainGameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		shape.dispose();
	}

	public void display() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		this.camera.update();
		this.batch.setProjectionMatrix(this.camera.combined);

	}

}
