package com.unkowns.mind.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.unkowns.mind.MindGame;
import com.unkowns.mind.Question;
import com.unkowns.mind.QuestionParser;

import java.io.IOException;
import java.util.ArrayList;

public class MainGameScreen implements Screen {

    final MindGame game;
    private Texture white;
    private Texture black;
    private Texture question;

    private Polygon whitePolygon;
    private Rectangle mouse;
    private Polygon blackPolygon;

    private static final boolean READY = true;
    private boolean whiteCol;
    private boolean blackCol;
    private ArrayList<Question> questions;
    private int questionIndex;
    private GlyphLayout glyphLayout;


    public MainGameScreen(final MindGame game) {
        System.out.println("[INFO] Initializing MainGameScreen");
        this.game = game;

//      Texture init
        white = game.assetManager.get("texture/white.png");
        black = game.assetManager.get("texture/black.png");
        question = game.assetManager.get("texture/rectangleAlpha.png");


//      Polygon creation for bounds
        whitePolygon = new Polygon(new float[]{0, 0, Gdx.graphics.getWidth(), 0, 0, Gdx.graphics.getHeight()});
        mouse = new Rectangle(0, 0, 1, 1);
        blackPolygon = new Polygon(new float[]{Gdx.graphics.getWidth(), 0, 0, Gdx.graphics.getHeight(),
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight()});


//      bool checkers
        whiteCol = false;
        blackCol = false;


//      Misc initializer
        questions = new ArrayList<Question>();
        questionIndex = 0;

        try {
            // FIXME: 1/23/18 fix AssetMangaer here
            QuestionParser parser = new QuestionParser(Gdx.files.internal("data/questions.txt").file());
            if (parser.initArray(questions)) {
                System.out.println("[OK] Parsing questions from questions.txt");
                System.out.println("[INFO] Questions.size() = " + questions.size());
            }
        } catch (IOException e) {
            System.out.println("Gdx.files.classpath(\"questions.txt\") = " + Gdx.files.classpath("questions.txt"));
            e.printStackTrace();
        }


        game.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        game.font.getData().setScale(2);
        glyphLayout = new GlyphLayout();
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
        game.batch.draw(question, this.game.camera.position.x * 2 / 27.32F,
                (game.camera.position.y - game.camera.position.y / 3.186f) + game.camera.position.y / 6.372f,
                game.camera.position.x * 2 - (game.camera.position.x * 2 / 27.32f) * 2,
                game.camera.position.y);
        if (READY)
            startGame(delta);
        game.batch.end();


        if (blackCol && Gdx.input.justTouched()) {
            triggerBlack(delta);
        }
        if (whiteCol && Gdx.input.justTouched()) {
            triggerWhite(delta);
        }

        blackCol = false;
        whiteCol = false;
        if (isCollision(blackPolygon, mouse))
            blackCol = true;
        else if (isCollision(whitePolygon, mouse))
            whiteCol = true;


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

    private void startGame(float delta) {
        if (questions.get(questionIndex).isAnswered())
            questionIndex++;
        renderQuestionFont(delta, questions.get(questionIndex));
    }

    private void renderQuestionFont(float delta, Question question) {
        glyphLayout.setText(game.font, question.getQuestion());
        this.game.font.draw(game.batch, question.getQuestion(), this.game.camera.position.x - glyphLayout.width / 2,
                this.game.camera.position.y + glyphLayout.height / 2);
    }

}
