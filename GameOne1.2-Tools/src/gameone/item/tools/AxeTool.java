package gameone.item.tools;

import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.gfx.Assets;

public class AxeTool extends Tool{

	public AxeTool(Handler handler, int count, float x, float y) {
		super(handler, Assets.axe, "Axe", 6, count, 2, 3, x, y);
		
	}

}
