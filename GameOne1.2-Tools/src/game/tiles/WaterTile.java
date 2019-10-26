package game.tiles;

import java.awt.image.BufferedImage;

import game.gfx.Assets;

public class WaterTile extends Tile{

	public WaterTile(int id) {
		super(Assets.water, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}
	
	@Override
	public boolean isValidSpawn() {
		return false;
	}
}
