package com.unkowns.mind.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
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

    private Polygon whitePolygon;
    private Rectangle mouse;
    private Polygon blackPolygon;


    public MainGameScreen(final MindGame game) {
        this.game = game;
//        this.game.font.getData().setScale(80);
//        badlogic = new PopupEntity(new Texture("badlogic.jpg"), 500);
//        bg = new Texture("bg_main.png");
        white = new Texture(whiteFile);
        black = new Texture(blackFile);
        question = new Texture(questionFile);
        whitePolygon = new Polygon(new float[]{0, 0, 1366, 0, 0, 768});
        mouse = new Rectangle(0, 0, 1, 1);
        blackPolygon = new Polygon(new float[]{1366, 0, 0, 768, 1366, 768});
        //FIXME change the hard coded vertecies
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.display();

        mouse.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        game.batch.begin();
        game.batch.draw(white, 0, 0);
        game.batch.draw(black, 0, 0);

        game.batch.draw(question, this.game.camera.position.x / 4, game.camera.position.y - game.camera.position.y / 8f, Gdx.graphics.getWidth() / 1.36F, Gdx.graphics.getHeight() / 1.92F);
        game.batch.end();

        if (isCollision(blackPolygon, mouse) && Gdx.input.justTouched()) {
            triggerBlack(delta);
        }
        if (isCollision(whitePolygon, mouse) && Gdx.input.justTouched()) {
            triggerWhite(delta);
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
        white.dispose();
        black.dispose();
        question.dispose();
    }

    private boolean isCollision(Polygon p, Rectangle r) {
        Polygon rPoly = new Polygon(new float[]{0, 0, r.width, 0, r.width,
                r.height, 0, r.height});
        rPoly.setPosition(r.x, r.y);
        return Intersector.overlapConvexPolygons(rPoly, p);
    }

    private void triggerWhite(float delta) {
        System.out.println("WHITE " + Gdx.input.getX());
    }

    private void triggerBlack(float delta) {
        System.out.println("BLACK " + Gdx.input.getX());
    }

}
