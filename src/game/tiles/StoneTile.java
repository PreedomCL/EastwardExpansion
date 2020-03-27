package game.tiles;

import game.Handler;
import game.gfx.Assets;

public class StoneTile extends Tile {

	public StoneTile(Handler handler, int x, int y) {
		super(handler, x, y, Assets.stone, 2);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
