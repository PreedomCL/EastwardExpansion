package gameone.entities.staticentity.craftingstation;

import java.awt.Graphics;

import gameone.Handler;
import gameone.crafting.Recipe;
import gameone.gfx.Assets;
import gameone.item.placeable.BasicWorkTableItem;

public class BasicWorkTable extends CraftingStation{
	private static Recipe[] recipe;
	public BasicWorkTable(Handler handler, float x, float y) {
		super(handler,Assets.craftingStation, x, y, recipe = new Recipe[] {handler.getGame().getRecipeManager().getRecipes()[0], handler.getGame().getRecipeManager().getRecipes()[1], handler.getGame().getRecipeManager().getRecipes()[2]});
		
	}

	@Override
	public void onDie() {
		handler.getWorld().getItemManager().getItemsToAdd().add(new BasicWorkTableItem(handler, 1, x, y));
	}

}
