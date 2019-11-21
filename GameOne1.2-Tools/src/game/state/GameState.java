package game.state;
import java.awt.Graphics;

import game.Handler;
import game.crafting.Recipe;
import game.entities.creature.DonkeyCreature;
import game.entities.creature.NPC;
import game.entities.creature.Player;
import game.entities.creature.RockbugCreature;
import game.entities.staticentity.Bridge;
import game.entities.staticentity.EnterableBuilding;
import game.entities.staticentity.MapChanger;
import game.entities.staticentity.Stone;
import game.entities.staticentity.Tree;
import game.gfx.Assets;
import game.ui.UIManager;
import game.utils.Utils;
import game.worlds.EntityLoader;
import game.worlds.World;

public class GameState extends State {
	

	private World world;
	
	
	public GameState(Handler handler) {
		super(handler);
		handler.setPlayer(new Player(handler, 100, 100));
		
		handler.setTown(new World(handler, System.getProperty("user.dir") + "/res/world/world1.txt"));
		handler.setWorld2(new World(handler, System.getProperty("user.dir") + "/res/world/world2.txt"));
//Town **** *** *** *** *** *** *** ****
		handler.setWorld(handler.getTown());
		world = handler.getWorld();
		world.setEntityLoader(new EntityLoader() {
			@Override
			public void loadEntities() {
				handler.getWorld().getEntityManager().getEntitiesToAdd().add(new Tree(handler, 500, 500));
				String[] speech;
				Recipe[] trades;
				handler.getWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 992, 1216, 63, 32,992,1150, 2));
				handler.getWorld().getEntityManager().getEntitiesToAdd().add(new NPC(handler, Assets.player[0],speech = new String[] {"Hello, I am your trusty blacksmith.", "Would you like to trade or smelt some ore?"}, trades = new Recipe[] {handler.getGame().getTradingManager().getRecipes()[0], handler.getGame().getTradingManager().getRecipes()[1],  handler.getGame().getTradingManager().getRecipes()[2]}, 750, 580, 32, 64));
				handler.getWorld().getEntityManager().getEntitiesToAdd().add(new EnterableBuilding(handler, Assets.blacksmith, 648, 500, 220, 128));
				handler.getWorld().getEntityManager().getEntitiesToAdd().add(new DonkeyCreature(handler, 200, 200, true));
				
				
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
		
		handler.setTown(handler.getWorld());
		handler.getTown().loadEntities();
		
//World 2 **** *** *** *** *** *** *** ****
		handler.setWorld(handler.getWorld2());
		
		handler.getWorld().setEntityLoader(new EntityLoader() {
			@Override
			public void loadEntities() {
				handler.getWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 64,32, 63, 32,64,64, 1));
				handler.getWorld().getEntityManager().getEntitiesToAdd().add(new Bridge(handler, 640, 320, 64, 64));
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
		
		handler.setWorld2(handler.getWorld());
		handler.getWorld2().loadEntities();
		
//Set Initial World **** *** *** *** *** ****
		handler.setWorld(handler.getTown());
	}
	
	public void tick() {
		world = handler.getWorld();
		world.tick();
	}
	public void render(Graphics g) {
		world.render(g);
	}

	
	
}
