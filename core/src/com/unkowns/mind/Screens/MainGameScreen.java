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
import com.unkowns.mind.managers.Text;

import java.util.ArrayList;

public class MainGameScreen implements Screen {

    final MindGame game;
    private Texture white;
    private Texture black;
    private Texture question;
    private Texture transition;

    private Polygon whitePolygon;
    private Rectangle mouse;
    private Polygon blackPolygon;

    private static final boolean READY = true;
    private boolean whiteCol;
    private boolean blackCol;
    private ArrayList<Question> questions;
    private int questionIndex;
    private GlyphLayout glyphLayout;
    private int animateFactor;


    public MainGameScreen(final MindGame game) {
        System.out.println("[INFO] Initializing MainGameScreen");
        this.game = game;

//      Texture init
        white = game.assetManager.get("texture/white.png");
        black = game.assetManager.get("texture/black.png");
        question = game.assetManager.get("texture/rectangleAlpha.png");
        transition = game.assetManager.get("texture/final.png");

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

        QuestionParser parser = new QuestionParser(game.assetManager.get("data/questions.txt", Text.class).getString());
        if (parser.initArray(questions)) {
                System.out.println("[OK] Parsing questions from questions.txt");
                System.out.println("[INFO] Questions.size() = " + questions.size());
        }

        glyphLayout = new GlyphLayout();
        animateFactor = -Gdx.graphics.getWidth();
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

    private void startGame(float delta) {
        if (questionIndex >= questions.size()) {
            questionIndex = 0;
            game.setScreen(new DataScreen(game));
            dispose();
        } else if (questions.get(questionIndex).isAnswered()) {
            if (transition(delta)) {
                questionIndex++;
                animateFactor = -Gdx.graphics.getWidth();
            }
        } else if (animateFactor == -Gdx.graphics.getWidth()) {
            renderBlackChoice(delta, questions.get(questionIndex));
            renderQuestionFont(delta, questions.get(questionIndex));
            renderWhiteChoice(delta, questions.get(questionIndex));
        }
    }

    private boolean transition(float delta) {
        game.batch.draw(transition, animateFactor, 0);
        animateFactor += 20 + Math.random() * 10 * delta;
        return animateFactor >= Gdx.graphics.getWidth();
    }

    private boolean isCollision(Polygon p, Rectangle r) {
        Polygon rPoly = new Polygon(new float[]{0, 0, r.width, 0, r.width,
                r.height, 0, r.height});
        rPoly.setPosition(r.x, r.y);
        return Intersector.overlapConvexPolygons(rPoly, p);
    }

    private void triggerWhite(float delta) {
        questions.get(questionIndex).setAnswerID((short) 0);
        questions.get(questionIndex).setAnswered(true);
    }

    private void triggerBlack(float delta) {
        questions.get(questionIndex).setAnswerID((short) 1);
        questions.get(questionIndex).setAnswered(true);
    }

    private void renderQuestionFont(float delta, Question question) {
        glyphLayout.setText(game.font, question.getQuestion());
        this.game.font.draw(game.batch, question.getQuestion(), this.game.camera.position.x - glyphLayout.width / 2,
                this.game.camera.position.y + glyphLayout.height / 2);
    }

    private void renderBlackChoice(float delta, Question question) {
        glyphLayout.setText(game.font, question.getTwo());

        this.game.font.draw(game.batch, question.getTwo(), (this.game.camera.position.x * 1.7f) - glyphLayout.width / 2,
                ((this.game.camera.position.y * 1.7f) + glyphLayout.height / 2));
    }

    private void renderWhiteChoice(float delta, Question question) {
        glyphLayout.setText(game.font, question.getOne());

        this.game.font.draw(game.batch, question.getOne(), this.game.camera.position.x * 2 - (this.game.camera.position.x * 1.7f) - glyphLayout.width / 2,
                this.game.camera.position.y * 2 - ((this.game.camera.position.y * 1.7f) + glyphLayout.height / 2));
    }

}
