package game.tiles;

import game.gfx.Assets;

public class StoneTile extends Tile {

	public StoneTile( int id) {
		super(Assets.stone, id);
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
