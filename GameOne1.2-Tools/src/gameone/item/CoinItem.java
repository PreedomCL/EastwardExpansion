package gameone.item;

import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.gfx.Assets;

public class CoinItem extends Item{

	public CoinItem(Handler handler, int count, float x, float y) {
		super(handler, Assets.unknown, "Coin", 9, count, x, y);
		
	}

}
