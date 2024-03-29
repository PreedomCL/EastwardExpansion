package game.entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import game.gfx.Assets;
import game.sfx.SoundPlayer;
import game.tiles.Tile;

public abstract class Entity {
	
	protected Handler handler;
	protected float x, y, renderY, cX, cY;
	protected int width, height;
	protected Rectangle bounds, activeBounds;
	protected int health;
	protected int type, tool;
	protected boolean active = true, solid = true, walkable = false, spawnExcused = false, useActiveBounds = false, portalCooldown = false;
	protected boolean inRange = false;
	protected int[] nonSpawnableTiles;
	public static final int DEFAULT_HEALTH = 10;
	
	
	
	public Entity(Handler handler, float x, float y, int width, int height, int[] nonSpawnableTiles) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		
		
		renderY = 0;
		health = DEFAULT_HEALTH;
		
		bounds = new Rectangle(0, 0, width, height);
		activeBounds = new Rectangle(-16, -16, width + 32, height + 32);
		
		
		//Move to Exact Location
		x = x - bounds.x;
		y = y - bounds.y;
		//Check for valid spawn location
		this.nonSpawnableTiles = nonSpawnableTiles;
		if(this != handler.getPlayer() || !spawnExcused) {
			int tx = (int) (x  + bounds.x + bounds.width + (bounds.width / 2)) / Tile.TILEWIDTH;
			
			if(!checkVaildSpawnTile(tx, (int) (y+bounds.y) / Tile.TILEHEIGHT) || !checkVaildSpawnTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				active = false;
			}
			int ty = (int) (y + bounds.y + (bounds.height / 2)) / Tile.TILEHEIGHT;
			
			if(!checkVaildSpawnTile((int) (x + bounds.x) / Tile.TILEHEIGHT, ty) || !checkVaildSpawnTile((int) (x + bounds.x + bounds.width) / Tile.TILEHEIGHT, ty)){
				active = false;
			}
			
			if(handler.getCurrentWorld() != null && this != handler.getPlayer()) {
				for(Entity e: handler.getCurrentWorld().getEntityManager().getEntitiesToAdd()) {
					if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(0f,0f))){
						active = false;
					}
				}
			}
		}
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void onDie();
	public void use() {}
	
	protected boolean checkActiveBounds() {
		if(handler.getCurrentWorld().getEntityManager().getPlayer().getCollisionBounds(-x, -y).intersects(activeBounds))
			return true;
		else 
			return false;
	}
	public void showHitBoxes(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect((int) (bounds.x - handler.getGameCamera().getxOffset() + x),(int) (bounds.y - handler.getGameCamera().getyOffset() + y), bounds.width, bounds.height);
		if(useActiveBounds) {
			g.setColor(Color.ORANGE);
			g.drawRect((int) (activeBounds.x - handler.getGameCamera().getxOffset() + x),(int) (activeBounds.y - handler.getGameCamera().getyOffset() + y), activeBounds.width, activeBounds.height);
		}
	}
	
	public void hurt(int damage) {
		health -= damage;
		SoundPlayer.playSound(Assets.treeHit);
		if(health <= 0) {
			active = false;
			onDie();
		}
	}
	//
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public boolean checkEntityCollisions(Rectangle collisionBounds) {
		for(Entity e: handler.getCurrentWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(collisionBounds) && e.solid)
				return true;
		}
		return false;
	}
	
	public boolean checkEntityWalkable(float xOffset, float yOffset) {
		for(Entity e: handler.getCurrentWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)) && e.walkable)
				return true;
		}
		return false;
	}
	
	protected boolean collisionWithTile(int x, int y) {
		if(handler.getCurrentWorld() == null)
			return false;
		return handler.getCurrentWorld().getTile(x, y).isSolid();
	}
	
	protected boolean checkVaildSpawnTile(int x, int y) {
		if(handler.getCurrentWorld() == null)
			return true;
		for(int i = 0; i < nonSpawnableTiles.length; i++) {
			if(handler.getCurrentWorld().getTile(x, y).getId() == nonSpawnableTiles[i])
				return false;
			if(handler.getCurrentWorld().getTile(x - 1, y).getId() == nonSpawnableTiles[i])
				return false;
			if(handler.getCurrentWorld().getTile(x + 1, y).getId() == nonSpawnableTiles[i])
				return false;
			if(handler.getCurrentWorld().getTile(x, y - 1).getId() == nonSpawnableTiles[i])
				return false;
			if(handler.getCurrentWorld().getTile(x, y + 1).getId() == nonSpawnableTiles[i])
				return false;
		}
		return true;
	}
	
	
	//GETTERS SETTERS
	
	public int getHealth() {
		return health;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public float getcX() {
		return (float) (x + width/2);
	}		

	public float getcY() {
		return (float) (y + height/2);
	}

	public float getRenderY() {
		return renderY;
	}

	public void setRenderY(float renderY) {
		this.renderY = renderY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public int getTool() {
		return tool;
	}

	public void setTool(int tool) {
		this.tool = tool;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public boolean isPortalCooldown() {
		return portalCooldown;
	}

	public void setPortalCooldown(boolean portalCooldown) {
		this.portalCooldown = portalCooldown;
	}

	
	
	
}
