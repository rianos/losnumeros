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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.InputProcessor;

import es.eduardoanton.proyectos.juegos.losnumeros.Ficha.FichaColor;



public class IngameScreen implements Screen {

	private LosNumeros game;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private Texture fondo,red,marco,gr1,gr2,gr3,gr4,gr5,rr1,rr2,rr3,rr4,rr5,ar1,ar2,ar3,ar4,ar5,erodeo,aa1,aa2,aa3,aa4,aa5,select,crono,l3,l5,l7;
	private Texture p2,p3,p5,p7,p9,p11,botonp,botona,botone,botonr,boton8min,bmodo;
	private InputProcessor iproc;
	private BitmapFont font;
	private ShapeRenderer shapeRenderer;

	
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
		aa1 = LosNumeros.asset.get("aa1.png", Texture.class );
		aa2 = LosNumeros.asset.get("aa2.png", Texture.class );
		aa3 = LosNumeros.asset.get("aa3.png", Texture.class );
		aa4 = LosNumeros.asset.get("aa4.png", Texture.class );
		aa5 = LosNumeros.asset.get("aa5.png", Texture.class );
		ar1 = LosNumeros.asset.get("ar1.png", Texture.class);
		ar2 = LosNumeros.asset.get("ar2.png", Texture.class);
		ar3 = LosNumeros.asset.get("ar3.png", Texture.class);
		ar4 = LosNumeros.asset.get("ar4.png", Texture.class);
		ar5 = LosNumeros.asset.get("ar5.png", Texture.class);
		erodeo = LosNumeros.asset.get("erodeo.png", Texture.class);
		l3 =LosNumeros.asset.get("l3.png", Texture.class );
		l5 =LosNumeros.asset.get("l5.png", Texture.class );
		l7 =LosNumeros.asset.get("l7.png", Texture.class );
		p2 =LosNumeros.asset.get("p2.png", Texture.class );
		p3 =LosNumeros.asset.get("p3.png", Texture.class );
		p5 =LosNumeros.asset.get("p5.png", Texture.class );
		p7 =LosNumeros.asset.get("p7.png", Texture.class );
		p9 =LosNumeros.asset.get("p9.png", Texture.class );
		p11 =LosNumeros.asset.get("p11.png", Texture.class );
		botonp = LosNumeros.asset.get("bparedes.png", Texture.class );
		botona = LosNumeros.asset.get("bamarillas.png", Texture.class );
		botone = LosNumeros.asset.get("bespecial.png", Texture.class);
		bmodo = LosNumeros.asset.get("bmodo.png", Texture.class);
		boton8min = LosNumeros.asset.get("b8minimo.png", Texture.class);
		botonr = LosNumeros.asset.get("brodeo.png", Texture.class);
		select = LosNumeros.asset.get("select.png", Texture.class );
		crono = LosNumeros.asset.get("crono.png", Texture.class );
		iproc = new InputProcesadorIngame(cam,game.gamew);
		//shapeRenderer = new ShapeRenderer();
	}
	@Override
	public void render(float delta) {
		game.gamew.update(delta);
		Gdx.gl.glClearColor(0.0f,0.0f ,0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		cam.update();
		/*shapeRenderer.setProjectionMatrix(cam.combined);
		shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.TEAL);
        shapeRenderer.rect(10, 650, LosNumeros.gamew.gametime*300/GameWorld.GAMETIME, 40);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.TEAL);
        shapeRenderer.rect(10, 650, 300, 40);
        shapeRenderer.end();*/
    
		batch.begin();
		//font.draw(batch, "Grises:" + LosNumeros.gamew.grises + " Rojas: " + LosNumeros.gamew.rojos , 20 , 600);
		font.draw(batch, "PUNTOS:" + LosNumeros.gamew.puntos, 20, 300);
		font.draw(batch, String.format("%.0f", LosNumeros.gamew.gametime), 15, 682);
		font.draw(batch, GameWorld.modojuegoS[GameWorld.modojuego], 15,540);
		batch.draw(marco, 295, -25);
		batch.draw(fondo, 344, 22);
		
		render_fichas(delta);
		render_trail(delta);
		batch.draw(crono,40,40);
		batch.draw(red, 2, -1);
		batch.draw(botonp,10,350);
		batch.draw(botone,10,400);
		if (game.gamew.modorodeo){
			batch.draw(botonr,10,450);
		}else{
			batch.draw(boton8min,10,450);
		}
		batch.draw(bmodo,10,550);
		batch.end();
		/*shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(120, 120, 90);
        shapeRenderer.end();
		shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.arc(120, 120, 88, 0f, (360f - 360f*LosNumeros.gamew.gametimereload/GameWorld.GAMETIMERELOAD));
        shapeRenderer.end();*/
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
	
	public void dibujaParedesFicha(Ficha ficha, int x, int y){
		for (int i=0;i<=5;i++){
			if (ficha.paredes[i]){
				switch (i){
					case 0: batch.draw(p2, x ,y); break;
					case 1: batch.draw(p3, x ,y); break;
					case 2: batch.draw(p5, x ,y); break;
					case 3: batch.draw(p7, x ,y); break;
					case 4: batch.draw(p9, x ,y); break;
					case 5: batch.draw(p11, x ,y); break;
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
			
		}else if (ficha.color == FichaColor.ROJO){
			switch (ficha.val){
				case 1: tmp = rr1;break;
				case 2: tmp = rr2;break;
				case 3: tmp = rr3;break;
				case 4: tmp = rr4;break;
				case 5: tmp = rr5;break;
			}
		}else if (ficha.color == FichaColor.AMARILLO){
			switch (ficha.val){
			case 1: tmp = aa1;break;
			case 2: tmp = aa2;break;
			case 3: tmp = aa3;break;
			case 4: tmp = aa4;break;
			case 5: tmp = aa5;break;
			}	
		}else if (ficha.color == FichaColor.E_RODEO){
			tmp = erodeo;
		}else if (ficha.color == FichaColor.LEON){
			tmp = ar1;
		}else if (ficha.color == FichaColor.OSO){
			tmp = ar2;
		}else if (ficha.color == FichaColor.PERRO){
			tmp = ar3;
		}else if (ficha.color == FichaColor.MORSA){
			tmp = ar4;
		}else if (ficha.color == FichaColor.BURRO){
			tmp = ar5;
		}
				
		if (ficha.y % 2 == 0){
			batch.draw(tmp, 343 + 83*ficha.x ,23 +72*ficha.y);
			dibujaParedesFicha(ficha,343 + 83*ficha.x,23 +72*ficha.y);
			//if ( ficha.marcada){
			//	batch.draw(select, 343 + 83*ficha.x ,23 +72*ficha.y);
			//}
		}else{
			batch.draw(tmp, 384 + 83*ficha.x ,23 + 72*ficha.y);
			dibujaParedesFicha(ficha, 384 + 83*ficha.x,23 +72*ficha.y);
			//if (ficha.marcada){
			//	batch.draw(select, 384 + 83*ficha.x ,23 + 72*ficha.y);
			//}
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
