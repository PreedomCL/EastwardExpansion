package game.entities.staticentity;

import java.awt.Graphics;

import game.Assets;
import game.Handler;

public class Boulder extends StaticEntity{

	public Boulder(Handler handler, float x, float y) {
		super(handler, x, y, 64, 64, new int[] {1, 2, 3, 4, 5});
		
		bounds.y = 32;
		bounds.height = 32;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.boulder, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void onDie() {
		
	}

}
