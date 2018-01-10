package com.unkowns.mind.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class PopupEntity implements Disposable {

    private Texture texture;
    private float x;
    private float y;
    private int limit;
    private boolean reached = false;

    public PopupEntity(Texture texture, int limit) {
        this.texture = texture;
        this.limit = limit;
        this.x = 0;
        this.y = 0;
    }

    public void setCords(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void animate(float delta) {
        if (x >= limit + 20) {
            reached = true;
        }
        if (x >= limit - 20 && reached) {
            this.x = (x - 4);
            this.y = (y - 4);
        }
        if (!reached) {
            this.x = (x + 8);
            this.y = (y + 8);
        }
    }


    public Texture getTexture() {
        return this.texture;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
