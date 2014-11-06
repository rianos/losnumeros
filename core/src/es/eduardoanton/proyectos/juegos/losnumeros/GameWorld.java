package es.eduardoanton.proyectos.juegos.losnumeros;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;

import es.eduardoanton.proyectos.juegos.losnumeros.Ficha.FichaColor;


public class GameWorld {
	private LosNumeros game;
	public Ficha[][] fichas;
	public Stack<Ficha> linea;
	public int lastx,lasty;
	public int grises, rojos,puntos;
	public float gametime;
	public Sound correctoS,failS,recargaS,timeS;
	private boolean timeexpired = false;
	public static float GAMETIME = 120;

	public GameWorld(LosNumeros game){
		this.game = game;
		puntos = 0;
		gametime =  GAMETIME;
		fichas = new Ficha[11][9];
		linea = new Stack<Ficha>();
		correctoS= LosNumeros.asset.get("app_game_interactive_alert_tone_007.mp3", Sound.class);
		failS = LosNumeros.asset.get("multimedia_event_tone_2.mp3", Sound.class);
		recargaS =  LosNumeros.asset.get("button_switch_gear_multimedia_web_interactive.mp3", Sound.class);
		timeS = LosNumeros.asset.get("clock_digital_alarm_beeping_003.mp3", Sound.class);
		generarPanel();
	}
	
	private void calculate(){
		rojos = 0;
		grises = 0;
		for ( Ficha fichita : linea){
			if (fichita.color == FichaColor.GRIS){
				grises+=fichita.val;
			}else{
				rojos+=fichita.val;
			}
		}
	}
	
	public void update(float delta){
		calculate();
		gametime-=delta;
		if ( gametime < 0f && !timeexpired){
			timeexpired = true;
			timeS.play();
		}
	}
	
	public void generarPanel(){
		recargaS.play();
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				fichas[i][j] = new Ficha(i,j,MathUtils.random(1, 5),MathUtils.random(0,1));
			}
		}
		lastx = -1;
		lasty = -1;
		gametime-=5f;
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
		failS.play();
		linea.clear();
		lastx = -1;
		lasty = -1;
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				fichas[i][j].marcada = false;
			}
		}
		// Penalizo 10 segundos un fallo
		gametime-=10f;
	}
	
	private void successTrail(){
		correctoS.play();
		lastx = -1;
		lasty = -1;
		puntos += linea.size();
		// Si la linea es de 3 damos un segundo, y 2 segundos por cada celda mayor que 2
		if ( linea.size() >= 3){
			gametime = Math.min(GAMETIME, gametime + (1 + ((linea.size() - 3)*2)));
			puntos += (linea.size() -3)*2;
		}
		for (Ficha ficha : linea){
			fichas[ficha.x][ficha.y] =new Ficha(ficha.x,ficha.y,MathUtils.random(1, 5),MathUtils.random(0,1));	
		}
		linea.clear();
	}
	
	public void checkTrail(){
		calculate();
		if (grises == rojos){
			successTrail();
		}else{
			failTrail();
		}
	}
}
