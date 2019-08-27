package gameone.item.tools;

import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.entities.Entity;
import gameone.item.Item;

public abstract class Tool extends Item {
	protected int attackDamage, destroyDamage;
	protected int id;
	public Tool(Handler handler, BufferedImage texture, String name, int id, int count,int attackDamage,int destroyDamage, float x, float y) {
		super(handler, texture, name, id, count, x, y);
		this.attackDamage = attackDamage;
		this.destroyDamage = destroyDamage;
		this.id = id;
	}
	
	@Override
	public void attack(Entity e) {
		e.hurt(attackDamage);
	}
	
	@Override
	public void destroy(Entity e) {
		 
		if(e.getTool() == id)
			e.hurt(destroyDamage);
		else
			e.hurt(1);
	}
}
