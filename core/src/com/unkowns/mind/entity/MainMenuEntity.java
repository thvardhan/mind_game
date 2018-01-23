package com.unkowns.mind.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.unkowns.mind.MindGame;

public class MainMenuEntity implements Disposable {

    private Texture mainMenu;
    private Texture star;
    private float angle;
    private int selector;

    public MainMenuEntity(MindGame game) {
        mainMenu = game.assetManager.get("texture/main_menu_no_stroke.png");
        star = game.assetManager.get("texture/star64_fix.png");
        selector = 1;
        angle = 0;
    }

    @Override
    public void dispose() {
        mainMenu.dispose();
        star.dispose();
    }

    public Texture getMainMenu() {
        return mainMenu;
    }

    public Texture getStar() {
        return star;
    }

    public int getSelector() {
        return selector;
    }

    public void setSelector(int selector) {
        this.selector = selector;
    }

    public float getAngle() {
        return angle;
    }

    public void rotate() {
        this.angle += 1;
        if (angle >= 360) this.angle = 0;
    }

}
