package game.entities.staticentity;

import java.awt.Graphics;

import game.Assets;
import game.Handler;

public class TallGrass extends StaticEntity{

	public TallGrass(Handler handler, float x, float y) {
		super(handler, x, y, 32, 64, new int[]{1, 2, 3, 4, 5});
		solid = false;
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tallGrass, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void onDie() {
		
		
	}

}
