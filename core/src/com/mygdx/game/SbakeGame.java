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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.util.*;
public class SbakeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Stage stg;
	Texture img;
	Field field;
	BodyPart head;
	Donut donut;
	Random random = new Random();
	static boolean gameOver = false;
	int score = 0;
	//BitmapFont font = new BitmapFont();
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
		ScreenUtils.clear(0, 1, 1, 1);
		//font.draw(batch, "Score : " + score, 10, 10);
		if (gameOver)
			return;
		if(head.direction != Direction.RIGHT && Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			head.setDirection(Direction.LEFT);
		} else if(head.direction != Direction.DOWN && Gdx.input.isKeyPressed(Input.Keys.UP)) {
			head.setDirection(Direction.UP);
		} else if(head.direction != Direction.UP && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			head.setDirection(Direction.DOWN);
		} else if(head.direction != Direction.LEFT && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			head.setDirection(Direction.RIGHT);
		}/* else {
			gameOver = true;
		}*/
		if (field.positions.get(0).x == field.field.get(donut).x && field.positions.get(0).y == field.field.get(donut).y){
			System.out.println("TOUCHED DONUTTT!!!!!");
			field.addBodyPart(stg,batch);
			int x,y;
			do {
				x = random.nextInt(10);
				y = random.nextInt(10);
			} while(Field.donutUnderSnake(x,y));
			field.field.get(donut).x = x;
			field.field.get(donut).y = y;
		}
		field.updateField(false);
		stg.act();
		stg.draw();
		if(Field.headTouchesBody()){
			gameOver = true;
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
