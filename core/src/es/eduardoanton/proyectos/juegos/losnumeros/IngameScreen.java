package es.eduardoanton.proyectos.juegos.losnumeros;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.InputProcessor;

import es.eduardoanton.proyectos.juegos.losnumeros.Ficha.FichaColor;



public class IngameScreen implements Screen {

	private LosNumeros game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture fondo,red,marco,gr1,gr2,gr3,gr4,gr5,rr1,rr2,rr3,rr4,rr5,select,crono,l3,l5,l7;
	private InputProcessor iproc;
	private BitmapFont font;

	
	public IngameScreen(LosNumeros game){
		this.game = game;
		font = new BitmapFont();
		 font.setColor(Color.WHITE);
		 font.setScale(2f);
		cam = new OrthographicCamera();
		cam.setToOrtho(false, LosNumeros.screenwidth,LosNumeros.screenheight);
		cam.update();	
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);	
		fondo = LosNumeros.asset.get("fondo.png", Texture.class );
		red = LosNumeros.asset.get("red.png", Texture.class );
		marco = LosNumeros.asset.get("marco.png", Texture.class );
		gr1 = LosNumeros.asset.get("gr1.png", Texture.class );
		gr2 = LosNumeros.asset.get("gr2.png", Texture.class );
		gr3 = LosNumeros.asset.get("gr3.png", Texture.class );
		gr4 = LosNumeros.asset.get("gr4.png", Texture.class );
		gr5 = LosNumeros.asset.get("gr5.png", Texture.class );
		rr1 = LosNumeros.asset.get("rr1.png", Texture.class );
		rr2 = LosNumeros.asset.get("rr2.png", Texture.class );
		rr3 = LosNumeros.asset.get("rr3.png", Texture.class );
		rr4 = LosNumeros.asset.get("rr4.png", Texture.class );
		rr5 = LosNumeros.asset.get("rr5.png", Texture.class );
		l3 =LosNumeros.asset.get("l3.png", Texture.class );
		l5 =LosNumeros.asset.get("l5.png", Texture.class );
		l7 =LosNumeros.asset.get("l7.png", Texture.class );
		
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
		font.draw(batch, "Grises:" + LosNumeros.gamew.grises + " Rojas: " + LosNumeros.gamew.rojos , 20 , 600);
		font.draw(batch, "PUNTOS:" + LosNumeros.gamew.puntos, 20, 700);
		font.draw(batch, "TIEMPO:" + String.format("%.0f", LosNumeros.gamew.gametime), 20, 400);
		batch.draw(marco, 295, -25);
		batch.draw(fondo, 344, 22);
		
		render_fichas(delta);
		render_trail(delta);
		batch.draw(crono,40,40);
		batch.draw(red, 2, -1);
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
	
	private int tx(Ficha f){
		int tx = 0;
		if (f.y % 2 == 0){
			tx =  343 + 83*f.x;	
		}else{
			tx =  384 + 83*f.x;
		}
		return tx;
	}
	
	private int ty(Ficha f){
		int ty = 0;
		if (f.y % 2 == 0){
			ty =  23 +72*f.y;	
		}else{
			ty =  23 + 72*f.y;
		}
		return ty;
	}
	
	public void render_trail(float delta){
		int ux = -1;
		int uy = -1;
		for (Ficha ficha : LosNumeros.gamew.linea){
			if ( ux == -1){
				ux = ficha.x;
				uy = ficha.y;
			}else{
				if ( ficha.y == uy && ficha.x == ux + 1){
					batch.draw(l3,tx(ficha) - 60 ,ty(ficha) + 28);
					ux = ficha.x;
					uy = ficha.y;
				}else if (  ficha.y == uy && ficha.x == ux - 1){
					batch.draw(l3,tx(ficha) - 60 +83  ,ty(ficha) + 28);
					ux = ficha.x;
					uy = ficha.y;
				}else if ( ficha.x == ux && ficha.y == uy - 1 && uy % 2 == 0){
					batch.draw(l5,tx(ficha) - 17 ,ty(ficha) + 28);
					ux = ficha.x;
					uy = ficha.y;
				}else if ( ficha.x == ux +1 && ficha.y == uy - 1){
					batch.draw(l5,tx(ficha) - 17 ,ty(ficha) + 28);
					ux = ficha.x;
					uy = ficha.y;
				}else if ( ficha.x == ux && ficha.y == uy + 1 && uy % 2 != 0 ){
					batch.draw(l5,tx(ficha) + 24 ,ty(ficha) - 44);
					ux = ficha.x;
					uy = ficha.y;
				}else if ( ficha.x == ux -1 && ficha.y == uy + 1 ){
					batch.draw(l5,tx(ficha) + 24 ,ty(ficha) - 44);
					ux = ficha.x;
					uy = ficha.y;
				}else if ( ficha.x == ux && ficha.y == uy - 1 ){
					batch.draw(l7,tx(ficha) + 24 ,ty(ficha) + 28);
					ux = ficha.x;
					uy = ficha.y;
				}else if ( ficha.x == ux - 1 && ficha.y == uy - 1 ){
					batch.draw(l7,tx(ficha) + 24 ,ty(ficha) + 28);
					ux = ficha.x;
					uy = ficha.y;
				}else if ( ficha.x == ux  && ficha.y == uy + 1 ){
					batch.draw(l7,tx(ficha) - 17 ,ty(ficha) - 44);
					ux = ficha.x;
					uy = ficha.y;
				}else if ( ficha.x == ux + 1 && ficha.y == uy + 1 ){
					batch.draw(l7,tx(ficha) - 17 ,ty(ficha) - 44);
					ux = ficha.x;
					uy = ficha.y;
				}
			}
		}
	}
	
	public void render_ficha(Ficha ficha, float delta){
		Texture tmp = gr1;
		if( ficha.color == FichaColor.GRIS){
			switch (ficha.val){
				case 1: tmp = gr1;break;
				case 2: tmp = gr2;break;
				case 3: tmp = gr3;break;
				case 4: tmp = gr4;break;
				case 5: tmp = gr5;break;
			}
			
		}else{
			switch (ficha.val){
			case 1: tmp = rr1;break;
			case 2: tmp = rr2;break;
			case 3: tmp = rr3;break;
			case 4: tmp = rr4;break;
			case 5: tmp = rr5;break;
		}
		}
				
		if (ficha.y % 2 == 0){
			batch.draw(tmp, 343 + 83*ficha.x ,23 +72*ficha.y);
			if ( ficha.marcada){
				batch.draw(select, 343 + 83*ficha.x ,23 +72*ficha.y);
			}
		}else{
			batch.draw(tmp, 384 + 83*ficha.x ,23 + 72*ficha.y);
			if (ficha.marcada){
				batch.draw(select, 384 + 83*ficha.x ,23 + 72*ficha.y);
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
