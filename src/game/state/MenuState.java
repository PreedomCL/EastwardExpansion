package game.state;
import java.awt.Color;
import java.awt.Graphics;

import game.Assets;
import game.Handler;
import game.gfx.Text;
import game.input.ClickListener;
import game.ui.UIImageButton;
import game.ui.UIManager;

public class MenuState extends State{
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(436, 200, 128, 64, true, Assets.start, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(new GameState(handler));
			}
		}));
	}
	
	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		Text.drawString(g, "Instructions:", 500, 300, true, Color.BLACK, Assets.font20);
		Text.drawString(g, "Use the WASD key to move", 500, 325, true, Color.BLACK, Assets.font20);
		Text.drawString(g, "Rigth-Click to attack/destroy entities", 500, 350, true, Color.BLACK, Assets.font20);
		Text.drawString(g, "Left-Click to place items, ie. the Basic Work Station", 500, 375, true, Color.BLACK, Assets.font20);
		Text.drawString(g, "When over an item use the R key to pick it up", 500, 400, true, Color.BLACK, Assets.font20);
		Text.drawString(g, "Use the number keys to switch between inventory slots", 500, 425, true, Color.BLACK, Assets.font20);
		Text.drawString(g, "Your goal is to buy a Basic Work Station, make all the tools, and finally build and cross the bridge", 500, 475, true, Color.BLACK, Assets.font20);
		Text.drawString(g, "Good Luck!", 500, 500, true, Color.BLACK, Assets.font20);
	}

}
