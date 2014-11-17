package es.eduardoanton.proyectos.juegos.losnumeros;

import java.util.Stack;

import javafx.scene.shape.Line;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;

import es.eduardoanton.proyectos.juegos.losnumeros.Ficha.FichaColor;


public class GameWorld {
	private LosNumeros game;
	public Ficha[][] fichas;
	public Stack<Ficha> linea;
	public int lastx,lasty,lastsx,lastsy;
	public int grises, rojos,amarillos,puntos;
	public float gametime,gametimereload;
	public boolean famarillas,modorodeo;
	public Sound correctoS,failS,recargaS,timeS;
	private boolean timeexpired = false;
	public static float GAMETIME = 180;
	public static int MINROWSPECIAL = 8;
	public static int PRIZE_ESPECIAL = 50;
	
	public GameWorld(LosNumeros game){
		this.game = game;
		puntos = 0;
		gametime =  GAMETIME;
		famarillas = false;
		modorodeo = true;
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
		amarillos = 0;
		for ( Ficha fichita : linea){
			switch( fichita.color){
				case GRIS: 	grises+=fichita.val;break;
				case ROJO: 	rojos+=fichita.val;break;
				case AMARILLO: amarillos+=fichita.val;break;
			}
		}
	}
	
	public void update(float delta){
		calculate();
		gametime-=delta;
		gametimereload-=delta;
		if ( gametime < 0f && !timeexpired){
			timeexpired = true;
			timeS.play();
		}
	}
	
