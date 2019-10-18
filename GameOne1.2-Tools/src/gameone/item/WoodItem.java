package gameone.item;

import java.awt.Graphics;

import gameone.Handler;
import gameone.entities.Entity;
import gameone.gfx.Assets;

public class WoodItem extends Item {

	public WoodItem(Handler handler,int count, float x, float y) {
		super(handler, Assets.log,"Log", 1, count, x, y);
	}

}
