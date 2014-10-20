package es.eduardoanton.proyectos.juegos.losnumeros;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LoadingScreen implements Screen {
	private LosNumeros game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private BitmapFont font;
	private Texture emptyT,icono;
	private NinePatch empty;
	private Texture fullT;
	private NinePatch full;

	public LoadingScreen(LosNumeros game){
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, LosNumeros.screenwidth,LosNumeros.screenheight);
		cam.update();	
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);	
		font = new BitmapFont();
		emptyT = new Texture(Gdx.files.internal("empty.png"));
		fullT = new Texture(Gdx.files.internal("full.png"));
		icono = new Texture(Gdx.files.internal("loading.png"));
		empty = new NinePatch(new TextureRegion(emptyT,24,24),8,8,8,8);
		full = new NinePatch(new TextureRegion(fullT,24,24),8,8,8,8);
	}
	
	@Override
	public void render(float delta) {
		if (LosNumeros.asset.update()){
			game.mainscreen();
		}else{
			//Gdx.gl.glClearColor(0.12f,0.22f ,0.22f, 1);
			Gdx.gl.glClearColor(1f,1f ,1f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			batch.draw(icono, 260, 200);
			empty.draw(batch, 200, 70, LosNumeros.screenwidth - 400, 30);
			full.draw(batch, 200, 70, LosNumeros.asset.getProgress()*(LosNumeros.screenwidth - 400) + 1, 30);
			font.drawMultiLine(batch,"Cargando... " + (int)(LosNumeros.asset.getProgress()*100)+"% loaded",500,92,0, BitmapFont.HAlignment.CENTER);
			batch.end();
		}	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		this.emptyT.dispose();
		this.fullT.dispose();
		this.font.dispose();
		this.icono.dispose();
		this.batch.dispose();
	}

}
