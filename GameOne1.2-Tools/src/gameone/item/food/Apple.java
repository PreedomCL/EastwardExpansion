package gameone.item.food;

import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.gfx.Assets;

public class Apple extends Food{

	public Apple(Handler handler, int count, float x, float y) {
		super(handler, Assets.unknown, "Apple", 5, count, 1, x, y);
	}

}
