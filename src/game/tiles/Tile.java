package game.tiles;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Handler;

public class Tile {
	
	//STATIC STUFF
	
	//CLASS
	public static final int TILEWIDTH = 32, TILEHEIGHT = 32;
	
	protected Handler handler;
	protected int x, y;
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(Handler handler, int x, int y, BufferedImage texture, int id) {
		
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.id = id;
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(texture,(int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public static Tile newTile(Handler handler,int x, int y,int id) {
		switch(id){
			case 0:
				return new GrassTile(handler, x, y);
			case 1:
				return new DirtTile(handler, x, y);
			case 2:
				return new BarrierTile(handler, x, y);
			case 3:
				return new StoneTile(handler, x, y);
			case 4:
				return new WaterTile(handler, x, y);
			case 5:
				return new SandTile(handler,x ,y);
			default:
				System.out.println("Unknown Tile Id");
				return new GrassTile(handler, x, y);
		}
	}
	
	public int getId() {
		return id;	
	}
}
