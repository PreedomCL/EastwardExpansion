package game.entities.staticentity;
import java.awt.Graphics;

import game.Assets;
import game.Handler;
import game.inventory.Inventory;
import game.item.*;
import game.tiles.Tile;
import game.utils.Utils;

public class Stone extends StaticEntity {
	private static final int[] NST = {1,2,4};
	public Stone(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH , Tile.TILEHEIGHT , NST);
		
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
		g.drawImage(Assets.stones, (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void onDie() {
		handler.getCurrentWorld().getItemManager().getItemsToAdd().add(new StoneItem(handler, Utils.randomNumber(3, 1), x - 10, y));		
	}

}
