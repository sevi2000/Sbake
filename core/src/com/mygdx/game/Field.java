package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

import java.util.*;

public class Field {
    static Random random = new Random();
    int width;
    int height;
    Map<FieldElement, Vector2> field;
    Field() {
        field = new HashMap<>();
        this.width = 10;
        this.height = 10;
    }
    public void addDonutAtRandomLocation(Donut donut){
        field.put(donut,new Vector2(random.nextInt(10),random.nextInt(10)));
        updateField();
    }
    public void updateField(){
        for (FieldElement elt:field.keySet()){
            elt.setX(field.get(elt).x * 50);
            elt.setY(field.get(elt).y * 50);
        }
    }
}