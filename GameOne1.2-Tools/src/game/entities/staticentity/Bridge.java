package game.entities.staticentity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Color;

import game.Handler;



public class Bridge extends StaticEntity{
	
	private Boolean inRange = false, active = false;
	private Rectangle activeBounds;
	private Handler handler;
	
	public Bridge(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height, true);
		health = 1000;
		this.handler = handler;
		
		activeBounds = new Rectangle((int)x - 16, (int)y - 16, width + 32, height + 32);
	}

	@Override
	public void tick() {
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(activeBounds))
			inRange = true;
		else 
			inRange = false;
		
		if(inRange && handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			active = ! active;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height);
		
		
	}

	@Override
	public void onDie() {
		
		
	}

}
