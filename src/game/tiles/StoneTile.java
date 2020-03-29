package game.tiles;

import game.Assets;
import game.Handler;

public class StoneTile extends Tile {

	public StoneTile(Handler handler, int x, int y) {
		super(handler, x, y, Assets.stone, 2);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
