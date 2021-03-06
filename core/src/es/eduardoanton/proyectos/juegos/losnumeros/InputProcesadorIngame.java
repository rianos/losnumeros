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
			gamew.selectCell(x, y);
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
		if (touchpos.x > 4 && touchpos.x < 130 && touchpos.y > 350 && touchpos.y < 390 ){
			gamew.generarPared();
			return true;
		}
		if (touchpos.x > 4 && touchpos.x < 130 && touchpos.y > 550 && touchpos.y < 590 ){
			gamew.cambiarModo();
			return true;
		}
		if (touchpos.x > 4 && touchpos.x < 130 && touchpos.y > 450 && touchpos.y < 490 ){
			gamew.modoRodeo();
			return true;
		}
		if (touchpos.x > 4 && touchpos.x < 130 && touchpos.y > 400 && touchpos.y < 440 ){
			gamew.generarEspecial();
			return true;
		}
		gamew.checkTrail();
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
			gamew.selectCell(x, y);
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
