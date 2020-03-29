package game.item;

import java.awt.image.BufferedImage;

import game.Assets;
import game.Handler;

public class IronOreItem extends Item{

	public IronOreItem(Handler handler, int count, float x, float y) {
		super(handler, Assets.ironOre, "Iron Ore", 13, count, x, y);
	}

}
