package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

import game.crafting.Recipe;
import game.crafting.RecipeManager;
import game.display.Display;
import game.gfx.Assets;
import game.gfx.GameCamera;
import game.gfx.Text;
import game.input.KeyManager;
import game.input.MouseManager;
import game.state.GameState;
import game.state.MenuState;
import game.state.State;
import game.ui.UIManager;

public class Game implements Runnable {
	
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	private int fps;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private boolean debugMode = false;
	
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
	
	//Crafting & Trading
	private RecipeManager recipeManager, tradingManager;
	
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
		tradingManager = new RecipeManager(handler, System.getProperty("user.dir") + "/res/recipes/trades.recipe");
		uiManager = new UIManager(handler);
		gameCamera = new GameCamera(handler, 0, 0);
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		handler.getGame().getRecipeManager().init();
		handler.getGame().getTradingManager().init();
		
		
		State.setState(menuState);
		
		
		
	}
	
	private void tick() {
		keyManager.tick();
		if(State.getState() != null)
			State.getState().tick();
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_H))
			debugMode = !debugMode;
		
	}
	private void render(double delta) {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		//start code
		g = bs.getDrawGraphics();
		
		//Draw
		
		if(State.getState() != null)
			State.getState().render(g);
		if(debugMode) {
			Text.drawString(g,"Delta: " + Float.toString((int) ((delta - 1) * -100)) + "%", 31, 31, false, Color.LIGHT_GRAY, Assets.font14);
			Text.drawString(g,"Delta: " + Float.toString((int) ((delta - 1) * -100)) + "%", 32, 32, false, Color.BLACK, Assets.font14);
		}
		//End Code
		
		bs.show();
		g.dispose();
		
		
	}
	public void run() {
		
		init();
		
		fps = 60;
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
				render(delta);
				delta = 0;
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
	
	public RecipeManager getTradingManager() {
		return tradingManager;
	}

	public void setTradingManager(RecipeManager tradingManager) {
		this.tradingManager = tradingManager;
	}

	public UIManager getUiManager() {
		return uiManager;
	}

	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	
}
