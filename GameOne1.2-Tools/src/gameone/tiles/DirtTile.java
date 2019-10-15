package gameone.tiles;
import java.awt.image.BufferedImage;

import gameone.gfx.Assets;

public class DirtTile extends Tile {

	public DirtTile(int id) {
		super(Assets.dirt, id);
	}
	
	@Override
	public boolean isValidSpawn() {
		return false;
	}
}
