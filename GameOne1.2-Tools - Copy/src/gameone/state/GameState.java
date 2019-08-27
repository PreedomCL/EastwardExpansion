package gameone.state;
import java.awt.Graphics;

import gameone.Handler;
import gameone.worlds.World;

public class GameState extends State {
	

	private World world;
	
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "C:/Users/coenr/eclipse-workspace/GameOne-res/world/world1.txt");
		handler.setWorld(world);
		
		
		
	}
	
	public void tick() {
		world.tick();
		
		
	}

	
	public void render(Graphics g) {
		world.render(g);
	}
	
}
