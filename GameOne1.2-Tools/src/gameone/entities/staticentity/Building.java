package gameone.entities.staticentity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameone.Handler;

public abstract class Building extends StaticEntity{
	protected BufferedImage texture;
	
	public Building(Handler handler,BufferedImage texture, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		this.texture = texture;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(texture, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
	}
}
