package game.entities.creature;

import game.Handler;
import game.entities.Entity;
import game.tiles.Tile;

public abstract class Creature extends Entity {
	
	
	public static final float DEFAULT_SPEED = 2.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
							DEFAULT_CREATURE_HEIGHT = 64;
	protected float speed;
	protected float xMove, yMove;
	protected Vehicle vehicle;
	
	public Creature(Handler handler, float x, float y, int width, int height, int[] nonSpawnableTiles) {
		super(handler, x, y, width, height, nonSpawnableTiles);
		
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		type = 1;
	}
	
	public void move() {
		if(vehicle != null) {
			vehicle.setxMove((xMove / speed) * vehicle.getSpeed());
			vehicle.setyMove((yMove / speed) * vehicle.getSpeed());
			vehicle.move();
			x = vehicle.x + vehicle.getxRide();
			y = vehicle.y + vehicle.getyRide();
		}else {
			if(!checkEntityCollisions(getCollisionBounds(xMove,0f)))
				moveX();
			if(!checkEntityCollisions(getCollisionBounds(0f, yMove)))
				moveY();
		}
	}
	
	public void moveX() {
		if(xMove > 0) { //Right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y+bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) || checkEntityWalkable(xMove + bounds.width, 0f)) {
				x += xMove;
			}else {
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
			
		}else if(xMove < 0){ //Left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y+bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) || checkEntityWalkable(0f, yMove)) {
				x += xMove;
			}else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
			
		}
		
	}
	
	public void moveY() {
		if(yMove < 0) { //Up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			
			if(checkEntityWalkable(0f, yMove)) {
				y += yMove;
				System.out.println("EntityWalkable at 0f, yMove");
			}else if(!checkEntityWalkable(0f,0f)) {
				if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEHEIGHT, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEHEIGHT, ty)){
					y += yMove;
				}else{
					y = ty* Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
				}
			}
			
		}else if(yMove > 0) { //Down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if(checkEntityWalkable(0f,yMove + bounds.height)) {
				y += yMove;
			}else if(!checkEntityWalkable(0f,0f)) {
				if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEHEIGHT, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEHEIGHT, ty)){
					y += yMove;
				}else {
					y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
				}
			}
			
		}
		
	}
	
	
	
	
	
	//G&S
	
	public int getHealth() {
		return health;
	}

	public float getxMove() {
		return xMove;
	}


	public void setxMove(float xMove) {
		this.xMove = xMove;
	}


	public float getyMove() {
		return yMove;
	}


	public void setyMove(float yMove) {
		this.yMove = yMove;
	}


	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
}
