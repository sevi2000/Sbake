package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;


import java.util.*;
import com.badlogic.gdx.scenes.scene2d.Stage;


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
    public void addDonutAtRandomLocation(Donut donut,Stage stg){
        field.put(donut,new Vector2(random.nextInt(10),random.nextInt(10)));
        updateField();
        stg.addActor(donut);
    }
    public void initSnake(BodyPart bodyPart, Stage stg) {
        field.put(bodyPart,new Vector2(3,3));
        System.out.println("nb fe : " + field.size());
        stg.addActor(bodyPart);
        updateField();
    }
    public void updateField(){
        for (FieldElement elt:field.keySet()){
            System.out.println("updating body");
            if (elt instanceof BodyPart){
                switch (((BodyPart)elt).direction) {
                    case UP:
                        elt.setY(field.get(elt).y++);
                        break;
                    case DOWN:
                        elt.setY(field.get(elt).y--);
                        break;
                    case LEFT:
                        elt.setY(field.get(elt).x--);
                        break;
                    case RIGHT:
                        elt.setY(field.get(elt).x++);
                        break;
                }
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

            }
            if (field.get(elt).x != 0 && field.get(elt).y != 0) {
                elt.setX(field.get(elt).x * 50);
                elt.setY(field.get(elt).y * 50);
            }
        }
    }
}