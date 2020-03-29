package game.item.tools;

import java.awt.image.BufferedImage;

import game.Assets;
import game.Handler;

public class PickaxeTool extends Tool{

	public PickaxeTool(Handler handler, int count, float x, float y) {
		super(handler, Assets.pickaxe, "Pickaxe", 7, count, 1, 3, x, y);
	}
}
