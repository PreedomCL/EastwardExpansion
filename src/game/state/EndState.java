package game.state;

import java.awt.Color;
import java.awt.Graphics;

import game.Assets;
import game.Handler;
import game.gfx.Text;

public class EndState extends State{

	public EndState(Handler handler) {
		super(handler);
		
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		Text.drawString(g, "Congrats!!! You Beat Our Game.", 560, 200, true, Color.BLUE, Assets.font28);
	}

}
