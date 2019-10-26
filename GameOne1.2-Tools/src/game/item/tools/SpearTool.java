package game.item.tools;

import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Assets;

public class SpearTool extends Tool {

	public SpearTool(Handler handler,int count, float x, float y) {
		super(handler, Assets.spear, "Spear", 4, count, 3, 2, x, y);
		
	}

	

}
