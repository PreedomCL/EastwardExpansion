package game.tiles;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;

public class GrassTile extends Tile {

	public GrassTile(Handler handler, int x, int y) {
		super(handler, x, y, Assets.grass, 0);
	}
	
	
}
