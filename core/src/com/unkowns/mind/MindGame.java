package com.unkowns.mind;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.unkowns.mind.Screens.LogoScreen;
import com.unkowns.mind.Screens.MainGameScreen;
import com.unkowns.mind.managers.Text;
import com.unkowns.mind.managers.TextLoader;


public class MindGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public ShapeRenderer shape;
	public OrthographicCamera camera;
    public static final boolean DEBUG = true;
    public AssetManager assetManager;
    public FreetypeFontLoader.FreeTypeFontLoaderParameter fontSystem;

	@Override
	public void create () {
        System.out.println("[INFO] DEBUG setting is set to " + DEBUG);
		batch = new SpriteBatch();
//		font = new BitmapFont();
		shape = new ShapeRenderer();
		camera = new OrthographicCamera();
        assetManager = new AssetManager();
        camera.setToOrtho(false, 1366, 768);
        //FIXME change camera resolution

        fontSystem = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontSystem.fontFileName = "font/system.ttf";
        fontSystem.fontParameters.size = 80;
        fontSystem.fontParameters.shadowColor = Color.BLACK;
        fontSystem.fontParameters.borderColor = Color.WHITE;
        fontSystem.fontParameters.borderWidth = 3;

        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(Text.class, new TextLoader(new InternalFileHandleResolver()));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        assetManager.load("font/system.ttf", BitmapFont.class, fontSystem);
        loadAssets();


        font = assetManager.get("font/system.ttf");
        font.setColor(Color.GRAY.premultiplyAlpha());
        font.getData().setScale(0.5f);
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
//            assetManager.load("font/system.ttf",True);
            assetManager.load(new AssetDescriptor<Text>("data/questions.txt", Text.class, new TextLoader.TextParameter()));

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
