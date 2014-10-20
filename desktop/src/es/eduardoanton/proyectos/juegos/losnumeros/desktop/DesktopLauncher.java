package es.eduardoanton.proyectos.juegos.losnumeros.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import es.eduardoanton.proyectos.juegos.losnumeros.LosNumeros;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 600;
		config.width = 1024;
		new LwjglApplication(new LosNumeros(), config);
	}
}
