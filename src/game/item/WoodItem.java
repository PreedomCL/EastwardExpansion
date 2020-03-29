package game.item;

import java.awt.Graphics;

import game.Assets;
import game.Handler;
import game.entities.Entity;

public class WoodItem extends Item {

	public WoodItem(Handler handler,int count, float x, float y) {
		super(handler, Assets.log,"Log", 1, count, x, y);
	}

}
