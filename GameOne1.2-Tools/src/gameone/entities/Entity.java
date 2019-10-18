package gameone.entities;
import java.awt.Graphics;
import java.awt.Rectangle;

import gameone.Handler;
import gameone.tiles.Tile;

public abstract class Entity {
	
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected int health;
	protected int type, tool;
	protected boolean active = true, solid = true, excused;
	public static final int DEFAULT_HEALTH = 10;
	
	
	
	public Entity(Handler handler, float x, float y, int width, int height, boolean excused) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		health = DEFAULT_HEALTH;
		
		bounds = new Rectangle(0, 0, width, height);
		
		
		//Check for valid spawn location
		this.excused = excused;
		if(!excused) {
			int tx = (int) (x  + bounds.x + bounds.width) / Tile.TILEWIDTH;
			
			if(!checkVaildSpawnTile(tx, (int) (y+bounds.y) / Tile.TILEHEIGHT) || !checkVaildSpawnTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				System.out.println("	" + this + ": Invalid Spawn Location (Tile)");
				active = false;
			}
			int ty = (int) (y + bounds.y) / Tile.TILEHEIGHT;
			
			if(!checkVaildSpawnTile((int) (x + bounds.x) / Tile.TILEHEIGHT, ty) || !checkVaildSpawnTile((int) (x + bounds.x + bounds.width) / Tile.TILEHEIGHT, ty)){
				System.out.println("	" + this + ": Invalid Spawn Location (Tile)");
				active = false;
			}
			
			if(handler.getWorld() != null) {
				for(Entity e: handler.getWorld().getEntityManager().getEntitiesToAdd()) {
					if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(0f,0f))){
						System.out.println("	" + this +": Invalid Spawn Location (Collision With Pre-existing Entity)");
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
	
	public void showHitBoxes(Graphics g) {
		g.drawRect((int) (bounds.x - handler.getGameCamera().getxOffset() + x),(int) (bounds.y - handler.getGameCamera().getyOffset() + y), bounds.width, bounds.height);
	}
	
	public int getInstance() {
		return (Integer) null;
	}
	
	public void hurt(int damage) {
		health -= damage;
		if(health <= 0) {
			active = false;
			onDie();
		}
	}
	//
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e: handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)) && e.solid)
				return true;
		}
		return false;
	}
	
	protected boolean collisionWithTile(int x, int y) {
		if(handler.getWorld() == null)
			return false;
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	protected boolean checkVaildSpawnTile(int x, int y) {
		if(handler.getWorld() == null)
			return true;
		return handler.getWorld().getTile(x, y).isValidSpawn();
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

	
	
	
}
