package game.entities.staticentity;
import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.tiles.Tile;

public class Sapling extends StaticEntity{
	
	private long startTime, currentTime;
	private int growthTime;
	public Sapling(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, false);
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = Tile.TILEWIDTH;
		bounds.height = Tile.TILEHEIGHT;
		
		startTime = System.currentTimeMillis();
		currentTime = System.currentTimeMillis();
		growthTime = 10000; //120000;
		health = 2;
		solid = false;
		
	}

	@Override
	public void tick() {
		currentTime = System.currentTimeMillis();
		
		if(startTime + growthTime <= currentTime && active)
			grow();
			
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.sapling, (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void onDie() {
		
	}
	
	public void grow() {
			active = false;
			handler.getWorld().getEntityManager().getEntitiesToAdd().add(new Tree(handler, (int) x - Tile.TILEWIDTH / 2,(int) y - Tile.TILEWIDTH * 3));
	}
		

}
