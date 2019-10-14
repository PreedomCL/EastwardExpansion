package gameone.entities.staticentity;

import java.awt.Graphics;

import gameone.Handler;
import gameone.worlds.World;

public class MapChanger extends StaticEntity{
	
	private World destination;
	private int returnX, returnY;
	
	public MapChanger(Handler handler, float x, float y, int width, int height, int returnX, int returnY, World destination) {
		super(handler, x, y, width, height);
		this.destination = destination;
		this.returnX = returnX;
		this.returnY = returnY;
		health = 1000000000;
		solid = false;
	}

	@Override
	public void tick() {
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(getCollisionBounds(0f,0f))) {
			handler.getWorld().getEntityManager().getPlayer().setX(returnX);
			handler.getWorld().getEntityManager().getPlayer().setY(returnY);
			handler.setWorld(handler.getWorld2());
		}
	}

	@Override
	public void render(Graphics g) {
		
		
	}

	@Override
	public void onDie() {
		
		
	}

}
