package gameone.entities.staticentity;

import java.awt.Graphics;

import gameone.Handler;
import gameone.worlds.World;

public class MapChanger extends StaticEntity{
	
	private int index;
	private int returnX, returnY;
	private Handler handler;
	public MapChanger(Handler handler, float x, float y, int width, int height, int returnX, int returnY, int index) {
		super(handler, x, y, width, height);
		this.index = index;
		this.returnX = returnX;
		this.returnY = returnY;
		health = 1000000000;
		solid = false;
		this.handler = handler;
	}

	@Override
	public void tick() {
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(getCollisionBounds(0f,0f))) {
			handler.getWorld().getEntityManager().getPlayer().setX(returnX);
			handler.getWorld().getEntityManager().getPlayer().setY(returnY);
			
			switch(index) {
			case 1:
				handler.setWorld(handler.getTown());
				break;
			case 2:
				handler.setWorld(handler.getWorld2());
				break;
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
