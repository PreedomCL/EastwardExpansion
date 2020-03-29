package game.item.tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Assets;
import game.Handler;
import game.entities.Entity;
import game.item.Item;

public class StaffTool extends Tool{

	public StaffTool(Handler handler, int count, float x, float y) {
		super(handler, Assets.unknown, "Staff", 3, count, 2, 5, x, y);
	}
	
	public void use(Entity e) {
		e.setX(handler.getMouseManager().getMouseX());
		e.setY(handler.getMouseManager().getMouseY());
	}
}
