package gameone.item;

import gameone.Handler;
import gameone.entities.Entity;
import gameone.gfx.Assets;

public class StoneItem extends Item{
	
	public StoneItem(Handler handler,int count, float x, float y) {
		super(handler, Assets.stones,"Stones", 2, count, x, y);
	}

}
