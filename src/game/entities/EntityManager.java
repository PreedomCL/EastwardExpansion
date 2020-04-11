package game.entities;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import game.Handler;
import game.entities.creature.Player;
import game.entities.staticentity.craftingstation.CraftingStation;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities, entitiesToAdd, solidEntities;
	
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getRenderY() + a.getHeight() < b.getY() + b.getRenderY() + b.getHeight())
				return -1;
			return 1;
		}
	
	};
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		entitiesToAdd = new ArrayList<Entity>();
		solidEntities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	public void tick() {
		Iterator<Entity> it = entities.iterator();
		solidEntities.clear();
		for(Entity e:entities) {
			if(e.isSolid())
				solidEntities.add(e);
		}
		
		while(it.hasNext()) {
			Entity e = it.next();
			if(!e.isActive())
				it.remove();
			e.tick();
		}
		try {
		entities.sort(renderSorter);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void render(Graphics g) {
		for(Entity e: entities) {
			e.render(g);
			if(handler.getCurrentWorld().isHitBoxes() && handler.getCurrentWorld().isDebugMode()) {
				e.showHitBoxes(g);
			}
		}
		player.postRender(g);
		
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	
	//Setters Getters
	
	
	
	public Handler getHandler() {
		return handler;
	}

	public ArrayList<Entity> getEntitiesToAdd() {
		return entitiesToAdd;
	}

	public void setEntitiesToAdd(ArrayList<Entity> entitiesToAdd) {
		this.entitiesToAdd = entitiesToAdd;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public ArrayList<Entity> getSolidEntities() {
		return solidEntities;
	}

	public void setSolidEntities(ArrayList<Entity> solidEntities) {
		this.solidEntities = solidEntities;
	}

	
}
