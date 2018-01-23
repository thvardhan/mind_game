package com.unkowns.mind;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
    public static final boolean DEBUG = false;
    public AssetManager assetManager;

	@Override
	public void create () {
        System.out.println("[INFO] DEBUG setting is set to " + DEBUG);
		batch = new SpriteBatch();
		font = new BitmapFont();
		shape = new ShapeRenderer();
		camera = new OrthographicCamera();
        assetManager = new AssetManager();
        camera.setToOrtho(false, 1366, 768);
        loadAssets();

        if (!MindGame.DEBUG)
            this.setScreen(new LogoScreen(this));
        else
            this.setScreen(new MainGameScreen(this));
	}

    public boolean loadAssets() {
        try {
            assetManager.load("texture/team_unknowns.png", Texture.class);
            assetManager.load("texture/main_menu_no_stroke.png", Texture.class);
            assetManager.load("texture/star64_fix.png", Texture.class);
            assetManager.load("texture/white.png", Texture.class);
            assetManager.load("texture/black.png", Texture.class);
            assetManager.load("texture/rectangleAlpha.png", Texture.class);
            assetManager.finishLoading();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return assetManager.update();
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
        assetManager.clear();
        assetManager.dispose();
	}

	public void display() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		this.camera.update();
		this.batch.setProjectionMatrix(this.camera.combined);

	}

}
