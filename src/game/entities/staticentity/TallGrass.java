package game.entities.staticentity;

import java.awt.Graphics;

import game.Assets;
import game.Handler;

public class TallGrass extends StaticEntity{

	public TallGrass(Handler handler, float x, float y) {
		super(handler, x, y, 32, 48, new int[]{1, 2, 3, 4, 5});
		bounds.y = 16;
		bounds.height = 32;
		solid = false;
		health = 1;
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tallGrass, (int) (x - handler.getGameCamera().getxOffset() - 6), (int) (y - handler.getGameCamera().getyOffset()), 64, 48, null);
	}

	@Override
	public void onDie() {
		
		
	}

}
