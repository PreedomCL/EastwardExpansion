package game.pathfinding;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.lang.Math.*;

import game.Handler;

public class PathFinder {
	
	private Handler handler;
	private int buffer;
	private ArrayList<PathNode> open, closed;
	private PathNode[][] nodes;
	ArrayList<PathNode> path;
	
	public PathFinder(Handler handler, int width, int height) {
		this.handler = handler;
		
		nodes = new PathNode[width * 2][height * 2];
		for(int x = 0; x < nodes.length; x++) {
			for(int y = 0; y < nodes[x].length; y++) {
				nodes[x][y] = new PathNode(handler, this, x * 16, y * 16);
			}
		}
	}
	
	public ArrayList<PathNode> findPath(PathNode start, PathNode end, int buffer) {
		int timeOut = 0;
		open = new ArrayList<PathNode>();
		closed = new ArrayList<PathNode>();
		path = new ArrayList<PathNode>();
		this.buffer = buffer;
		
		tickNodes();
		start.tick(buffer);
		end.tick(buffer);
		open.add(start);
		
		while(timeOut < 200) {
			timeOut++;
			PathNode current = getCurrentNode();
			open.remove(current);
			closed.add(current);
			try {
				for(PathNode neighbor : current.getNeighbors()) {
					if(neighbor == null)
						continue;
					if(!neighbor.walkable || closed.contains(neighbor))
						continue;
					
					if(!open.contains(neighbor) || current.getG() + calculateG(neighbor.getX(), current.getX(), neighbor.getY(), current.getY()) < neighbor.getG()) {
						neighbor.setParent(current);
						
					//Calculate H cost
						int xDist = Math.abs(neighbor.getX() - end.getX());
						int yDist = Math.abs(neighbor.getY() - end.getY());
						if(xDist > yDist) {
							neighbor.setH(((xDist - yDist) * 10) + (yDist * 14));
						}else {
							neighbor.setH(((yDist - xDist) * 10) + (xDist * 14));
						}
						
					//Calculate G cost
						neighbor.setG(neighbor.getParent().getG() + calculateG(neighbor.getX(), neighbor.getParent().getX(), neighbor.getY(), neighbor.getParent().getY()));
					//Add Neighbor to open if not already
						if(!open.contains(neighbor))
							open.add(neighbor);
					}
				}
			}catch(Exception e) {
				path.add(start);
				return path;
			}
			
			if(current == end) {
				PathNode node = current;
				while(node.getParent() != null) {
					path.add(node);
					node = node.getParent();
				}
				System.out.println();
				return path;
			}
		}
		
		path.add(start);
		return path;
	}
	
	public void tickNodes() {
		for(PathNode[] ns : nodes) {
			for(PathNode node : ns) {
				node.tick(buffer);
			}
		}
	}
	
	public void render(Graphics g) {
		for(PathNode[] nn: nodes) {
			for(PathNode n: nn) {
				g.setColor(Color.BLUE);
				g.drawRect(n.getX() - (int)handler.getGameCamera().getxOffset(), n.getY()- (int) handler.getGameCamera().getyOffset(), n.getBounds().width, n.getBounds().height);
				
				if(n.walkable)
					g.setColor(Color.ORANGE);
				else
					g.setColor(Color.RED);
				g.drawOval((int) (n.getcX() - handler.getGameCamera().getxOffset() - 1),(int) (n.getcY() - handler.getGameCamera().getyOffset() - 1), 3, 3);
			
			}
		}
		
		try {
			for(PathNode o: open) {
				g.setColor(Color.BLUE);
				g.drawOval((int) (o.getcX() - handler.getGameCamera().getxOffset() - 1),(int) (o.getcY() - handler.getGameCamera().getyOffset() - 1), 3, 3);
			}
			for(PathNode c: closed) {
				g.setColor(Color.BLACK);
				g.drawOval((int) (c.getcX() - handler.getGameCamera().getxOffset() - 1),(int) (c.getcY() - handler.getGameCamera().getyOffset() - 1), 3, 3);
			}
			for(PathNode n: path) {
				if(n.getParent() != null) {
					g.setColor(Color.RED);
					g.drawLine((int) (n.getcX() - handler.getGameCamera().getxOffset()), (int) (n.getcY() - handler.getGameCamera().getyOffset()), (int) (n.getParent().getcX()- handler.getGameCamera().getxOffset()), (int) (n.getParent().getcY()- handler.getGameCamera().getyOffset()));
				}
			}
		}catch(Exception e) {
			
		}
		
	}
	
	public PathNode getNode(int x, int y) {
		if((int) (x/16) < 0 || (int) (y/16) < 0 || (int) (x/16) >= nodes.length || (int) (y/16) >= nodes[0].length) {
			return getNode(16, 16);
		}
		//System.out.println("Found node at " + x + "," + y + ":" + nodes[(int) (x / 16)][(int) (y / 16)] + "|@ PathFinder.getNode");
		return nodes[(int) (x / 16)][(int) (y / 16)];
	}
	
	public int calculateG(int x1, int x2, int y1, int y2) {
		if(Math.abs(Math.hypot(x1 - x2, y1 - y2)) > 16) {
			return 14;
		}else {
			return 10;
		}
	}
	
	public PathNode getCurrentNode() {
		PathNode bestNode = null;
		
		for(PathNode node : open) {
			if(bestNode == null) {
				bestNode = node;
				continue;
			}
			if(node.getF() < bestNode.getF())
				bestNode = node;
		}
		return bestNode;
	}
}
