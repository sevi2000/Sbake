package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import space.earlygrey.shapedrawer.ShapeDrawer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class Donut extends FieldElement {
    Color color = Color.RED;
    boolean special;
    Texture texture;
    public Donut(Batch batch, boolean special){
        super(batch);
        this.special = special;
        if (special)
            color = Color.ORANGE;
        texture = new Texture(Gdx.files.internal("images/donut.jpg"));
    }
    @Override
    public void draw(Batch btc, float transparency) {
        this.shDr.setColor(color);
        //this.shDr.filledRectangle(getX(),getY(),getWidth(),getHeight());
        btc.draw(texture,this.getX(),this.getY(),getWidth(),getHeight());
    }
    public int score(){
        return special? 6:1;
    }
}