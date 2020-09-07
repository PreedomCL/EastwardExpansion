package game.gfx;

import game.Handler;
import game.entities.Entity;
import game.tiles.Tile;

public class GameCamera {
	private Handler handler;
	private float xOffset, yOffset;
	
	public GameCamera(Handler game, float xOffset, float yOffset) {
		this.handler = game;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void checkBlankSpace() {
		if(xOffset < 32) {
			xOffset = 32;
		}else if(xOffset + 32 > handler.getCurrentWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
			xOffset = handler.getCurrentWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth() - 32;
		}
		
		if(yOffset < 32) {
			yOffset = 32;
		}else if(yOffset + 32 > handler.getCurrentWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
			yOffset = handler.getCurrentWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight() - 32;
		}
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() /2;
		
		checkBlankSpace();
	}
	
	public void move( float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}
	
	public float getxOffset() {
		return xOffset;
	}
	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}
	public float getyOffset() {
		return yOffset;
	}
	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
	
}
