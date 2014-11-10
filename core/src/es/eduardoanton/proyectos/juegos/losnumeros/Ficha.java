package es.eduardoanton.proyectos.juegos.losnumeros;

import com.badlogic.gdx.math.MathUtils;

public class Ficha {
	public int x;
	public int y;
	public int val;
	public enum FichaColor  { ROJO, GRIS};
	public FichaColor color;
	public boolean marcada;
	public boolean paredes[] = {false,false,false,false,false,false};
	
	public Ficha(int posx,int posy,int val, int color){
		this.x = posx;
		this.y = posy;
		if (color == 0){
			this.color = FichaColor.ROJO;
		}else{
			this.color = FichaColor.GRIS;
		}
		this.marcada = false;
		this.val = val;
	}

	public void generaPared(){
		paredes[MathUtils.random(0,5)] = true;
	}
}
