package gameone.state;
import java.awt.Graphics;

import gameone.Handler;
import gameone.entities.staticentity.Tree;
import gameone.ui.UIManager;
import gameone.worlds.EntityLoader;
import gameone.worlds.World;

public class GameState extends State {
	

	private World world;
	
	
	public GameState(Handler handler) {
		super(handler);
		handler.setTown(new World(handler, System.getProperty("user.dir") + "/res/world/world1.txt"));
		handler.setWorld(handler.getTown());
		world = handler.getWorld();
		world.setEntityLoader(new EntityLoader() {
			@Override
			public void loadEntities() {
				handler.getWorld().getEntityManager().getEntitiesToAdd().add(new Tree(handler, 500, 500));
			}
		});
		handler.getWorld().loadEntities();
		
		
		handler.setWorld2(new World(handler, System.getProperty("user.dir") + "/res/world/world2.txt"));
	}
	
	public void tick() {
		world = handler.getWorld();
		world.tick();
	}
	public void render(Graphics g) {
		world.render(g);
	}

	
	
}
