package gameone;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import gameone.crafting.Recipe;
import gameone.crafting.RecipeManager;
import gameone.display.Display;
import gameone.gfx.Assets;
import gameone.gfx.GameCamera;
import gameone.input.KeyManager;
import gameone.input.MouseManager;
import gameone.state.GameState;
import gameone.state.MenuState;
import gameone.state.State;
import gameone.ui.UIManager;

public class Game implements Runnable {
	
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	public State gameState;
	public State menuState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	//Crafting
	private RecipeManager recipeManager;
	
	//UIManager
	private UIManager uiManager;
	
	public Game(String title, int width,int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
		
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		recipeManager = new RecipeManager(handler, System.getProperty("user.dir") + "/res/recipes/recipes.recipe");
		uiManager = new UIManager(handler);
		gameCamera = new GameCamera(handler, 0, 0);
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		handler.getGame().getRecipeManager().init();
		
		
		State.setState(menuState);
		
		
		
	}
	
	private void tick() {
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
		
	}
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		//start code
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		//Draw
		
		if(State.getState() != null)
			State.getState().render(g);
		
		//End Code
		
		bs.show();
		g.dispose();
		
	}
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		while(running) {
			now = System.nanoTime();
			delta += (now-lastTime) / timePerTick;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				delta--;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}	
	public RecipeManager getRecipeManager() {
		return recipeManager;
	}

	public void setRecipeManager(RecipeManager recipeManager) {
		this.recipeManager = recipeManager;
	}

	public UIManager getUiManager() {
		return uiManager;
	}

	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}

	
}
