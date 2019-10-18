package gameone.item.placeable;

import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.entities.staticentity.craftingstation.BasicWorkTable;
import gameone.gfx.Assets;

public class BasicWorkTableItem extends PlaceableItem{

	public BasicWorkTableItem(Handler handler, int count, float x, float y) {
		super(handler, Assets.craftingStation, "Basic Work Table", 8, count, x, y);
		
	}

	@Override
	protected void place() {
		System.out.println("Place");
		int _x = (int) (handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset());
		int _y = (int) (handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset());
		handler.getWorld().getEntityManager().getEntitiesToAdd().add(new BasicWorkTable(handler, _x, _y));
		
		handler.getWorld().getEntityManager().getPlayer().getInventory().removeItem(this, 1);
	}

}
