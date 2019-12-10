package game.entities.staticentity;
import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.item.*;
import game.tiles.Tile;
import game.utils.Utils;

public class Stone extends StaticEntity {

	public Stone(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, false);
		
		bounds.x = 0;
		bounds.y = 0;
		
		bounds.width = 32;
		bounds.height = 32;
		tool = 7;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock, (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void onDie() {
		handler.getWorld().getItemManager().getItemsToAdd().add(new StoneItem(handler, 3, x - 10, y));
		if(handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()[handler.getWorld().getEntityManager().getPlayer().getInventory().getSelectedItem()] == null)
			return;
		if(handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()[handler.getWorld().getEntityManager().getPlayer().getInventory().getSelectedItem()].getId() == 7) {
			if(Utils.randomNumber(5, 1) == 1) {
				handler.getWorld().getItemManager().getItemsToAdd().add(new IronItem(handler, 3, x + 10, y));
			}
		}
	}

}
