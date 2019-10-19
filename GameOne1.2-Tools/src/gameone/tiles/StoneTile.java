package gameone.tiles;

import gameone.gfx.Assets;

public class StoneTile extends Tile {

	public StoneTile( int id) {
		super(Assets.rock, id);
	}
	@Override
	public boolean isSolid() {
		return true;
	}

}
