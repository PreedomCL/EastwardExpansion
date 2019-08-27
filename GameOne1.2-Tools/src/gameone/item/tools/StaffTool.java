package gameone.item.tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.entities.Entity;
import gameone.gfx.Assets;
import gameone.item.Item;

public class StaffTool extends Tool{

	public StaffTool(Handler handler, int count, float x, float y) {
		super(handler, Assets.unknown, "Staff", 3, count, 2, 5, x, y);
	}
	
	public void use(Entity e) {
		e.setX(handler.getMouseManager().getMouseX());
		e.setY(handler.getMouseManager().getMouseY());
	}
}