	public void generarPanel(){
		recargaS.play();
		int limite = 1;
		if (famarillas){
			limite = 2;
		}
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				fichas[i][j] = new Ficha(i,j,MathUtils.random(1, 5),MathUtils.random(0,limite));
			}
		}
		lastx = -1;
		lasty = -1;
		gametime-=5f;
	}
	
	public void generarPared(){
		fichas[MathUtils.random(0,10)][MathUtils.random(0,8)].generaPared();
	}
	
	private boolean isContigua(int x, int y){
		if ( y %2 != 0){
			return (lastx ==  x-1 && lasty == y) ||
					(lastx == x && lasty == y - 1) ||
					(lastx == x + 1 && lasty == y - 1 ) ||
					(lastx == x + 1 && lasty == y ) ||
					(lastx == x + 1 && lasty == y +1 ) ||
					(lastx == x && lasty == y +1 ) ||
					(lastx == -1 && lasty == -1);
		}else{
			return (lastx ==  x-1 && lasty == y) ||
					(lastx == x && lasty == y - 1) ||
					(lastx == x - 1 && lasty == y - 1 ) ||
					(lastx == x + 1 && lasty == y ) ||
					(lastx == x - 1 && lasty == y +1 ) ||
					(lastx == x && lasty == y +1 ) ||
					(lastx == -1 && lasty == -1);
		}
	}
	
	private boolean isContiguaLastSelected(int x, int y){
		if ( y %2 != 0){
			return (lastsx ==  x-1 && lastsy == y) ||
					(lastsx == x && lastsy == y - 1) ||
					(lastsx == x + 1 && lastsy == y - 1 ) ||
					(lastsx == x + 1 && lastsy == y ) ||
					(lastsx == x + 1 && lastsy == y +1 ) ||
					(lastsx == x && lastsy == y +1 ) ||
					(lastsx == -1 && lastsy == -1);
		}else{
			return (lastsx ==  x-1 && lastsy == y) ||
					(lastsx == x && lastsy == y - 1) ||
					(lastsx == x - 1 && lastsy == y - 1 ) ||
					(lastsx == x + 1 && lastsy == y ) ||
					(lastsx == x - 1 && lastsy == y +1 ) ||
					(lastsx == x && lastsy == y +1 ) ||
					(lastsx == -1 && lastsy == -1);
		}
	}
	
	private void noSelected(int x, int y){
		// Si es la primera la seleccionamos si o si
		if (linea.size() < 1 ){
			fichas[x][y].marcada = true; 
			linea.push(fichas[x][y]);
			lastx = x;
			lasty = y;
			lastsx = x;
			lastsy = y;
		}else {
			// Si es contigua a la ultima seleccionada la marcamos
			if (isContiguaLastSelected(x,y) && noPared(x,y)){		
				fichas[x][y].marcada = true; 
				linea.push(fichas[x][y]);
				lastx = x;
				lasty = y;
				lastsx = x;
				lastsy = y;
			// No es contigua a la seleccioanda
			}else{
				lastx = x;
				lasty = y;
			}
		}
	}
	
	
	public boolean noPared(int x, int y){
		Ficha ficha = fichas[lastsx][lastsy];
		Ficha ficha2 = fichas[x][y];
		if ( y == lastsy && x == lastsx + 1){ // Esta a las 3
			Gdx.app.log("LOG","1");
			return !ficha.paredes[1] && !ficha2.paredes[4];
		}else if (  y == lastsy && x == lastsx - 1){ // Esta a las 9
			Gdx.app.log("LOG","2");
			return !ficha.paredes[4]&& !ficha2.paredes[1];
		}else if ( x == lastsx && y == lastsy - 1 && lastsy % 2 == 0){ // esta a las 5
			Gdx.app.log("LOG","3");
			return !ficha.paredes[2]&& !ficha2.paredes[5];
		}else if ( x == lastsx +1 && y == lastsy - 1){
			Gdx.app.log("LOG","4");
			return !ficha.paredes[2]&& !ficha2.paredes[5];
		}else if ( x == lastsx && y == lastsy + 1 && lastsy % 2 != 0 ){
			Gdx.app.log("LOG","5");
			return !ficha.paredes[5]&& !ficha2.paredes[2];
		}else if ( x == lastsx -1 && y == lastsy + 1 ){
			Gdx.app.log("LOG","6");
			return !ficha.paredes[5]&& !ficha2.paredes[2];
		}else if ( x == lastsx && y == lastsy - 1 ){
			Gdx.app.log("LOG","7");
			return !ficha.paredes[3]&& !ficha2.paredes[0];
		}else if ( x == lastsx - 1 && y == lastsy - 1 ){
			Gdx.app.log("LOG","8");
			return !ficha.paredes[3]&& !ficha2.paredes[0];
		}else if ( x == lastsx  && y == lastsy + 1 ){
			Gdx.app.log("LOG","9");
			return !ficha.paredes[0]&& !ficha2.paredes[3];
		}else if ( x == lastsx + 1 && y == lastsy + 1 ){
			Gdx.app.log("LOG","10");
			return !ficha.paredes[0] && !ficha2.paredes[3];
		}
		return false;
	}
	
	public void generarAmarillas(){
		famarillas = !famarillas;
	}
	
	public void modoRodeo(){
		modorodeo = !modorodeo;
	}
	
	public void generarEspecial(){
		int x = MathUtils.random(0,10);
		int y = MathUtils.random(0,8);
		fichas[x][y] = new Ficha(x,y,5,3);	
	}
	
	public void selectCell(int x, int y){
		// Comprobamos si se ha seleccionado una celda
		if (( y % 2 == 0 && y >=0 && y<=8 && x<=10 && x >= 0) ||
			( y % 2 != 0 && y>=0 && y<=8 && x<=9 && x>=0)){
			if (isContigua(x,y)){ 
				//Esta la ficha seleccionada
				if (linea.contains(fichas[x][y])){
					if (((linea.size() -2) > -1) && linea.elementAt(linea.size() - 2) == fichas[x][y]){
						linea.pop().marcada = false;
						lastsx = linea.lastElement().x;
						lastsy = linea.lastElement().y;
					}
					lastx = x;
					lasty = y;
				}else{
					if (fichas[x][y].color != FichaColor.E_RODEO && modorodeo ){
						noSelected(x,y);
					}else if (!modorodeo){
						noSelected(x,y);
					}else{
						lastx = x;
						lasty = y;
					}
				}
			}
		}	
	}
	
	private void failTrail(){
		failS.play();
		linea.clear();
		lastx = -1;
		lasty = -1;
		lastsx = -1;
		lastsy = -1;
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				fichas[i][j].marcada = false;
			}
		}
		// Penalizo 10 segundos un fallo
		gametime-=10f;
	}
	
	private void nullTrail(){
		linea.clear();
		lastx = -1;
		lasty = -1;
		lastsx = -1;
		lastsy = -1;
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				fichas[i][j].marcada = false;
			}
		}
	}
	
	private void psuccessTrail(){
		if (modorodeo){
			checkRodeo();
			successTrail();
		}else{
			if ( contieneEspecial()){
				if (linea.size() >= GameWorld.MINROWSPECIAL){
					puntos+=GameWorld.PRIZE_ESPECIAL;
					successTrail();
				}else{
					failTrail();
				}
			}else{
				successTrail();
			}
		}
	}
	
	private void checkRodeo(){
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				if (fichas[i][j].color == FichaColor.E_RODEO ){
					if (checkMeRodea(i,j)){
						int limite = 1;
						if (famarillas){
							limite = 2;
						}
						fichas[i][j] =new Ficha(i,j,MathUtils.random(1, 5),MathUtils.random(0,limite));	
						puntos+=100;
					}
				}
			}
		}
	}
	
	private boolean checkMeRodea(int x, int y){
		Stack<Ficha> vecinos;
		boolean rodeado = true;
		vecinos = getVecinos(x,y);
		Gdx.app.log("ESPCIAS", "Mis vecinos" + x + " -- " + y);
		for (Ficha vecino : vecinos){
			Gdx.app.log("SSS", "Mis vecinos" + vecino.x + " -- " + vecino.y);
			if (! linea.contains(fichas[vecino.x][vecino.y])){
				rodeado = false;break;
			}
		}
		return rodeado;
	}
	
	private Stack<Ficha> getVecinos(int x,int y){
		Stack<Ficha> temp = new Stack<Ficha>();
		if ( y %2 != 0){
			if (mete_ficha(x-1,y)){
				temp.add(new Ficha(x-1,y));
				Gdx.app.log("--1","--1");
			}
			if	(mete_ficha(x ,y - 1)){
				temp.add(new Ficha(x ,y - 1));
				Gdx.app.log("--2","--1");
			}
			if (mete_ficha(x + 1 ,y - 1)){
				temp.add(new Ficha(x + 1 ,y - 1));
				Gdx.app.log("--3","--1");
			}
			if (mete_ficha(x + 1,y)){
				temp.add(new Ficha(x + 1 ,y));
				Gdx.app.log("--4","--1");
			}
			if (mete_ficha(x + 1,y + 1)){
				temp.add(new Ficha(x + 1 ,y + 1));
				Gdx.app.log("--5","--1");
			}
			if (mete_ficha(x,y + 1)){
				temp.add(new Ficha(x ,y + 1));
				Gdx.app.log("--6","--1");
			}
		
		}else{
			if (mete_ficha(x-1,y)){
				temp.add(new Ficha(x-1,y));
				Gdx.app.log("--1","--2");
			}
			if (mete_ficha(x ,y - 1)){
				temp.add(new Ficha(x, y -1 ));
				Gdx.app.log("--2","--2");
			}
			if (mete_ficha(x - 1 ,y - 1)){
				temp.add(new Ficha(x-1,y-1));
				Gdx.app.log("--3","--2");
			}
			if (mete_ficha(x + 1,y)){
				temp.add(new Ficha(x+1,y));
				Gdx.app.log("--4","--2");
			}
			if (mete_ficha(x - 1,y + 1)){
				temp.add(new Ficha(x-1,y+1));
				Gdx.app.log("--5","--2");
			}
			if (mete_ficha(x,y + 1)){
				temp.add(new Ficha(x, y +1));
				Gdx.app.log("--6","--2");
			}
		}
		return temp;
	}
	
	private boolean mete_ficha(int x, int y){
		boolean ret = true;
		if (y % 2 == 0){
			if (x >= 11 || x < 0){
				ret = false;
			}
			if (y >= 9 || y < 0){
				ret = false;
			}
		}else{
			if (x >= 10 || x < 0){
				ret = false;
			}
			if (y >= 9 || y < 0){
				ret = false;
			}
		}
		return ret;
	}
	
	
	private boolean contieneEspecial(){
		boolean resultado = false;
		for (Ficha ficha : linea){		
			if (ficha.color == FichaColor.E_RODEO ){
				resultado = true;break;
			}
		}
		return resultado;
	}
	
	private void successTrail(){
		correctoS.play();
		lastx = -1;
		lasty = -1;
		lastsx = -1;
		lastsy = -1;
		puntos += linea.size();
		int limite = 1;
		if (famarillas){
			limite = 2;
		}
		// Si la linea es de 3 damos un segundo, y 2 segundos por cada celda mayor que 2
		if ( linea.size() >= 3){
			gametime = Math.min(GAMETIME, gametime + (1 + ((linea.size() - 3)*2)));
			puntos += (linea.size() -3)*2;
		}
		for (Ficha ficha : linea){		
			fichas[ficha.x][ficha.y] =new Ficha(ficha.x,ficha.y,MathUtils.random(1, 5),MathUtils.random(0,limite));	
		}
		linea.clear();
	}
	
	public void checkTrail(){
		calculate();
		if (grises == rojos && rojos == amarillos){
			psuccessTrail();
		}else if (amarillos == 0 && grises == rojos){
			psuccessTrail();
		}else if (grises == 0 && rojos == amarillos){
			psuccessTrail();
		}else if (rojos == 0 && grises == amarillos){
			psuccessTrail();
		}else if (linea.size() == 1){
			nullTrail();
		}else{
			failTrail();
		}
	}
}
