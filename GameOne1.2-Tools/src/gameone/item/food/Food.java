package gameone.item.food;

import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.entities.Entity;
import gameone.gfx.Assets;
import gameone.item.Item;

public abstract class Food extends Item{
	
	protected int foodValue;
	
	public Food(Handler handler, BufferedImage texture, String name, int id, int count, int foodValue, float x, float y) {
		super(handler, Assets.unknown, name, id, count, x, y);
		this.foodValue = foodValue;
		
	}
	public void use(Entity e){
		System.out.println("Heyyo");
		handler.getWorld().getEntityManager().getPlayer().setHunger(handler.getWorld().getEntityManager().getPlayer().getHunger() + foodValue);
		
		if(handler.getWorld().getEntityManager().getPlayer().getHunger() > 10){
			handler.getWorld().getEntityManager().getPlayer().setHunger(10);
		}
		handler.getWorld().getEntityManager().getPlayer().getInventory().removeItem(this, 1);
	}
	
	
	
}
