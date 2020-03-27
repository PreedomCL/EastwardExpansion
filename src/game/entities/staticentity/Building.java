package game.entities.staticentity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Handler;

public abstract class Building extends StaticEntity{
	protected BufferedImage texture;
	
	
	public Building(Handler handler,BufferedImage texture, float x, float y, int width, int height, int[] NST) {
		super(handler, x, y, width, height, NST);
		this.texture = texture;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(texture, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
	}
}
