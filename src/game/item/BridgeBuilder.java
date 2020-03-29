package game.item;

import java.awt.image.BufferedImage;

import game.Assets;
import game.Handler;

public class BridgeBuilder extends Item{

	public BridgeBuilder(Handler handler, int count, float x, float y) {
		super(handler, Assets.bridgeBuilder, "Bridge Builder", 11, count, x, y);
	}

}
