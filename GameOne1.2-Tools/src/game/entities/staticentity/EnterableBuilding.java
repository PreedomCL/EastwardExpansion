package game.entities.staticentity;

import java.awt.image.BufferedImage;

import game.Handler;

public class EnterableBuilding extends Building{

	public EnterableBuilding(Handler handler, BufferedImage texture, float x, float y, int width, int height) {
		super(handler, texture, x, y, width, height);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDie() {
		// TODO Auto-generated method stub
		
	}

}
