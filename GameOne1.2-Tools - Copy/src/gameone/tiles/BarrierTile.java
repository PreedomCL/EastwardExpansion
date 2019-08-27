package gameone.tiles;
import java.awt.image.BufferedImage;

import gameone.gfx.Assets;

public class BarrierTile extends Tile{

	public BarrierTile(int id) {
		super(Assets.barrier, id);	
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
