package gameone.item.placeable;

import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.entities.Entity;
import gameone.item.Item;

public abstract class PlaceableItem extends Item{

	public PlaceableItem(Handler handler, BufferedImage texture, String name, int id, int count, float x, float y) {
		super(handler, texture, name, id, count, x, y);
		
	}
	
	@Override
	public void use(Entity e) {
		if(e == null) {
			place();
			return;
		}
		e.use();
	}
	
	protected abstract void place();

}
