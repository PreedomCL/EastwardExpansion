package gameone.crafting;

import gameone.Handler;
import gameone.item.Item;
import gameone.item.items.StoneItem;
import gameone.item.items.WoodItem;
import gameone.item.tools.SpearTool;
import gameone.utils.Utils;

public class RecipeManager {
	
	private Handler handler;
	
	private Recipe recipes[] = new Recipe[128];
	private String path;
	
	
	
	public RecipeManager(Handler handler, String path) {
		this.handler = handler;
		this.path = path;
		
	}
	
	public void init() {
		Item items[], yield;
		int id, length;
		int start = 0;
		
//		items[0] = new WoodItem(handler, 6, 0, 0);
//		items[1] = new StoneItem(handler, 1, 0, 0);
//		recipes[0] = new Recipe(handler,0 , 2, new SpearTool(handler, 1, 0, 0), items);
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		int j = 0;
		while (j < tokens.length) {
			start = j;
			items = new Item[10];
			System.out.println(start);
			
			id = Utils.parseInt(tokens[0 + start]);
			length = Utils.parseInt(tokens[1 + start]);
			yield = handler.getWorld().getItemManager().getItemList()[Utils.parseInt(tokens[2 + start]) - 1];
			yield.setCount(Utils.parseInt(tokens[3 + start]));
			
			for(int i = 0; i < length; i++) {
				System.out.println(i);
				items[i] = handler.getWorld().getItemManager().getItemList()[Utils.parseInt(tokens[start + i * 2 + 4]) - 1];
				items[i].setCount(Utils.parseInt(tokens[start + i * 2 + 5]));
			}
			recipes[id] = new Recipe(handler, id, length, yield, items);
			j = start + 4 + (length * 2);
		}
		
	}
	
	//G&S
	public Recipe[] getRecipes() {
		return recipes;
	}

	public void setRecipes(Recipe[] recipes) {
		this.recipes = recipes;
	}
	
	
}
