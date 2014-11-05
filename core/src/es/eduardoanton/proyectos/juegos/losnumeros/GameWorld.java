package es.eduardoanton.proyectos.juegos.losnumeros;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

import es.eduardoanton.proyectos.juegos.losnumeros.Ficha.FichaColor;


public class GameWorld {
	private LosNumeros game;
	public Ficha[][] fichas;
	public Stack<Ficha> linea;
	public int lastx,lasty;
	public int amarillos, rojos,puntos;

	public GameWorld(LosNumeros game){
		this.game = game;
		puntos = 0;
		fichas = new Ficha[11][9];
		linea = new Stack<Ficha>();
		generarPanel();
	}
	
	public void update(float delta){
		rojos = 0;
		amarillos = 0;
		for ( Ficha fichita : linea){
			if (fichita.color == FichaColor.AMARILLO){
				amarillos+=fichita.val;
			}else{
				rojos+=fichita.val;
			}
		}
		//Gdx.app.log("LOS", "AMARILLAS " + amarillos + " ROJOS: " + rojos);
	}
	
	public void generarPanel(){
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				fichas[i][j] = new Ficha(i,j,MathUtils.random(1, 3),MathUtils.random(0,1));
			}
		}
		lastx = -1;
		lasty = -1;
	}
	
	public void selectCell(int x, int y){
		// Comprobamos si se ha seleccionado una celda
		if (( y % 2 == 0 && y >=0 && y<=8 && x<=10 && x >= 0) ||
			( y % 2 != 0 && y>=0 && y<=8 && x<=9 && x>=0)){
			// Comprobamos que la celda sea una contigua
			//Gdx.app.log("GG", "x: " + x + " y: " + y + " lastx: " + lastx + " lasty: " +lasty);
			if (
				(lastx !=x && lasty !=y && ((x == lastx + 1 || x == lastx - 1 || lastx == -1) && (y == lasty + 1 || y == lasty - 1 || lasty == -1)))
			||	(lastx != x && lasty == y && (x == lastx + 1 || x == lastx - 1 || lastx == -1))
			||  (lasty != y  && lastx == x && (y == lasty + 1 || y == lasty - 1 || lasty == -1))
				){ 
				if (linea.contains(fichas[x][y])){
					linea.pop().marcada = false;
				}else{
					fichas[x][y].marcada = true; 
					linea.push(fichas[x][y]);
					lastx = x;
					lasty = y;
				}
			}
		}	
	}
	
	private void failTrail(){
		linea.clear();
		lastx = -1;
		lasty = -1;
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				fichas[i][j].marcada = false;
			}
		}
	}
	
	private void successTrail(){
		lastx = -1;
		lasty = -1;
		for (Ficha ficha : linea){
			fichas[ficha.x][ficha.y] =new Ficha(ficha.x,ficha.y,MathUtils.random(1, 3),MathUtils.random(0,1));	
		}
		linea.clear();
	}
	
	public void checkTrail(){
		update(0f);
		if (amarillos == rojos){
			puntos += amarillos;
			successTrail();
		}else{
			failTrail();
		}
	}
}
