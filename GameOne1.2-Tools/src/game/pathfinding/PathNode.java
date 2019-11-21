package game.pathfinding;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PathNode {
	private int x, y, h, g;
	private float  cX, cY;
	private Rectangle bounds;
	private PathNode parent;
	
	public boolean walkable;
	public PathNode[] neighbors = new PathNode[7];
	
	public PathNode(PathFinder pathFinder,int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
		bounds.width = 16;
		bounds.height = 16;
		cX = (float) bounds.getCenterX();
		cY = (float) bounds.getCenterY();
		
		neighbors[0] = pathFinder.getNode(x - 16, y - 16);
		neighbors[1] = pathFinder.getNode(x, y - 16);
		neighbors[2] = pathFinder.getNode(x + 16, y - 16);
		neighbors[3] = pathFinder.getNode(x - 16, y);
		neighbors[4] = pathFinder.getNode(x + 16, y);
		neighbors[5] = pathFinder.getNode(x - 16, y + 16);
		neighbors[6] = pathFinder.getNode(x, y + 16);
		neighbors[7] = pathFinder.getNode(x + 16, y + 16);
	}
	
	public void tick() {
		parent = null;
	}
	
	public void render(Graphics g) {
		
	}
	
	
	
//G&S
	public int getF() {
		return h + g;
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
	
	
}