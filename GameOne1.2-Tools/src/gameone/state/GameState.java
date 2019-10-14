package gameone.state;
import java.awt.Graphics;



import gameone.Handler;
import gameone.crafting.Recipe;
import gameone.entities.creature.NPC;
import gameone.entities.creature.Player;
import gameone.entities.creature.RockbugCreature;
import gameone.entities.staticentity.EnterableBuilding;
import gameone.entities.staticentity.MapChanger;
import gameone.entities.staticentity.Stone;
import gameone.entities.staticentity.Tree;
import gameone.gfx.Assets;
import gameone.ui.UIManager;
import gameone.utils.Utils;
import gameone.worlds.EntityLoader;
import gameone.worlds.World;

public class GameState extends State {
	

	private World world;
	
	
	public GameState(Handler handler) {
		super(handler);
		handler.setPlayer(new Player(handler, 100, 100));
		handler.setTown(new World(handler, System.getProperty("user.dir") + "/res/world/world1.txt"));
		handler.setWorld(handler.getTown());
		world = handler.getWorld();
		
		
		world.setEntityLoader(new EntityLoader() {
			@Override
			public void loadEntities() {
				handler.getWorld().getEntityManager().getEntitiesToAdd().add(new Tree(handler, 500, 500));
				String[] speech;
				Recipe[] trades;
				handler.getWorld().getEntityManager().getEntitiesToAdd().add(new NPC(handler, Assets.player[0],speech = new String[] {"Hello, I am your trusty blacksmith.", "Would you like to trade or smelt some ore?"}, trades = new Recipe[] {handler.getGame().getTradingManager().getRecipes()[0]}, 750, 580, 32, 64));
				handler.getWorld().getEntityManager().getEntitiesToAdd().add(new EnterableBuilding(handler, Assets.blacksmith, 648, 500, 220, 128));
				handler.getWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 500, 1000, 100, 10,550,516, handler.getWorld2()));
				
				
				//Random
				for(int i=0; i < Utils.randomNumber(20, 5); i++) { 
					handler.getWorld().getEntityManager().getEntitiesToAdd().add(new Stone(handler, Utils.randomNumber(1300, 0), Utils.randomNumber(1300, 0)));
				}
				for(int i=0; i <Utils.randomNumber(10, 5); i++) { 
					handler.getWorld().getEntityManager().getEntitiesToAdd().add(new Tree(handler, Utils.randomNumber(1300, 0), Utils.randomNumber(1300, 0)));
				}
				for(int i=0; i < Utils.randomNumber(20, 5); i++) { 
					handler.getWorld().getEntityManager().getEntitiesToAdd().add(new RockbugCreature(handler, Utils.randomNumber(1300, 0),Utils.randomNumber(1300, 0)));
				}
				}
		});
		handler.getWorld().loadEntities();
		
		
		handler.setWorld2(new World(handler, System.getProperty("user.dir") + "/res/world/world2.txt"));
		handler.getWorld2().setEntityLoader(new EntityLoader() {
			@Override
			public void loadEntities() {
				
			}
		});
		handler.getWorld2().loadEntities();
	}
	
	public void tick() {
		world = handler.getWorld();
		world.tick();
	}
	public void render(Graphics g) {
		world.render(g);
	}

	
	
}
