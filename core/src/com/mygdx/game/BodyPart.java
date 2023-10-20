package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Color;

public class BodyPart extends FieldElement {
    Color color = Color.GREEN;
    Direction direction = Direction.UP;
    BodyPart(Batch batch) {
        super(batch);
    }
    @Override
    public void draw(Batch btc, float transparency) {
        this.shDr.setColor(color);
        this.shDr.filledRectangle(getX(),getY(),getWidth(),getHeight());
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}