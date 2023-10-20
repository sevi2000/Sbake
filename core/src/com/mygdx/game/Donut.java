package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import space.earlygrey.shapedrawer.ShapeDrawer;


public class Donut extends FieldElement {
    Color color = Color.RED;
    public Donut(Batch batch){
        super(batch);
    }
    @Override
    public void draw(Batch btc, float transparency) {
        this.shDr.setColor(color);
        this.shDr.filledRectangle(getX(),getY(),getWidth(),getHeight());
    }
}