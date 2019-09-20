package gameone.entities.staticentity;
import java.awt.Color;
import java.awt.Graphics;

import gameone.Handler;
import gameone.gfx.Assets;
import gameone.item.Item;
import gameone.item.items.WoodItem;
import gameone.item.food.*;
import gameone.tiles.Tile;
import gameone.utils.Utils;
  
//the hting is so cool that I thin th
public class Tree extends StaticEntity{
	public int appleSpawn;
	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 4);
		
		bounds.x = 10;
		bounds.y = height - Tile.TILEHEIGHT;
		
		bounds.width = width - 20;
		bounds.height = Tile.TILEHEIGHT;
		tool = 3;
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void onDie() {
		handler.getWorld().getItemManager().getItemsToAdd().add(new WoodItem(handler, 5, x, y));
		if( Utils.randomNumber(7, 7) == 7) {
			handler.getWorld().getItemManager().getItemsToAdd().add(new Apple(handler, 2, x-16, y-16));
		}
		handler.getWorld().getEntityManager().getEntitiesToAdd().add(new Sapling(handler, (int) x + Tile.TILEWIDTH / 2,(int) y + Tile.TILEWIDTH * 3));
		
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
