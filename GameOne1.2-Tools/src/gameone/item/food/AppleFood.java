package gameone.item.food;

import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.gfx.Assets;

public class AppleFood extends Food{

	public AppleFood(Handler handler, int count, float x, float y) {
		super(handler, Assets.apple, "Apple", 5, count, 1, x, y);
	}

}
