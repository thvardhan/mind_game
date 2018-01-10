package com.unkowns.mind.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class MainMenuEntity implements Disposable {

    private static MainMenuEntity instance = new MainMenuEntity();
    private Texture mainMenu;
    private Texture star;
    private float angle;
    private int selector;

    private MainMenuEntity() {
        mainMenu = new Texture("main_menu.png");
        star = new Texture("star64.png");
        selector = 1;
        angle = 0;
    }

    public static MainMenuEntity getInstance() {
        return instance;
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
