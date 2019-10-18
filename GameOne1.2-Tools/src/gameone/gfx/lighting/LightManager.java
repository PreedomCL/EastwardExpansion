package gameone.gfx.lighting;

import java.awt.Graphics;
import java.util.ArrayList;

import gameone.Handler;

public class LightManager {
	
	private Handler handler;
	private LightNode[][] nodes;
	private boolean initialized = false;
	
	public LightManager(Handler handler) {
		this.handler = handler;
		initialized = true;
	}
	
	public void init() {
		
		initialized = false;
		
		System.out.println(handler.getWorld());
		nodes = new LightNode[handler.getWorld().getWidth() * 2][handler.getWorld().getHeight() * 2];
		
		for(int x = 0; x < handler.getWorld().getWidth()  * 2; x++) {
			for(int y = 0; y < handler.getWorld().getHeight() * 2; y++) {
				nodes[x][y] = new LightNode(handler, x, y);
			}
		}
	}
	
	public void tick() {
		if(initialized)
			init();
		
		for(int x = 0; x <  handler.getWorld().getWidth()  * 2; x++) {
			for(LightNode n: nodes[x]) {
				n.tick();
			}
		}
	}
	
	public void render(Graphics g) {
//		for(int x = 0; x <  handler.getWorld().getWidth()  * 2; x++) {
//			for(LightNode n: nodes[x]) {
//				n.render(g);
//			}
//		}
	}
}
