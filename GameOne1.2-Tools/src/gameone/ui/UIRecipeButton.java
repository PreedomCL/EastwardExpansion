package gameone.ui;

import java.awt.Color;
import java.awt.Graphics;

import gameone.Handler;
import gameone.crafting.Recipe;

public class UIRecipeButton extends UIObject{
	private Recipe recipe;
	
	public UIRecipeButton(Handler handler, float x, float y, Recipe recipe) {
		super(handler, x, y, 33, 33);
		this.recipe = recipe;
		
	}
//
	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		if(hovering)
			g.setColor(Color.gray);
		else
			g.setColor(Color.WHITE);
		
		g.fillRect((int) x,(int) y, width, height);
		
		if(recipe == null)
			return;
		if(recipe.checkCraftable(handler.getWorld().getEntityManager().getPlayer().getInventory())) 
			g.setColor(Color.GREEN);
		else 
			g.setColor(Color.RED);
		
		g.drawRect((int)x, (int)y, width, height);
		g.drawImage(recipe.getYield().getTexture(), (int) x + 1, (int) y + 1, null);
	}

	@Override
	public void onClick() {
		if(recipe == null)
			return;
		if(recipe.checkCraftable(handler.getWorld().getEntityManager().getPlayer().getInventory()))
			recipe.craft(handler.getWorld().getEntityManager().getPlayer().getInventory());
	}

}
