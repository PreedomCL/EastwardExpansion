package game.worlds;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.sun.glass.events.KeyEvent;

import game.Handler;
import game.crafting.Recipe;
import game.entities.Entity;
import game.entities.EntityManager;
import game.entities.creature.NPC;
import game.entities.creature.Player;
import game.entities.creature.RockbugCreature;
import game.entities.staticentity.Sapling;
import game.entities.staticentity.Stone;
import game.entities.staticentity.Tree;
import game.entities.staticentity.craftingstation.BasicWorkTable;
import game.gfx.Assets;
import game.gfx.Text;
import game.gfx.lighting.LightingManager;
import game.item.Item;
import game.item.ItemManager;
import game.tiles.Tile;
import game.ui.UIManager;
import game.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;

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
	//Lighting
	private LightingManager lightManager;
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
		
		lightManager = new LightingManager();
		
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
		
		
		
		debugMode = handler.getGame().isDebugMode();
		//Adding Entities & Items
		
		entitiesToAdd = entityManager.getEntitiesToAdd();
		
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
		//Lighting
		
		handler.getGame().getUiManager().render(g);
		
		if (!getEntityManager().getPlayer().isActive()) {
			Text.drawString(g, "Game Over", 562, 315, true, Color.BLACK, Assets.font28);
		}
		
		if(debugMode) {
			Text.drawString(g,Integer.toString((int) (handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset())) + "," + Integer.toString((int) (handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset())), handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), false, Color.BLACK, Assets.font20);
			Text.drawString(g,"FPS:" + Integer.toString(handler.getGame().getFps()), 0, 16, false, Color.BLACK, Assets.font20);
			
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
		System.out.println(this + " 's entites loaded:" + entityManager.getEntitiesToAdd());
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

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean showHitBoxes) {
		this.debugMode = showHitBoxes;
	}
	
}
