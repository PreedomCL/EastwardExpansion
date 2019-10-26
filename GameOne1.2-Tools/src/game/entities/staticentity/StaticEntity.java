package game.entities.staticentity;

import game.Handler;
import game.entities.Entity;

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, float x, float y, int width, int height, boolean excused) {
		super(handler, x, y, width, height, excused);
		type = 0;
	}
	
	
}
