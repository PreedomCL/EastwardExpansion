package gameone.entities.staticentity.craftingstation;

import gameone.Handler;
import gameone.crafting.Recipe;
import gameone.entities.staticentity.StaticEntity;

public abstract class CraftingStation extends StaticEntity{
	
	private Recipe recipes[];
	
	public CraftingStation(Handler handler, float x, float y, Recipe recipes[]) {
		super(handler, x, y, 32, 32);
		this.recipes = recipes;
	}
	
	

}
