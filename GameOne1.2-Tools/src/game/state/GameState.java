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
import game.entities.staticentity.MineralRock;
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
		
		handler.setTown(new World(handler, System.getProperty("user.dir") + "/res/world/world1.txt", 1));
		handler.setWorld2(new World(handler, System.getProperty("user.dir") + "/res/world/world2.txt", 2));
		handler.setWorld3(new World(handler, System.getProperty("user.dir") + "/res/world/world3.txt", 3));
		handler.setBeach(new World(handler, System.getProperty("user.dir") + "/res/world/beach.txt", 4));
//Town **** *** *** *** *** *** *** ****
		handler.setCurrentWorld(handler.getTown());
		handler.getCurrentWorld().setEntityLoader(new EntityLoader() {
			@Override
			public void loadEntities() {
				String[] speech;
				Recipe[] trades;
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 992, 1216, 63, 32,992,1150, handler.getWorld2()));
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 480, 32, 63, 32,480, 32, handler.getBeach()));
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new NPC(handler, Assets.player[0],speech = new String[] {"Hello, I am your trusty toolsmith", "I cannot sell you any tools right now, but..", "...I can sell you one of my spare tool stations!"}, trades = new Recipe[] {handler.getGame().getTradingManager().getRecipes()[0], handler.getGame().getTradingManager().getRecipes()[1],  handler.getGame().getTradingManager().getRecipes()[2]}, 750, 580, 32, 64));
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new EnterableBuilding(handler, Assets.blacksmith, 648, 500, 220, 128, null, 705, 628, 42, 16, 708, 600));
				
				//Random
				for(int i=0; i <Utils.randomNumber(15, 9); i++) { 
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new Tree(handler, Utils.randomNumber(1300, 50), Utils.randomNumber(1000, 0)));
				}
				for(int i=0; i < Utils.randomNumber(20, 15); i++) { 
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new Stone(handler, Utils.randomNumber(1300, 0), Utils.randomNumber(1300, 0)));
				}
				for(int i=0; i < Utils.randomNumber(10, 5); i++) { 
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new RockbugCreature(handler, Utils.randomNumber(1300, 0),Utils.randomNumber(1300, 0)));
				}
			}
		});
		
		handler.getCurrentWorld().loadEntities();
		handler.setTown(handler.getCurrentWorld());
		
		
//World 2 **** *** *** *** *** *** *** ****
		handler.setCurrentWorld(handler.getWorld2());
		handler.getCurrentWorld().setEntityLoader(new EntityLoader() {
			@Override
			public void loadEntities() {
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 64,32, 63, 32,64,32, handler.getTown()));
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 1216, 320, 32, 64, 1200, 336, handler.getWorld3()));
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
		
		handler.getCurrentWorld().loadEntities();
		handler.setWorld2(handler.getCurrentWorld());
		
//World 3 **** *** *** *** *** *** *** ****
		handler.setCurrentWorld(handler.getWorld3());
		handler.getCurrentWorld().setEntityLoader(new EntityLoader() {
			@Override
			public void loadEntities() {
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 32,608, 32, 64,64,616, handler.getWorld2()));
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
		
		handler.getCurrentWorld().loadEntities();
		handler.setWorld3(handler.getCurrentWorld());
//Beach
		handler.setCurrentWorld(handler.getBeach());
		handler.getCurrentWorld().setEntityLoader(new EntityLoader() {
			@Override
			public void loadEntities() {
				handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, 544, 640, 64, 32,544,576, handler.getTown()));
				//Random
				for(int i=0; i < Utils.randomNumber(10, 5); i++) { 
					handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new Stone(handler, Utils.randomNumber(1216, 0), Utils.randomNumber(640, 540)));
				}
			}
		});
		
		handler.getCurrentWorld().loadEntities();
		handler.setBeach(handler.getCurrentWorld());
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
