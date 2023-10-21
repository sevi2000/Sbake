package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;


import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Batch;
public class Field {
    static Random random = new Random();
    int width;
    int height;
    Map<FieldElement, Vector2> field;
    BodyPart lastAddedPart;
    long timeForNextMove;
    static final long TIME_FOR_NEXT_MOVE_MAX = 200;
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
        stg.addActor(bodyPart);
        lastAddedPart =  bodyPart;
        updateField();
    }
    public void addBodyPart(Stage stg, Batch batch) {
        BodyPart newBodyPart =  new BodyPart(batch);
        newBodyPart.direction = lastAddedPart.direction;
        field.put(newBodyPart,new Vector2(field.get(lastAddedPart).x-1,field.get(lastAddedPart).y));
        stg.addActor(newBodyPart);
        lastAddedPart = newBodyPart;
        updateField();
    }
    public void updateField(){
        timeForNextMove += Gdx.graphics.getDeltaTime() * 1000;
        if (timeForNextMove > TIME_FOR_NEXT_MOVE_MAX) {
            timeForNextMove -= TIME_FOR_NEXT_MOVE_MAX;
        for (FieldElement elt:field.keySet()) {
            if (elt instanceof BodyPart elt1) {
                switch (elt1.direction) {
                    case UP:
                        if (field.get(elt1).y != 9)
                            elt1.setY(field.get(elt1).y++);
                        elt1.direction = Direction.UP;
                        break;
                    case DOWN:
                        if (field.get(elt1).y != 0)
                            elt1.setY(field.get(elt1).y--);
                        elt1.direction = Direction.DOWN;
                        break;
                    case LEFT:
                        if (field.get(elt1).x != 0)
                            elt1.setY(field.get(elt1).x--);
                        elt1.direction = Direction.LEFT;
                        break;
                    case RIGHT:
                        if (field.get(elt1).x != 9)
                            elt1.setY(field.get(elt1).x++);
                        elt1.direction = Direction.RIGHT;
                        break;
                }
            }

            elt.setX(field.get(elt).x * 50);
            elt.setY(field.get(elt).y * 50);
        }
        }
    }
}