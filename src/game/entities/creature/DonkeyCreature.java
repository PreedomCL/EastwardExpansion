package game.entities.creature;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Handler;
import game.gfx.Assets;

public class DonkeyCreature extends Vehicle{
	
	private Rectangle activeBounds;
	private static final int[] NST = {0};
	
	public DonkeyCreature(Handler handler, float x, float y) {
		super(handler, x, y, 80, 64, 0, -20,NST);
		speed = 6;
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.donkey,(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), null);
		Message(g);
	}

	@Override
	public void onDie() {
		
		
	}

}
