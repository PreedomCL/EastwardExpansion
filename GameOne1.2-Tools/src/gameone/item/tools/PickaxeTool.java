package gameone.item.tools;

import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.gfx.Assets;

public class PickaxeTool extends Tool{

	public PickaxeTool(Handler handler, int count, float x, float y) {
		super(handler, Assets.pickaxe, "Pickaxe", 7, count, 1, 3, x, y);
	}
}
