package es.eduardoanton.proyectos.juegos.losnumeros;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LosNumeros extends Game {
	public static AssetManager asset;
	public static Preferences prefs;
	public final static float screenwidth = 1280f;
	public final static float screenheight = 720f;
	public static Screen loadingscreen,ingamescreen;
	public static GameWorld gamew;
	@Override
	public void create() {
		loadassets();
		prefs = Gdx.app.getPreferences("LosNumeros");
		loadingscreen = new LoadingScreen(this);
		this.setScreen(loadingscreen);
	}
	
	public void dispose(){
		super.dispose();
		asset.dispose();
	}
	
	public void loadassets(){
		asset = new AssetManager();
		asset.load("loading.png", Texture.class);
		asset.load("empty.png", Texture.class);
		asset.load("full.png", Texture.class);
		asset.load("fondo.png", Texture.class);
		asset.load("marco.png", Texture.class);
		asset.load("red.png",Texture.class);
		asset.load("rr1.png",Texture.class);
		asset.load("rr2.png",Texture.class);
		asset.load("rr3.png",Texture.class);
		asset.load("rr4.png",Texture.class);
		asset.load("rr5.png",Texture.class);
		asset.load("gr1.png",Texture.class);
		asset.load("gr2.png",Texture.class);
		asset.load("gr3.png",Texture.class);
		asset.load("gr4.png",Texture.class);
		asset.load("gr5.png",Texture.class);
		asset.load("aa1.png",Texture.class);
		asset.load("aa2.png",Texture.class);
		asset.load("aa3.png",Texture.class);
		asset.load("aa4.png",Texture.class);
		asset.load("aa5.png",Texture.class);
		asset.load("crono.png",Texture.class);
		asset.load("select.png", Texture.class);
		asset.load("l3.png", Texture.class);
		asset.load("l5.png", Texture.class);
		asset.load("l7.png", Texture.class);
		asset.load("p2.png", Texture.class);
		asset.load("p3.png", Texture.class);
		asset.load("p5.png", Texture.class);
		asset.load("p7.png", Texture.class);
		asset.load("p9.png", Texture.class);
		asset.load("p11.png", Texture.class);
		asset.load("botonp.png", Texture.class);
		asset.load("bparedes.png", Texture.class);
		asset.load("bamarillas.png", Texture.class);
		asset.load("app_game_interactive_alert_tone_007.mp3", Sound.class);
		asset.load("button_switch_gear_multimedia_web_interactive.mp3", Sound.class);
		asset.load("multimedia_event_tone_2.mp3", Sound.class);
		asset.load("clock_digital_alarm_beeping_003.mp3", Sound.class);
	}
	
	public void mainscreen(){
		gamew = new GameWorld(this);
		//mainscreen = new MainScreen(this);
		ingamescreen = new IngameScreen( this );
		//gameoverscreen = new GameOverScreen(this);
		//creditsscreen = new CreditsScreen(this);
		//instructionscreen = new InstructionsScreen(this);
		this.setScreen(ingamescreen);
	}
}