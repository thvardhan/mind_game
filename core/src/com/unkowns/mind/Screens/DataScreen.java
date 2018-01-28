package com.unkowns.mind.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.ShortArray;
import com.unkowns.mind.Choice;
import com.unkowns.mind.MindGame;
import com.unkowns.mind.Question;

import java.util.ArrayList;

public class DataScreen implements Screen {

    final MindGame game;
    private Texture textureSolid;
    private Choice choice;


    public DataScreen(MindGame game, ArrayList<Question> questions) {
        this.game = game;
        //TextureRegion Color here
        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(102, 204, 255, 0.4f); // DE is red, AD is green and BE is blue.
        pix.fill();
        textureSolid = new Texture(pix);
        TextureRegion textureRegion = new TextureRegion(textureSolid);

        float[] vertices = new float[]{10, 10, 100, 10, 800, 800, 10, 100};

        EarClippingTriangulator triangulator = new EarClippingTriangulator();
        ShortArray triangleIndices = triangulator.computeTriangles(vertices);

        PolygonRegion polyReg = new PolygonRegion(textureRegion, vertices, triangleIndices.toArray());

        game.poly = new PolygonSprite(polyReg);
        choice = Question.getChoice(questions);
        System.out.println(choice.toString());
        System.out.println(choice.getPersona());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.display();

        Gdx.gl.glLineWidth(3);
        float x = game.camera.position.x;
        float y = game.camera.position.y;

        game.shape.begin(ShapeRenderer.ShapeType.Line);
        game.shape.setColor(Color.BLACK);

        game.shape.polygon(new float[]{x + x * 12 / 100, y, x, y + y * 15 / 100, x - x * 12 / 100, y, x - x * 9 / 100, y - y * 30 / 100,
                x + x * 9 / 100, y - y * 30 / 100});
        game.shape.polygon(new float[]{x + x * 20 / 100, y, x, y + y * 25 / 100, x - x * 20 / 100, y, x - x * 15 / 100, y - y * 40 / 100,
                x + x * 15 / 100, y - y * 40 / 100});
        game.shape.polygon(new float[]{x + x * 28 / 100, y, x, y + y * 35 / 100, x - x * 28 / 100, y, x - x * 21 / 100, y - y * 50 / 100,
                x + x * 21 / 100, y - y * 50 / 100});

        game.shape.polygon(new float[]{x + x * 36 / 100, y, x, y + y * 45 / 100, x - x * 36 / 100, y, x - x * 26 / 100, y - y * 60 / 100,
                x + x * 26 / 100, y - y * 60 / 100});

        game.shape.end();
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

