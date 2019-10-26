package game.crafting;

import game.Handler;
import game.item.Item;
import game.item.StoneItem;
import game.item.WoodItem;
import game.item.tools.SpearTool;
import game.utils.Utils;

public class RecipeManager {
	
	private Handler handler;
	
	private final Recipe recipes[] = new Recipe[128];
	private String path;
	
	
	
	public RecipeManager(Handler handler, String path) {
		this.handler = handler;
		this.path = path;
		
	}
	
	public void init() {
		Item items[][],yield;
		int id, length, count[];
		int start = 0;
		
		
		items = new Item[128][10];
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		
		int j = 0;
		
		while (j < tokens.length) {
			start = j;
			
			id = Utils.parseInt(tokens[0 + start]);
			length = Utils.parseInt(tokens[1 + start]);
			yield = handler.getWorld().getItemManager().getItem(Utils.parseInt(tokens[2 + start]), Utils.parseInt(tokens[3 + start]));
		
			for(int i = 0; i < length; i++) {
				items[id][i] = handler.getWorld().getItemManager().getItem(Utils.parseInt(tokens[start + (i * 2) + 4]), Utils.parseInt(tokens[start + (i * 2) + 5]));
			}
			recipes[id] = new Recipe(handler, id, length, yield, items[id]);
			j = start + 4 + (length * 2);
		}
	}
	
	//G&S
	public Recipe[] getRecipes() {
		return recipes;
	}

//	public void setRecipes(Recipe[] recipes) {
//		this.recipes = recipes;
//	}
	
	
}
