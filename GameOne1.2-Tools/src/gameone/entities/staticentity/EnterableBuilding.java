package gameone.entities.staticentity;

import java.awt.image.BufferedImage;

import gameone.Handler;

public class EnterableBuilding extends Building{

	public EnterableBuilding(Handler handler, BufferedImage texture, float x, float y, int width, int height) {
		super(handler, texture, x, y, width, height - 15);
		
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
