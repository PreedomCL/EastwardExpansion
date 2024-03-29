package game.entities.staticentity.craftingstation;

import java.awt.Graphics;

import game.Handler;
import game.crafting.Recipe;
import game.gfx.Assets;
import game.item.placeable.BasicWorkTableItem;

public class BasicWorkTable extends CraftingStation{
	private static Recipe[] recipe;
	private static final int[] NST = {4};
	public BasicWorkTable(Handler handler, float x, float y) {
		super(handler,Assets.basicWorkTable, x, y, recipe = new Recipe[] {handler.getGame().getRecipeManager().getRecipes()[0], handler.getGame().getRecipeManager().getRecipes()[1], handler.getGame().getRecipeManager().getRecipes()[2]}, NST);
		
	}

	@Override
	public void onDie() {
		handler.getCurrentWorld().getItemManager().getItemsToAdd().add(new BasicWorkTableItem(handler, 1, x, y));
	}

}
