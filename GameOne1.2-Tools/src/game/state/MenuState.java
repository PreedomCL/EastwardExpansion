package game.state;
import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.input.ClickListener;
import game.ui.UIImageButton;
import game.ui.UIManager;

public class MenuState extends State{
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(200, 200, 128, 64, true, Assets.start, new ClickListener() {

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
	}

}
