package game.pathfinding;

import java.util.ArrayList;

import game.Handler;

public class PathFinder {
	
	
	private Handler handler;
	private ArrayList<PathNode> open, closed;
	private PathNode[][] nodes;
	
	
	public PathFinder(Handler handler) {
		this.handler = handler;
		
		nodes = new PathNode[handler.getWorld().getWidth()][handler.getWorld().getHeight()];
		for(int x = 0; x < nodes.length; x++) {
			for(int y = 0; y < nodes[x].length; y++) {
				nodes[x][y] = new PathNode(this, x * 16, y *16);
			}
		}
	}
	
	public PathNode findPath(PathNode start, PathNode end) {
		for(int x = 0; x <nodes.length; x++ ) {
			for(PathNode node : nodes[x]) {
				node.tick();
			}
		}
		start.tick();
		end.tick();
		open.add(start);
		while(1 > 0) {
			PathNode current = getCurrentNode();
			open.remove(current);
			closed.add(current);
			
			if(current == end) {
				PathNode node = current;
				while(1 > 0) {
					if(node.getParent().getParent() == null)
						return node;
					node = node.getParent();
				}
			}
				
			for(PathNode neighbor : current.getNeighbors()) {
				if(!neighbor.walkable || closed.contains(neighbor))
					continue;
				if(!open.contains(neighbor)) {
					neighbor.setParent(current);
					open.add(neighbor);
				}
			}
		}
	}
	
	public PathNode getNode(int x, int y) {
		return nodes[(int) (x / 16)][(int) (y / 16)];
	}
	
	public PathNode getCurrentNode() {
		PathNode bestNode = null;
		
		for(PathNode node : open) {
			if(bestNode == null) {
				bestNode = node;
				continue;
			}
			if(node.getF() > bestNode.getF())
				bestNode = node;
		}
		return bestNode;
	}
}
