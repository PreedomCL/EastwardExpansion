package gameone.item.items;

import gameone.Handler;
import gameone.entities.Entity;
import gameone.gfx.Assets;
import gameone.item.Item;

public class StoneItem extends Item{
	
	public StoneItem(Handler handler,int count, float x, float y) {
		super(handler, Assets.stones,"Stones", 2, count, x, y);
	}

}
