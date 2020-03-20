package game.gfx.lighting;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class LightManager {
	
	private ArrayList<LightSource> lights = new ArrayList<LightSource>();
	
	public LightManager() {
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
//		Graphics2D gg = (Graphics2D) g;
//		for(LightSource l: lights) {
//			l.render(g);
//		}
//		
//		gg.fillRect(0, 0, 1500, 1500);
//		gg.dispose();
	}

	public void addLight(LightSource light) {
		lights.add(light);
	}
	
	public ArrayList<LightSource> getLights() {
		return lights;
	}

	public void setLights(ArrayList<LightSource> lights) {
		this.lights = lights;
	}
	
	
}
