package es.eduardoanton.proyectos.juegos.losnumeros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;




public class InputProcesadorIngame implements InputProcessor {


	private OrthographicCamera cam;
	private GameWorld gamew;
	private static int lasty,lastx;
	
	InputProcesadorIngame(OrthographicCamera cam, GameWorld gamew){
		this.cam = cam;
		this.gamew = gamew;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 touchpos = new Vector3(screenX,screenY,0);
		cam.unproject(touchpos);
		int x;
		if (touchpos.x > 342 ){
			int y = (int)(touchpos.y - 25)/72;
			if ( y % 2 == 0){
				 x = (int)(touchpos.x - 342)/83;
			}else{
				x = (int)(touchpos.x - 382)/83;
			}
			if (( y >=0 && y <= 8 && x >=0 && x <= 10  )){
				gamew.fichas[x][y].marcada = true;
				gamew.linea.push(gamew.fichas[x][y]);
				lastx = x;
				lasty = y;
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 touchpos = new Vector3(screenX,screenY,0);
		cam.unproject(touchpos);
		Gdx.app.log("LOSNUMEROS", "X: " + touchpos.x + " Y: " + touchpos.y);
		if (touchpos.x > 0 && touchpos.x < 250 && touchpos.y > 00 && touchpos.y < 230 ){
			gamew.generarPanel();
			return true;
		}
		gamew.resetTrail();
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 touchpos = new Vector3(screenX,screenY,0);
		cam.unproject(touchpos);
		int x;
		if (touchpos.x > 342){
			int y = (int)(touchpos.y -25)/72;
			if ( y % 2 == 0){
				x = (int)(touchpos.x - 342)/83;
			}else{
				x = (int)(touchpos.x - 382)/83;
			}
			if ((x != lastx || y != lasty) && ( y >=0 && y <= 8 && x >=0 && x <= 10 )){
				if (gamew.linea.contains(gamew.fichas[x][y])){
					gamew.linea.pop().marcada = false;
				}else{
					gamew.fichas[x][y].marcada = true; 
					gamew.linea.push(gamew.fichas[x][y]);
					lastx = x;
					lasty = y;
				}
			}
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
