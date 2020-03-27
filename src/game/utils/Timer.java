package game.utils;

public class Timer {
	private long lastTime, timer;
	private boolean active;
	
	public Timer() {
		active = false;
		
	}
	
	public void start(long timer) {
		if (!active) {
			this.timer = timer;
			lastTime = System.currentTimeMillis();
		}
		active = true;
	}
	
	public void stop() {
		active = false;
	}
	
	public boolean run() {
		
		if(active) {
			timer -= (System.currentTimeMillis() - lastTime);
			if(timer <= 0)
				return true;
		}
		lastTime = System.currentTimeMillis();
		return false;
	}
}
