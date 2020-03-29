package game.item.placeable;

import java.awt.image.BufferedImage;

import game.Assets;
import game.Handler;
import game.entities.staticentity.craftingstation.BasicWorkTable;

public class BasicWorkTableItem extends PlaceableItem{

	public BasicWorkTableItem(Handler handler, int count, float x, float y) {
		super(handler, Assets.craftingStation, "Basic Work Table", 8, count, x, y);
		
	}

	@Override
	protected void place() {
		System.out.println("Place");
		int _x = (int) (handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset());
		int _y = (int) (handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset());
		handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new BasicWorkTable(handler, _x, _y));
		
		handler.getCurrentWorld().getEntityManager().getPlayer().getInventory().removeItem(this, 1);
	}

}
