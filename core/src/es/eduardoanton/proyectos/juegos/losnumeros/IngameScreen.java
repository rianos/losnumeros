package es.eduardoanton.proyectos.juegos.losnumeros;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;



public class IngameScreen implements Screen {

	private LosNumeros game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture fondo,red,marco,a1,a2,a3,r1,r2,r3;
	
	public IngameScreen(LosNumeros game){
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, LosNumeros.screenwidth,LosNumeros.screenheight);
		cam.update();	
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);	
		fondo = LosNumeros.asset.get("fondo.png", Texture.class );
		red = LosNumeros.asset.get("red.png", Texture.class );
		marco = LosNumeros.asset.get("marco.png", Texture.class );
		a1 = LosNumeros.asset.get("a1.png", Texture.class );
		a2 = LosNumeros.asset.get("a2.png", Texture.class );
		a3 = LosNumeros.asset.get("a3.png", Texture.class );
		r1 = LosNumeros.asset.get("r1.png", Texture.class );
		r2 = LosNumeros.asset.get("r2.png", Texture.class );
		r3 = LosNumeros.asset.get("r3.png", Texture.class );
	}
	@Override
	public void render(float delta) {
		game.gamew.update(delta);
		Gdx.gl.glClearColor(0.0f,0.0f ,0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		batch.begin();
		batch.draw(marco, 295, -25);
		batch.draw(fondo, 344, 22);
		batch.draw(red, 0, 0);
		render_fichas(delta);
		batch.end();
	}

	
	public void render_fichas(float delta){
		Ficha ficha;
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				render_ficha(game.gamew.fichas[i][j], delta); 
			}
		}
	}
	
	public void render_ficha(Ficha ficha, float delta){
		batch.draw(ficha.getTexture, ficha.x ,ficha.y);
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
		// TODO Auto-generated method stub

	}

}
