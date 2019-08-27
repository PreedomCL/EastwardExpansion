package gameone.item.tools;




import gameone.Handler;
import gameone.entities.Entity;
import gameone.gfx.Assets;

public class StaffTool extends Tool{

	public StaffTool(Handler handler, int count, float x, float y) {
		super(handler, Assets.unknown, "Staff", 3, count, 2, 5, x, y);
	}
	
	public void use(Entity e) {
		e.setX(handler.getMouseManager().getMouseX());
		e.setY(handler.getMouseManager().getMouseY());
	}
}
