package gameone.tiles;

import java.awt.image.BufferedImage;

import gameone.gfx.Assets;

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
