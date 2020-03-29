package game.item;

import java.awt.image.BufferedImage;

import game.Assets;
import game.Handler;

public class IronItem extends Item{

	public IronItem(Handler handler, int count, float x, float y) {
		super(handler, Assets.unknown, "Iron",10 , count, x, y);
	}

}
