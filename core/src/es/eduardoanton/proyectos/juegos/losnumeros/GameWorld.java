package es.eduardoanton.proyectos.juegos.losnumeros;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

import es.eduardoanton.proyectos.juegos.losnumeros.Ficha.FichaColor;


public class GameWorld {
	private LosNumeros game;
	public Ficha[][] fichas;
	public Stack<Ficha> linea;

	public GameWorld(LosNumeros game){
		this.game = game;
		fichas = new Ficha[11][9];
		linea = new Stack<Ficha>();
		generarPanel();
	}
	
	public void update(float delta){
		int rojos = 0;
		int amarillos = 0;
		for ( Ficha fichita : linea){
			if (fichita.color == FichaColor.AMARILLO){
				amarillos+=fichita.val;
			}else{
				rojos+=fichita.val;
			}
		}
		Gdx.app.log("LOS", "AMARILLAS " + amarillos + " ROJOS: " + rojos);
	}
	
	public void generarPanel(){
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				fichas[i][j] = new Ficha(i,j,MathUtils.random(1, 3),MathUtils.random(0,1));
			}
		}
	}
	
	public void resetTrail(){
		linea.clear();
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				fichas[i][j].marcada = false;
			}
		}
	}
}
