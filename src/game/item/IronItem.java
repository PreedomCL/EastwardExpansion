package game.item;

import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Assets;

public class IronItem extends Item{

	public IronItem(Handler handler, int count, float x, float y) {
		super(handler, Assets.unknown, "Iron",10 , count, x, y);
	}

}
