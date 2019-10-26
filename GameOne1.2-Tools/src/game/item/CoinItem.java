package game.item;

import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Assets;

public class CoinItem extends Item{

	public CoinItem(Handler handler, int count, float x, float y) {
		super(handler, Assets.coin, "Coin", 9, count, x, y);
		
	}

}
