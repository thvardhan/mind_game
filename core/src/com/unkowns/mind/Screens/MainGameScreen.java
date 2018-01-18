package com.unkowns.mind.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.unkowns.mind.MindGame;

public class MainGameScreen implements Screen {

    //    private static PopupEntity badlogic;
    final MindGame game;
    private final String whiteFile = "white.png";
    private final String blackFile = "black.png";
    private final String questionFile = "rectangleAlpha.png";
    private Texture white;
    private Texture black;
    private Texture question;

    public MainGameScreen(final MindGame game) {
        this.game = game;
//        this.game.font.getData().setScale(80);
//        badlogic = new PopupEntity(new Texture("badlogic.jpg"), 500);
//        bg = new Texture("bg_main.png");
        white = new Texture(whiteFile);
        black = new Texture(blackFile);
        question = new Texture(questionFile);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.display();


        game.batch.begin();
        game.batch.draw(white, 0, 0);
        game.batch.draw(black, 0, 0);
//        System.out.println("this.game.camera.position.x/2 = " + this.game.camera.position.x/2);
        game.batch.draw(question, this.game.camera.position.x - (this.question.getWidth() / 2), this.game.camera.position.y - (this.question.getHeight() / 2));
//        game.font.draw(game.batch, "Hello this is mind game", 60, 60, 1000, 500, true);
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
        white.dispose();
        black.dispose();
        question.dispose();
    }
}
