package com.unkowns.mind;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MindGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera camera;
    static int x = 0;
    static int y = 0;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		camera=new OrthographicCamera();
        camera.setToOrtho(false, 1366, 768);
	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(221, 221, 221, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
		batch.begin();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            x += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            y += 4;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            y -= 4;

        batch.draw(img, x, y);
		batch.end();

//		System.out.println(Gdx.input.getX());
//		System.out.println(Gdx.input.getY());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
