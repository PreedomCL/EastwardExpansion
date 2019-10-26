package game.gfx.lighting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;

import game.Handler;

public class LightNode {
	
	private Handler handler;
	private int level = 0;
	private boolean updated = false;
	private int x, y;
	private Color[] color;
	
	public LightNode(Handler handler, int x, int y) {
		this.x = x;
		this.y = y;
		
		color = new Color[9];
		
		for(int c = 0; c < color.length; c++) {
			color[c] = new Color(0f, 0f, 0f, .5f);		
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics gr) {
		Graphics2D g = (Graphics2D)gr;
		
	}
	
	
	//G&S
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	
	
	
}
