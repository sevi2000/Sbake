package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;


import java.util.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Color;
public class Field {
    static Random random = new Random();
    int width;
    int height;
    Map<FieldElement, Vector2> field;
    BodyPart lastAddedPart;
    BodyPart head;
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
        bodyPart.color = Color.BLUE;
        field.put(bodyPart,new Vector2(3,3));
        stg.addActor(bodyPart);
        lastAddedPart =  bodyPart;
        head = bodyPart;
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
    public void updateHead(){
        if (head == null)
            return;
        timeForNextMove += Gdx.graphics.getDeltaTime() * 1000;
        if (timeForNextMove > TIME_FOR_NEXT_MOVE_MAX) {
            timeForNextMove -= TIME_FOR_NEXT_MOVE_MAX;
                switch (head.direction) {
                    case UP:
                        if (field.get(head).y != 9)
                            head.setY(field.get(head).y++);
                        break;
                    case DOWN:
                        if (field.get(head).y != 0)
                            head.setY(field.get(head).y--);
                        break;
                    case LEFT:
                        if (field.get(head).x != 0)
                            head.setY(field.get(head).x--);
                        break;
                    case RIGHT:
                        if (field.get(head).x != 9)
                            head.setY(field.get(head).x++);
                        break;
                }
            }

            head.setX(field.get(head).x * 50);
            head.setY(field.get(head).y * 50);
        }
        public void updateField() {
        System.out.println(field);
        updateHead();
        for (FieldElement elt : field.keySet()) {
            if(elt instanceof BodyPart bp && elt != head)
            {
                lastAddedPart.direction = head.direction;
                switch (lastAddedPart.direction){
                    case UP:
                    field.get(elt).x = field.get(head).x;
                    field.get(elt).y = field.get(head).y-1;
                    break;
                    case DOWN:
                        field.get(elt).x = field.get(head).x;
                        field.get(elt).y = field.get(head).y+1;
                        break;
                    case LEFT:
                        field.get(elt).x = field.get(head).x+1;
                        field.get(elt).y = field.get(head).y;
                        break;
                    case RIGHT:
                        field.get(elt).x = field.get(head).x-1;
                        field.get(elt).y = field.get(head).y;
                        break;
                }
            }
            elt.setX(field.get(elt).x * 50);
            elt.setY(field.get(elt).y * 50);
        }
    }
}
