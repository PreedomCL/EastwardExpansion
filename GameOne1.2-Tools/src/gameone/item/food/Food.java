package gameone.item.food;

import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.gfx.Assets;
import gameone.item.Item;

public  class Food extends Item{

	public Food(Handler handler, BufferedImage texture, String name, int id, int count, float x, float y) {
		super(handler, Assets.unknown, "Apple", 3, count, x, y);
		
	}

	
}
