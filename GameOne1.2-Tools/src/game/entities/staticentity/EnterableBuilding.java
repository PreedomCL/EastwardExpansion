package game.entities.staticentity;

import java.awt.image.BufferedImage;

import game.Handler;
import game.worlds.World;

public class EnterableBuilding extends Building{
	
	private World interior;
	private Handler handler;
	public EnterableBuilding(Handler handler, BufferedImage texture, float x, float y, int width, int height, World interior, int entX, int entY, int entWidth, int entHeight, int entReturnX, int entReturnY) {
		super(handler, texture, x, y, width, height);
		this.interior = interior;
		this.handler = handler;
		
		if(interior != null)
			handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new MapChanger(handler, entX, entY, entWidth, entHeight, entReturnX, entReturnY, interior));
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void onDie() {
		
	}

}
