package game.tiles;

import game.Handler;
import game.gfx.Assets;

public class SandTile extends Tile{

	public SandTile(Handler handler, int x, int y) {
		super(handler, x, y, Assets.sand, 5);
	}

}
