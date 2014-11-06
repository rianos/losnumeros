package es.eduardoanton.proyectos.juegos.losnumeros;

public class Ficha {
	public int x;
	public int y;
	public int val;
	public enum FichaColor  { ROJO, GRIS};
	public FichaColor color;
	public boolean marcada;
	
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
	/*
	public String getTexture(){
		String res = "";
		switch(this.color){
			case ROJO: res = "rr";
						break;
			case GRIS: res = "gr";
		   		     break;
		}
		res = res + val + ".png";
		return res;
	}*/
}
