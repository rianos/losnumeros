package es.eduardoanton.proyectos.juegos.losnumeros;

import com.badlogic.gdx.math.MathUtils;

public class GameWorld {
	private LosNumeros game;
	public Ficha[][] fichas;

	public GameWorld(LosNumeros game){
		this.game = game;
		fichas = new Ficha[11][9];
		generarPanel();
	}
	
	public void update(float delta){
		
	}
	
	public void generarPanel(){
		for (int j=0;j<9;j++){
			for (int i=0;i<11;i++){
				fichas[i][j] = new Ficha(i,j,MathUtils.random(1, 3),MathUtils.random(0,1));
			}
		}
	}
}
