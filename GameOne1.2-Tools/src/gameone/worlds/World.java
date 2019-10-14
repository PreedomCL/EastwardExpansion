package gameone.worlds;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.sun.glass.events.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;

import gameone.Handler;
import gameone.crafting.Recipe;
import gameone.entities.Entity;
import gameone.entities.EntityManager;
import gameone.entities.creature.NPC;
import gameone.entities.creature.Player;
import gameone.entities.creature.RockbugCreature;
import gameone.entities.staticentity.Sapling;
import gameone.entities.staticentity.Stone;
import gameone.entities.staticentity.Tree;
import gameone.entities.staticentity.craftingstation.DefaultStation;
import gameone.gfx.Assets;
import gameone.gfx.Text;
import gameone.item.Item;
import gameone.item.ItemManager;
import gameone.tiles.Tile;
import gameone.ui.UIManager;
import gameone.utils.Utils;

public class World {
	
	
	
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private boolean debugMode = false;
	//Entities
	private EntityManager entityManager;
	private EntityLoader entityLoader;
	private float playerX, playerY;
	ArrayList<Entity> entitiesToAdd;
	ArrayList<Item> itemsToAdd;
	
	//Items
	private ItemManager itemManager;
	
	
	public World(Handler handler, String path) {
		this.handler = handler;
		handler.getMouseManager().setUIManager(handler.getGame().getUiManager());
		loadWorld(path);
		
		entityManager = new EntityManager(handler, handler.getPlayer());
		entitiesToAdd = new ArrayList<Entity>();
		
		itemManager = new ItemManager(handler);
		itemsToAdd = new ArrayList<Item>();
		

		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
		playerX = entityManager.getPlayer().getX();
		playerY = entityManager.getPlayer().getY();
		
		
	}	
	
	public void tick() {
		
		handler.getGame().getUiManager().tick();
		itemManager.tick();
		
		
		
		entityManager.getPlayer().setX(playerX);
		entityManager.getPlayer().setY(playerY);
		
		entityManager.tick();
		
		playerX = entityManager.getPlayer().getX();
		playerY = entityManager.getPlayer().getY();
		//Adding Entities & Items
		
		entitiesToAdd = handler.getWorld().getEntityManager().getEntitiesToAdd();
		
		Iterator<Entity> it = entitiesToAdd.iterator();
		while(it.hasNext()) {
			Entity e = it.next();
			entityManager.addEntity(e);
			it.remove();
		}
		entitiesToAdd.clear();
		
		itemsToAdd = handler.getWorld().getItemManager().getItemsToAdd();
		
		Iterator<Item> it1 = itemsToAdd.iterator();
		while(it1.hasNext()) {
			Item i = it1.next();
			itemManager.addItem(i);
			it1.remove();
		}
		itemsToAdd.clear();
		
	}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, (handler.getGameCamera().getxOffset()) / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		
		int yStart = (int) Math.max(0, (handler.getGameCamera().getyOffset()) / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
			
		}
		
		//Item
		itemManager.render(g);
		//Entities
		entityManager.render(g);
		
		
		handler.getGame().getUiManager().render(g);
		
		if (!getEntityManager().getPlayer().isActive()) {
			Text.drawString(g, "Game Over", 562, 315, true, Color.BLACK, Assets.font28);
		}
		
		
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	public void loadEntities() {
		entityLoader.loadEntities();
	}
	
	private void loadWorld(String path) {
		
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
		
	}

	
	//Getters Setters
	
	public int getWidth() {
		return width;
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public int getHeight() {
		return height;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityLoader(EntityLoader entityLoader) {
		this.entityLoader = entityLoader;
	}

	public boolean isShowHitBoxes() {
		return debugMode;
	}

	public void setShowHitBoxes(boolean showHitBoxes) {
		this.debugMode = showHitBoxes;
	}
	
}
