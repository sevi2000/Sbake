package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SbakeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Stage stg;
	Texture img;
	Field field;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		stg = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),new OrthographicCamera()),batch);
		
		field = new Field();
		Donut donut = new Donut(batch);
		stg.addActor(donut);
		field.addDonutAtRandomLocation(donut);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		stg.act();
		stg.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
