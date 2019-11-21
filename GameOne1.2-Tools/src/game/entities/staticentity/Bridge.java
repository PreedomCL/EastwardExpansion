package game.entities.staticentity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import game.Handler;



public class Bridge extends StaticEntity{
	
	
	
	public Bridge(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height, true);
		health = 1000;
		
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void onDie() {
		
		
	}

}
