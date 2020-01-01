package game.entities.staticentity;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.worlds.World;

public class MapChanger extends StaticEntity{
	
	private World destination;
	private int returnX, returnY;
	private Handler handler;
	
	
	public MapChanger(Handler handler, float x, float y, int width, int height, int returnX, int returnY, World destination) {
		super(handler, x, y, width, height, true);
		this.destination = destination;
		this.returnX = returnX;
		this.returnY = returnY;
		health = 1000000000;
		solid = false;
		this.handler = handler;
	}

	@Override
	public void tick() {
		if(handler.getCurrentWorld().getEntityManager().getPlayer().getVehicle() == null) {
			if(handler.getCurrentWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(getCollisionBounds(0f,0f))) {
				handler.getCurrentWorld().getEntityManager().getPlayer().setX(returnX);
				handler.getCurrentWorld().getEntityManager().getPlayer().setY(returnY);
				
				handler.setCurrentWorld(destination);
			}
		}
	}
	

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void onDie() {
		
		
	}

}
