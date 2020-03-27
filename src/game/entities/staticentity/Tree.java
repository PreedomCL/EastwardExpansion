package game.entities.staticentity;
import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.item.Item;
import game.item.WoodItem;
import game.item.food.*;
import game.tiles.Tile;
import game.utils.Utils;
  
//the hting is so cool that I thin th
public class Tree extends StaticEntity{
	public int appleSpawn;
	private static final int[] NST = {1,2,4};
	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 4, NST);
		
		bounds.x = 19;
		bounds.y = height - Tile.TILEHEIGHT;
		
		bounds.width = width - 31;
		bounds.height = Tile.TILEHEIGHT - 7;
		tool = 6;
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void onDie() {
		handler.getCurrentWorld().getItemManager().getItemsToAdd().add(new WoodItem(handler, Utils.randomNumber(3, 1), x + 32, y + 128));
		if(Utils.randomNumber(4,0) == 1) {
			handler.getCurrentWorld().getItemManager().getItemsToAdd().add(new AppleFood(handler, 2, x-16, y + 128));
		}
		handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new Sapling(handler, (int) x + Tile.TILEWIDTH / 2,(int) y + Tile.TILEWIDTH * 3));
		
	}
	
	@Override
	public void render(Graphics g) {
		
		if(health == 10) {
			g.drawImage(Assets.tree[0], (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
		}else {
			
			try{
				g.drawImage(Assets.tree[4 -((int) (health / 3))], (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
			}catch(Exception e){
				g.drawImage(Assets.tree[3 -(Math.abs((int) (health / 3)))], (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
				
			}
		}
	}
}
