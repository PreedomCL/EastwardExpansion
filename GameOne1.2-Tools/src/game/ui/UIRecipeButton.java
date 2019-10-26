package game.ui;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.crafting.Recipe;
import game.gfx.Assets;
import game.gfx.Text;

public class UIRecipeButton extends UIObject{
	private Recipe recipe;
	
	public UIRecipeButton(Handler handler, float x, float y, Recipe recipe) {
		super(handler, x, y, 33, 33);
		this.recipe = recipe;
	}
	
	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		
		
		if(hovering) 
			g.setColor(new Color(1f, 1f, 1f, .75f));
		else
			g.setColor(new Color(1f,1f,1f,.5f));
		
		g.fillRect((int)x, (int)y, width, height);
		
		if(recipe == null) {
			System.out.println("Recipe is Null");
			return;
		}
			
		if(recipe.checkCraftable(handler.getWorld().getEntityManager().getPlayer().getInventory())) 
			g.setColor(Color.GREEN);
		else 
			g.setColor(Color.RED);
		
		g.drawRect((int)x, (int)y, width, height);
		g.drawImage(recipe.getYield().getTexture(), (int) x + 1, (int) y + 1, null);
		Text.drawString(g, Integer.toString(recipe.getYield().getCount()), (int) x + 16, (int) y + 16 , true, Color.WHITE, Assets.font28);
		
		if(hovering) {
			for(int r = 0;r < recipe.getItems().length; r++ ) {
				if(recipe.getItems()[r] != null) {
					g.setColor(Color.black);
					g.drawRect((int)x + 47, (int) y + 33 * r - 1, width, height);
					g.setColor(Color.lightGray);
					g.fillRect((int) x + 48,(int) y + 33 * r, width - 1, height - 1);
					g.drawImage(recipe.getItems()[r].getTexture(), (int) x + 48,(int) y + 32 * r, null);
					Text.drawString(g, Integer.toString(recipe.getItems()[r].getCount()), (int) x + 64, (int) y + 32 * r + 16, true, Color.YELLOW, Assets.font28);
					
				}
			}
		}
		
	}

	@Override
	public void onClick() {
		if(recipe == null)
			return;
		if(recipe.checkCraftable(handler.getWorld().getEntityManager().getPlayer().getInventory()))
			recipe.craft(handler.getWorld().getEntityManager().getPlayer().getInventory());
	}

}
