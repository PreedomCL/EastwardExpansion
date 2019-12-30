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
		handler.setWorld3(new World(handler, System.getProperty("user.dir") + "/res/world/world3.txt"));
//Town **** *** *** *** *** *** *** ****
		handler.setCurrentWorld(handler.getTown());
		handler.getCurrentWorld().setEntityLoader(new EntityLoader() {
			@Override
			public void loadEntities() {
				String[] speech;
				Recipe[] trades;
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 992, 1216, 63, 32,992,1150, 2));
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new NPC(handler, Assets.player[0],speech = new String[] {"Hello, I am your trusty blacksmith.", "Would you like to trade or smelt some ore?"}, trades = new Recipe[] {handler.getGame().getTradingManager().getRecipes()[0], handler.getGame().getTradingManager().getRecipes()[1],  handler.getGame().getTradingManager().getRecipes()[2]}, 750, 580, 32, 64));
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new EnterableBuilding(handler, Assets.blacksmith, 648, 500, 220, 128));
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new DonkeyCreature(handler, 200, 200, true));
				
				
				//Random
				for(int i=0; i < Utils.randomNumber(20, 5); i++) { 
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new Stone(handler, Utils.randomNumber(1300, 0), Utils.randomNumber(1300, 0)));
				}
				for(int i=0; i <Utils.randomNumber(10, 5); i++) { 
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new Tree(handler, Utils.randomNumber(1300, 0), Utils.randomNumber(1300, 0)));
				}
				for(int i=0; i < Utils.randomNumber(20, 5); i++) { 
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new RockbugCreature(handler, Utils.randomNumber(1300, 0),Utils.randomNumber(1300, 0)));
				}
			}
		});
		
		handler.setTown(handler.getCurrentWorld());
		handler.getTown().loadEntities();
		
//World 2 **** *** *** *** *** *** *** ****
		handler.setCurrentWorld(handler.getWorld2());
		handler.getCurrentWorld().setEntityLoader(new EntityLoader() {
			@Override
			public void loadEntities() {
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 64,32, 63, 32,64,32, 1));
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 1216, 320, 32, 64, 1200, 336, 3));
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new Bridge(handler, 640, 320, 64, 64, 400, 64, handler.getGame().getTradingManager().getRecipes()[3]));
				//Random
				for(int i=0; i < Utils.randomNumber(20, 5); i++) { 
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new Stone(handler, Utils.randomNumber(1300, 0), Utils.randomNumber(1300, 0)));
				}
				for(int i=0; i <Utils.randomNumber(10, 5); i++) { 
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new Tree(handler, Utils.randomNumber(1300, 0), Utils.randomNumber(1300, 0)));
				}
				for(int i=0; i < Utils.randomNumber(20, 5); i++) {
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new RockbugCreature(handler, Utils.randomNumber(1300, 0),Utils.randomNumber(1300, 0)));
				}
			}
		});
		
		handler.setWorld2(handler.getCurrentWorld());
		handler.getWorld2().loadEntities();
//World 3 **** *** *** *** *** *** *** ****
		handler.setCurrentWorld(handler.getWorld3());
		handler.getCurrentWorld().setEntityLoader(new EntityLoader() {
			@Override
			public void loadEntities() {
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 32,608, 32, 64,64,616, 2));
				//Random
				for(int i=0; i < Utils.randomNumber(20, 5); i++) { 
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new Stone(handler, Utils.randomNumber(1300, 0), Utils.randomNumber(1300, 0)));
				}
				for(int i=0; i <Utils.randomNumber(10, 5); i++) { 
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new Tree(handler, Utils.randomNumber(1300, 0), Utils.randomNumber(1300, 0)));
				}
				for(int i=0; i < Utils.randomNumber(20, 5); i++) {
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new RockbugCreature(handler, Utils.randomNumber(1300, 0),Utils.randomNumber(1300, 0)));
				}
			}
		});
		
		handler.setWorld3(handler.getCurrentWorld());
		handler.getWorld3().loadEntities();
//Set Initial World **** *** *** *** *** ****
		handler.setCurrentWorld(handler.getTown());
	}
	
	public void tick() {
		world = handler.getCurrentWorld();
		world.tick();
	}
	public void render(Graphics g) {
		world.render(g);
	}

	
	
}
