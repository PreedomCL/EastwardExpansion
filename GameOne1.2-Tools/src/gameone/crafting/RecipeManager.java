package gameone.crafting;

import gameone.Handler;
import gameone.item.Item;
import gameone.item.items.StoneItem;
import gameone.item.items.WoodItem;
import gameone.item.tools.SpearTool;

public class RecipeManager {
	
	private Handler handler;
	private Item items[];
	public Recipe staff;
	
	
	public RecipeManager(Handler handler) {
		this.handler = handler;
		items = new Item[10];
	}
	
	public void init() {
		items[0] = new WoodItem(handler, 6, 0, 0);
		items[1] = new StoneItem(handler, 1, 0, 0);
		staff = new Recipe(handler, items, new SpearTool(handler, 1, 0, 0), 2);
	}
}
