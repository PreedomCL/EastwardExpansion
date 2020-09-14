package game.gfx;
import java.awt.image.BufferedImage;

public class Animation {
	private int duration[], index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	private String tag;
	
	public Animation(int[] speed, BufferedImage[] frames) {
		this.duration = speed;
		this.frames = frames;
		tag = "undefined";
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	public Animation(int[] duration, BufferedImage[] frames, String tag) {
		this.duration = duration;
		this.frames = frames;
		this.tag = tag;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > duration[index]) {
			index++;
			timer = 0;
			if(index >= frames.length)
				index = 0;
		}
	}
	
	public void setFrame(int frame) {
		index = frame;
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	public BufferedImage getFrame(int frame) {
		return frames[frame];
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
