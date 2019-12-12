package game.item;

import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Assets;

public class BridgeBuilder extends Item{

	public BridgeBuilder(Handler handler, int count, float x, float y) {
		super(handler, Assets.unknown, "Bridge Builder", 11, count, x, y);
	}

}
