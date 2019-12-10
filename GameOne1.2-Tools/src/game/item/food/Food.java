package game.item.food;

import java.awt.image.BufferedImage;

import game.Handler;
import game.entities.Entity;
import game.gfx.Assets;
import game.item.Item;

public abstract class Food extends Item{
	
	protected int foodValue;
	protected int eatTime;
	
	public Food(Handler handler, BufferedImage texture, String name, int id, int count, int foodValue, float x, float y) {
		super(handler, texture, name, id, count, x, y);
		this.foodValue = foodValue;
		eatTime = 50;
	}
	public void use(Entity e){
		eatTime --;
		if(eatTime <= 0) {
			handler.getWorld().getEntityManager().getPlayer().setHunger(handler.getWorld().getEntityManager().getPlayer().getHunger() + foodValue);
			
			if(handler.getWorld().getEntityManager().getPlayer().getHunger() > 10){
				handler.getWorld().getEntityManager().getPlayer().setHunger(10);
			}
			handler.getWorld().getEntityManager().getPlayer().getInventory().removeItem(this, 1);
			eatTime = 50;
		}
	}
	
	
	
}