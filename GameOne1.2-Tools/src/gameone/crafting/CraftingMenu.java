package gameone.crafting;

import java.awt.Graphics;

import gameone.Handler;
import gameone.state.GameState;
import gameone.state.State;
import gameone.ui.UIManager;
import gameone.ui.UIRecipeButton;

public class CraftingMenu {
	
	private Handler handler;
	private UIManager uiManager;
	private Recipe recipes[];
	
	public CraftingMenu(Handler handler, Recipe recipes[]) {
		this.handler = handler;
		this.recipes = recipes;
		uiManager = handler.getGame().getUiManager();
		this.recipes = recipes;
	}
	
	public void tick() {
		
	}
	
	public void loadMenu() {
		for(int r = 0; r < recipes.length; r++) {
			uiManager.addObject(new UIRecipeButton(handler, 32, r * 33 + 33, recipes[0]));
		}
	}
	
	public void closeMenu() {
		handler.getGame().setUiManager(new UIManager(handler));
	}
}
