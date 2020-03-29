package game.item.tools;

import java.awt.image.BufferedImage;

import game.Assets;
import game.Handler;

public class SpearTool extends Tool {

	public SpearTool(Handler handler,int count, float x, float y) {
		super(handler, Assets.spear, "Spear", 4, count, 3, 2, x, y);
		
	}

	

}
