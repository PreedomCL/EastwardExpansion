package game.tiles;

import game.Assets;
import game.Handler;

public class GrassTile extends Tile {

	public GrassTile(Handler handler, int x, int y) {
		super(handler, x, y, Assets.grass, 0);
	}
	
}
