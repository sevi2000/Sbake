package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class BodyPart extends FieldElement {
    Color color = Color.GREEN;
    Direction direction = Direction.UP;
    Texture texture;
    BodyPart(Batch batch) {
        super(batch);
        texture = new Texture(Gdx.files.internal("images/head.jpg"));
    }
    @Override
    public void draw(Batch btc, float transparency) {
        this.shDr.setColor(color);
        this.shDr.filledRectangle(getX(),getY(),getWidth(),getHeight());
        btc.draw(texture,this.getX(),this.getY(),getWidth(),getHeight());
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}