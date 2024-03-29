package game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Assets;

public class WaterTile extends Tile{

	public WaterTile(Handler handler, int x, int y) {
		super(handler, x, y, Assets.water, 4);
	}
	@Override
	public void render(Graphics g) {
		
		g.drawImage(texture,(int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), TILEWIDTH, TILEHEIGHT, null);
		
		if(handler.getCurrentWorld().getTile(x, y - 1).getId() == 0)
			g.drawImage(Assets.grassBorder[0], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), null);
		if(handler.getCurrentWorld().getTile(x, y + 1).getId() == 0)
			g.drawImage(Assets.grassBorder[1], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset() + 28), null);
		if(handler.getCurrentWorld().getTile(x + 1, y).getId() == 0)
			g.drawImage(Assets.grassBorder[2], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset() + 28),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), null);
		if(handler.getCurrentWorld().getTile(x - 1, y).getId() == 0)
			g.drawImage(Assets.grassBorder[3], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), null);
		
		if(handler.getCurrentWorld().getTile(x + 1, y + 1).getId() == 5)
			g.drawImage(Assets.sandBorder[0], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset())+16,(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset())+16, null);
		if(handler.getCurrentWorld().getTile(x, y + 1).getId() == 5)
			g.drawImage(Assets.sandBorder[1], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset())+16, null);
		if(handler.getCurrentWorld().getTile(x - 1, y + 1).getId() == 5)
			g.drawImage(Assets.sandBorder[2], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset())+16, null);
		if(handler.getCurrentWorld().getTile(x + 1, y - 1).getId() == 5)
			g.drawImage(Assets.sandBorder[3], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset())+16,(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), null);
		if(handler.getCurrentWorld().getTile(x, y - 1).getId() == 5)
			g.drawImage(Assets.sandBorder[4], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), null);
		if(handler.getCurrentWorld().getTile(x - 1, y - 1).getId() == 5)
			g.drawImage(Assets.sandBorder[5], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), null);
		if(handler.getCurrentWorld().getTile(x + 1, y).getId() == 5)
			g.drawImage(Assets.sandBorder[6], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset())+16,(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), null);
		if(handler.getCurrentWorld().getTile(x - 1, y).getId() == 5)
			g.drawImage(Assets.sandBorder[7], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), null);
		
			
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
