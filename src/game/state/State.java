package game.state;
import java.awt.Graphics;

import game.Handler;
import game.ui.UIManager;

public abstract class State {
	
	private static State currentState = null;
	protected int time = 1000;
	
	public static void setState(State state) {
		currentState = state;
		
	}
	
	public static State getState() {
		return currentState;
	}
	
	//class
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();

	public abstract void render( Graphics g);

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	
	
		
	
}
