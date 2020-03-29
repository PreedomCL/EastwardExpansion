package game.pathfinding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.entities.EntityManager;
import game.Handler;
import game.entities.Entity;

public class PathNode {
	private Handler handler;
	private int x, y, h, g, buffer, cost;
	private float  cX, cY;
	private Rectangle bounds = new Rectangle();
	private PathNode parent;
	private PathFinder pathFinder;
	
	public boolean walkable;
	public PathNode[] neighbors = new PathNode[8];
	
	public PathNode(Handler handler, PathFinder pathFinder,int x, int y) {
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.pathFinder = pathFinder;
		bounds.x = x;
		bounds.y = y;
		bounds.width = 16;
		bounds.height = 16;
		cX = (float) bounds.getCenterX();
		cY = (float) bounds.getCenterY();
		
		cost = 1;
	}
	
	public void tick(int bf) {
		parent = null;
		walkable = true;
		buffer = 0;
		
		neighbors[0] = pathFinder.getNode(x - 16, y - 16);
		neighbors[1] = pathFinder.getNode(x, y - 16);
		neighbors[2] = pathFinder.getNode(x + 16, y - 16);//+-
		neighbors[3] = pathFinder.getNode(x - 16, y);
		neighbors[4] = pathFinder.getNode(x + 16, y);//+_
		neighbors[5] = pathFinder.getNode(x - 16, y + 16);
		neighbors[6] = pathFinder.getNode(x, y + 16);//_+
		neighbors[7] = pathFinder.getNode(x + 16, y + 16);//++
		
		checkWalkable(bf);
	}
	
	public void checkWalkable(int bf) {
		for(Entity e: handler.getCurrentWorld().getEntityManager().getSolidEntities()) {
			if(e.getCollisionBounds(0f, 0f).intersects(bounds)) {
				buffer = bf;
				walkable = false;
			}
		}
		
		if(handler.getCurrentWorld().getTile(x/32, y/32).isSolid()) {
			buffer = bf;
			walkable = false;
		}
		
		for(PathNode n: neighbors) {
			if(n == null)
				continue;
			if(n.getBuffer() > 0 && n.getBuffer() > buffer) {
				buffer = n.getBuffer() - 1;
			}
		}
		
		if(buffer > 0) {
			cost = 2;
			for(PathNode n: neighbors) {
				if(n == null || n.getBuffer() >= buffer)
					continue;
				n.tick(bf);
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x - (int)handler.getGameCamera().getxOffset(), y- (int) handler.getGameCamera().getyOffset(), bounds.width, bounds.height);
		
		if(walkable)
			g.setColor(Color.ORANGE);
		else
			g.setColor(Color.RED);
		g.drawOval((int) (cX - handler.getGameCamera().getxOffset() - 1),(int) (cY - handler.getGameCamera().getyOffset() - 1), 3, 3);
	
	}
	
	
	
//G&S
	public int getF() {
		return (h + g) * cost;
	}
	
	public void setH(int h) {
		this.h = h;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public PathNode[] getNeighbors() {
		return neighbors;
	}

	public PathNode getParent() {
		return parent;
	}

	public void setParent(PathNode parent) {
		this.parent = parent;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public float getcX() {
		return cX;
	}

	public void setcX(float cX) {
		this.cX = cX;
	}

	public float getcY() {
		return cY;
	}

	public void setcY(float cY) {
		this.cY = cY;
	}

	public int getBuffer() {
		return buffer;
	}

	public void setBuffer(int buffer) {
		this.buffer = buffer;
	}
	
	
}