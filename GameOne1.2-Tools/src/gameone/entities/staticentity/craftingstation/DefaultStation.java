package gameone.entities.staticentity.craftingstation;

import java.awt.Graphics;

import gameone.Handler;
import gameone.crafting.Recipe;
import gameone.gfx.Assets;

public class DefaultStation extends CraftingStation{
	//
	private static Recipe[] recipe;
	public DefaultStation(Handler handler, float x, float y) {
		super(handler,Assets.craftingStation, x, y, recipe = new Recipe[] {handler.getGame().getRecipeManager().getRecipes()[0], handler.getGame().getRecipeManager().getRecipes()[1]});
		
	}

	@Override
	public void onDie() {
				
	}

}
