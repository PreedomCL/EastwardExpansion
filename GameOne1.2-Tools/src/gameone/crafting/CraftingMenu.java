package gameone.crafting;

import java.awt.Graphics;
//
import gameone.Handler;
import gameone.state.GameState;
import gameone.state.State;
import gameone.ui.UIManager;
import gameone.ui.UIRecipeButton;

public class CraftingMenu {
	
	private Handler handler;
	private Recipe recipes[];
	
	public CraftingMenu(Handler handler, Recipe recipes[]) {
		this.handler = handler;
		this.recipes = recipes;
		System.out.println(recipes);
	}
	
	public void tick() {
		
	}
	
	public void loadMenu() {
		handler.getGame().setUiManager(new UIManager(handler));
		for(int r = 0; r < recipes.length; r++) {
			
			handler.getGame().getUiManager().addObject(new UIRecipeButton(handler, 32, r * 33 + 33, recipes[r]));
			
		}
		handler.getMouseManager().setUIManager(handler.getGame().getUiManager());
	}
	
	public void closeMenu() {
		handler.getGame().setUiManager(new UIManager(handler));
		handler.getMouseManager().setUIManager(handler.getGame().getUiManager());
	}
}
