package game.entities.staticentity;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.inventory.Inventory;
import game.item.IronOreItem;
import game.item.StoneItem;
import game.tiles.Tile;
import game.utils.Utils;

public class MineralRock extends StaticEntity{
	private static final int[] NST = {1,2,4,5};
	public MineralRock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, NST);
		tool = 7;
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.mineralRock,(int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), null);
	}

	@Override
	public void onDie() {
		int id = handler.getCurrentWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()[handler.getCurrentWorld().getEntityManager().getPlayer().getInventory().getSelectedItem()].getId();
		handler.getCurrentWorld().getItemManager().getItemsToAdd().add(new StoneItem(handler, Utils.randomNumber(3, 1), x - 10, y));
		if(id == 7 && Utils.randomNumber(4, 1) == 2) {
			handler.getCurrentWorld().getItemManager().getItemsToAdd().add(new IronOreItem(handler, Utils.randomNumber(3, 1), x + 10, y));
		}
	}

}
