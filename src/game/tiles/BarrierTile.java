package game.tiles;
import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Assets;

public class BarrierTile extends Tile{

	public BarrierTile(Handler handler, int x, int y) {
		super(handler, x, y, Assets.barrier, 3);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
