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
    List<BodyPart> body = new ArrayList<>();
    static List<Vector2> positions = new ArrayList<>();
    BodyPart lastAddedPart;
    BodyPart head;
    long timeForNextMove;
    static final long TIME_FOR_NEXT_MOVE_MAX = 200;
    Field() {
        field = new HashMap<>();
        this.width = 20;
        this.height = 20;
    }
    public void addDonutAtRandomLocation(Donut donut,Stage stg){
        field.put(donut,new Vector2(random.nextInt(width),random.nextInt(height)));
        updateField(false);
        stg.addActor(donut);
    }
    public void initSnake(BodyPart bodyPart, Stage stg) {
        bodyPart.color = Color.BLUE;
        body.add(bodyPart);
        positions.add(new Vector2(3,3));
        stg.addActor(bodyPart);
        updateField(false);
        head = bodyPart;
    }
    public void addBodyPart(Stage stg, Batch batch) {
        BodyPart newBodyPart =  new BodyPart(batch);
        newBodyPart.direction = body.get(body.size()-1).direction;
        body.add(newBodyPart);
        stg.addActor(newBodyPart);
        updateField(true);
    }
    public void updateHead(boolean newElt){
        if (head == null)
            return;
        timeForNextMove += Gdx.graphics.getDeltaTime() * 1000;
        if (timeForNextMove > TIME_FOR_NEXT_MOVE_MAX || newElt) {
            timeForNextMove -= TIME_FOR_NEXT_MOVE_MAX;
            Vector2 pos = positions.get(0);
                switch (head.direction) {
                    case UP:
                        if (pos.y != height - 1)
                            positions.add(0,new Vector2(pos.x,pos.y + 1));
                        break;
                    case DOWN:
                        if (pos.y != 0)
                            positions.add(0,new Vector2(pos.x,pos.y - 1));
                        break;
                    case LEFT:
                        if (pos.x != 0)
                            positions.add(0,new Vector2(pos.x - 1,pos.y));
                        break;
                    case RIGHT:
                        if (pos.x != width - 1)
                            positions.add(0,new Vector2(pos.x + 1,pos.y));
                        break;
                }
                System.out.println("udateHead:\n"+positions);
                if (!newElt)
                    positions.remove(positions.get(positions.size() - 1));
            }
        if (positions.size() < body.size()){
            SbakeGame.gameOver = true;
        }
        }
        public void updateField(boolean newElt) {
        updateHead(newElt);
        if(SbakeGame.gameOver)
            return;
        int i = 0;
        for (FieldElement elt : body) {
            elt.setX(positions.get(i).x * 25);
            elt.setY(positions.get(i).y * 25);
            i++;
        }
        for (FieldElement elt:field.keySet()){
            elt.setX(field.get(elt).x * 25);
            elt.setY(field.get(elt).y * 25);
        }
    }
    public static boolean donutUnderSnake(int x, int y){
        for (Vector2 vec : positions){
            if (x == vec.x && y == vec.y)
                return true;
        }



        return false;
    }
    public static boolean headTouchesBody(){
        for (int i = 1; i < positions.size(); i++){
            if (positions.get(0).x == positions.get(i).x && positions.get(0).y == positions.get(i).y)
                return true;
        }
        return false;
    }
}
