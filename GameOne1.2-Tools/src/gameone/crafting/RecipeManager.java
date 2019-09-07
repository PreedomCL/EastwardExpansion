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
		Item items[] = new Item[10], yield;
		int id, length;
		int start = 0;
		
//		items[0] = new WoodItem(handler, 6, 0, 0);
//		items[1] = new StoneItem(handler, 1, 0, 0);
//		recipes[0] = new Recipe(handler,0 , 2, new SpearTool(handler, 1, 0, 0), items);
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		for(int i = 0; i <= tokens.length; i++) {
			id = Utils.parseInt(tokens[0 + start]);
			length = Utils.parseInt(tokens[1 + start]);
			
		}
		//width = Utils.parseInt(tokens[0]);
		
		
	}
	
	//G&S
	public Recipe[] getRecipes() {
		return recipes;
	}

	public void setRecipes(Recipe[] recipes) {
		this.recipes = recipes;
	}
	
	
}
