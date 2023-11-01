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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import  com.badlogic.gdx.audio.*;
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
	BitmapFont font;
	Sound sound;

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
		font = new BitmapFont();
		sound = Gdx.audio.newSound(Gdx.files.internal("sounds/eat.mp3"));
	}

	@Override
	public void render () {
		ScreenUtils.clear((float)0.9, (float)0.9, 0, 1);
		batch.begin();
		font.draw(batch, "Score : " + score, 10, 460); // Draw text at (100, 100)
		batch.end();
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
		Vector2 headPos = field.positions.get(0);
		Vector2 donutPos = field.field.get(donut);
		if (headPos.x == donutPos.x && headPos.y == donutPos.y){
			sound.play(1.0f);
			System.out.println("Score : " + score);
			score++;
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
		font.dispose(); // Dispose of the generator when you're done

	}
}
