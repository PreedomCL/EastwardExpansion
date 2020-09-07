package game.tiles;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Assets;

public class DirtTile extends Tile {

	public DirtTile(Handler handler, int x, int y) {
		super(handler, x, y, Assets.dirt, 1);
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(texture,(int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), TILEWIDTH, TILEHEIGHT, null);
		
		if(handler.getCurrentWorld().getTile(x - 1, y).getId() == 0)
			g.drawImage(Assets.grassBorder[0], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), TILEWIDTH, TILEHEIGHT, null);
		if(handler.getCurrentWorld().getTile(x + 1, y).getId() == 0)
			g.drawImage(Assets.grassBorder[1], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), TILEWIDTH, TILEHEIGHT, null);
		if(handler.getCurrentWorld().getTile(x, y - 1).getId() == 0)
			g.drawImage(Assets.grassBorder[2], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), TILEWIDTH, TILEHEIGHT, null);
		if(handler.getCurrentWorld().getTile(x, y + 1).getId() == 0)
			g.drawImage(Assets.grassBorder[3], (int) ((x * TILEWIDTH) - handler.getGameCamera().getxOffset()),(int) ((y * TILEHEIGHT) - handler.getGameCamera().getyOffset()), TILEWIDTH, TILEHEIGHT, null);
	
	}
}
