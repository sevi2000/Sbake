package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Input;
import java.util.*;
public class SbakeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Stage stg;
	Texture img;
	Field field;
	BodyPart head;
	Donut donut;
	Random random = new Random();
	@Override
	public void create () {
		batch = new SpriteBatch();
		stg = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera()),batch);
		
		field = new Field();
		donut = new Donut(batch);
		head = new BodyPart(batch);
		BodyPart bp = new BodyPart(batch);
		field.addDonutAtRandomLocation(donut,stg);
		field.initSnake(head,stg);
		//field.initSnake(bp,stg);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			head.setDirection(Direction.LEFT);
		} else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			head.setDirection(Direction.UP);
		} else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			head.setDirection(Direction.DOWN);
		} else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			head.setDirection(Direction.RIGHT);
		}
		if (field.field.get(head).x == field.field.get(donut).x && field.field.get(head).y == field.field.get(donut).y){
			System.out.println("TOUCHED DONUTTT!!!!!");
			field.addBodyPart(stg,batch);
			field.field.get(donut).x = random.nextInt(10);
			field.field.get(donut).y = random.nextInt(10);
		}
		field.updateField();
		stg.act();
		stg.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
