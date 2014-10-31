package es.eduardoanton.proyectos.juegos.losnumeros;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.InputProcessor;

import es.eduardoanton.proyectos.juegos.losnumeros.Ficha.FichaColor;



public class IngameScreen implements Screen {

	private LosNumeros game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture fondo,red,marco,a1,a2,a3,r1,r2,r3,select,crono;
	private InputProcessor iproc;
	
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
		select = LosNumeros.asset.get("select.png", Texture.class );
		crono = LosNumeros.asset.get("crono.png", Texture.class );
		iproc = new InputProcesadorIngame(cam,game.gamew);
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
		batch.draw(crono,40,40);
		batch.end();
	}

	
	public void render_fichas(float delta){
		Ficha ficha;
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				if (! (i == 10 && j%2 != 0)){
					render_ficha(game.gamew.fichas[i][j], delta);
				}
			}
		}
	}
	
	public void render_ficha(Ficha ficha, float delta){
		Texture tmp = a1;
		if( ficha.color == FichaColor.AMARILLO){
			switch (ficha.val){
				case 1: tmp = a1;break;
				case 2: tmp = a2;break;
				case 3: tmp = a3;break;
			}
			
		}else{
			switch (ficha.val){
			case 1: tmp = r1;break;
			case 2: tmp = r2;break;
			case 3: tmp = r3;break;
		}
		}
				
		if (ficha.y % 2 == 0){
			batch.draw(tmp, 342 + 83*ficha.x ,25 +72*ficha.y);
			if ( ficha.marcada){
				batch.draw(select, 342 + 83*ficha.x ,25 +72*ficha.y);
			}
		}else{
			batch.draw(tmp, 382 + 83*ficha.x ,25 + 72*ficha.y);
			if (ficha.marcada){
				batch.draw(select, 382 + 83*ficha.x ,25 + 72*ficha.y);
			}
		}
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(iproc);	

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
