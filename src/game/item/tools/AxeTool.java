package game.item.tools;

import java.awt.image.BufferedImage;

import game.Handler;
import game.gfx.Assets;

public class AxeTool extends Tool{

	public AxeTool(Handler handler, int count, float x, float y) {
		super(handler, Assets.axe, "Axe", 6, count, 2, 3, x, y);
		
	}

}
