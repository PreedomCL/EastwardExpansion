package game.tiles;

import game.Assets;
import game.Handler;

public class SandTile extends Tile{

	public SandTile(Handler handler, int x, int y) {
		super(handler, x, y, Assets.sand, 5);
	}

}
