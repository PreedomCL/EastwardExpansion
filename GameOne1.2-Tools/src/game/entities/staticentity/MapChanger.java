package game.entities.staticentity;

import java.awt.Color;
import java.awt.Graphics;

import game.Handler;
import game.worlds.World;

public class MapChanger extends StaticEntity{
	
	private int worldIndex;
	private int returnX, returnY;
	private Handler handler;
	
	
	public MapChanger(Handler handler, float x, float y, int width, int height, int returnX, int returnY, int worldIndex) {
		super(handler, x, y, width, height, true);
		this.worldIndex = worldIndex;
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
				
				switch(worldIndex) {
				case 1:
					handler.setCurrentWorld(handler.getTown());
					break;
				case 2:
					handler.setCurrentWorld(handler.getWorld2());
					break;
				case 3:
					handler.setCurrentWorld(handler.getWorld3());
					break;
				}
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
