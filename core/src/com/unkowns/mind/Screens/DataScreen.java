package com.unkowns.mind.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.ShortArray;
import com.unkowns.mind.Choice;
import com.unkowns.mind.MindGame;
import com.unkowns.mind.Question;
import com.unkowns.mind.managers.Text;

import java.util.ArrayList;

public class DataScreen implements Screen {

    final MindGame game;
    private Texture textureSolid;
    private final boolean GRAPH = true;
    private Choice choice;
    private Texture textureSolid1;
    private float startY;
    private float startX;

    public DataScreen(MindGame game, ArrayList<Question> questions) {
        this.game = game;
        //TextureRegion Color here
        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(102, 204, 255, 0.7f); // DE is red, AD is green and BE is blue.
        pix.fill();
        textureSolid = new Texture(pix);
        TextureRegion textureRegion = new TextureRegion(textureSolid);

        Pixmap pix1 = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(1, 1, 1, 0.7f); // DE is red, AD is green and BE is blue.
        pix.fill();
        textureSolid1 = new Texture(pix);
        TextureRegion textureRegion1 = new TextureRegion(textureSolid1);

        choice = Question.getChoice(questions);
        System.out.println(choice.toString());
        System.out.println(choice.getPersona());
        float x = game.camera.position.x;
        float y = game.camera.position.y;
        float inP = ((float) choice.intelligence / 200) * 100;
        float emP = ((float) choice.emotional / 200) * 100;
        float spP = ((float) choice.spiritual / 200) * 100;
        float acP = ((float) choice.academic / 200) * 100;
        float[] vertices = new float[]{x + (x * 36 / 100) * inP / 100, y, x, y + (y * 45 / 100) * emP / 100,
                x - (x * 36 / 100) * spP / 100, y, x, y - (y * 45 / 100) * acP / 100};

        float[] ver = new float[]{x + x * 36 / 100, y, x, y + y * 45 / 100, x - x * 36 / 100, y, x, y - y * 45 / 100};

        EarClippingTriangulator triangulator = new EarClippingTriangulator();
        ShortArray triangleIndices = triangulator.computeTriangles(vertices);
        ShortArray triangleIndices1 = triangulator.computeTriangles(ver);

        PolygonRegion polyReg = new PolygonRegion(textureRegion, vertices, triangleIndices.toArray());
        PolygonRegion polyReg1 = new PolygonRegion(textureRegion1, ver, triangleIndices1.toArray());
        game.poly = new PolygonSprite(polyReg);
        game.bg = new PolygonSprite(polyReg1);
        startY = game.camera.position.y;
        startX = game.camera.position.x;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.display();
        drawText();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            game.camera.translate(0, 10);
            game.shape.translate(0, 10, 0);
            game.bg.translate(0, 10);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && game.camera.position.y > startY) {
            game.camera.translate(0, -10);
            game.shape.translate(0, -10, 0);
            game.bg.translateY(-10);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

        if (GRAPH) {
            Gdx.gl.glLineWidth(3);

            float x = game.camera.position.x;
            float y = game.camera.position.y;
            game.shape.begin(ShapeRenderer.ShapeType.Line);
            game.shape.setColor(Color.BLACK);

            game.shape.polygon(new float[]{x + x * 12 / 100, y, x, y + y * 15 / 100, x - x * 12 / 100, y, x, y - y * 15 / 100});
            game.shape.polygon(new float[]{x + x * 20 / 100, y, x, y + y * 25 / 100, x - x * 20 / 100, y, x, y - y * 25 / 100});
            game.shape.polygon(new float[]{x + x * 28 / 100, y, x, y + y * 35 / 100, x - x * 28 / 100, y, x, y - y * 35 / 100});
            game.shape.polygon(new float[]{x + x * 36 / 100, y, x, y + y * 45 / 100, x - x * 36 / 100, y, x, y - y * 45 / 100});

            game.shape.line(x, y, x + x * 36 / 100, y);
            game.shape.line(x, y, x, y + y * 45 / 100);
            game.shape.line(x, y, x - x * 36 / 100, y);
            game.shape.line(x, y, x, y - y * 45 / 100);

            game.shape.end();

            game.polyBatch.begin();
            game.bg.draw(game.polyBatch);
            game.poly.draw(game.polyBatch);
            game.polyBatch.setTransformMatrix(game.shape.getTransformMatrix());
            game.polyBatch.end();
        }
    }

    void drawText() {
        float x = game.camera.position.x;
        float y = game.camera.position.y;
        game.batch.begin();
        game.font.draw(game.batch, new GlyphLayout(game.font, "Intelligence (" + this.choice.intelligence * 100 / 200 + "%)"), startX + startX * 40 / 100, startY);
        game.font.draw(game.batch, new GlyphLayout(game.font, "Emotional(" + this.choice.emotional * 100 / 200 + "%)"), startX, startY + startY * 55 / 100);
        game.font.draw(game.batch, new GlyphLayout(game.font, "Spiritual(" + this.choice.spiritual * 100 / 200 + "%)"), startX - (startX * 70 / 100), startY);
        game.font.draw(game.batch, new GlyphLayout(game.font, "Academic(" + this.choice.academic * 100 / 200 + "%)"), startX, startY - (startY * 48 / 100));
        game.font.draw(game.batch, game.assetManager.get("persona/" + choice.getPersona() + ".txt", Text.class).getString(),
                0, game.camera.position.y * 2 - 30, game.camera.position.x * 2, 1, true);
        game.batch.end();
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
        textureSolid.dispose();
        textureSolid1.dispose();
    }

}

