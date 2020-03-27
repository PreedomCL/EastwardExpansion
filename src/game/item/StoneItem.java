package game.item;

import game.Handler;
import game.entities.Entity;
import game.gfx.Assets;

public class StoneItem extends Item{
	
	public StoneItem(Handler handler,int count, float x, float y) {
		super(handler, Assets.stones,"Stones", 2, count, x, y);
	}

}
