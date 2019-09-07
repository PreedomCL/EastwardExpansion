package gameone.item.food;

import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.item.Item;

public abstract class Food extends Item{

	public Food(Handler handler, BufferedImage texture, String name, int id, int count, float x, float y) {
		super(handler, texture, name, id, count, x, y);
		
	}

	
}
