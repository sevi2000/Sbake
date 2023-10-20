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
public class SbakeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Stage stg;
	Texture img;
	Field field;
	BodyPart head;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		stg = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera()),batch);
		
		field = new Field();
		Donut donut = new Donut(batch);
		head = new BodyPart(batch);
		BodyPart bp = new BodyPart(batch);
		//stg.addActor(donut);
		//stg.addActor(head);
		//stg.addActor(bp);
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
