package game.item;

import game.Assets;
import game.Handler;
import game.entities.Entity;

public class StoneItem extends Item{
	
	public StoneItem(Handler handler,int count, float x, float y) {
		super(handler, Assets.stones,"Stones", 2, count, x, y);
	}

}
