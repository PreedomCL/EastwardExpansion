package game.entities.staticentity;
import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.item.Item;
import game.item.StoneItem;
import game.tiles.Tile;

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
		handler.getWorld().getItemManager().getItemsToAdd().add(new StoneItem(handler, 3, x, y));
	}

}
