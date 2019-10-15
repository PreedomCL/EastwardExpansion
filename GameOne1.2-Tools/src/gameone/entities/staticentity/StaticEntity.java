package gameone.entities.staticentity;

import gameone.Handler;
import gameone.entities.Entity;

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, float x, float y, int width, int height, boolean excused) {
		super(handler, x, y, width, height, excused);
		type = 0;
	}
	
	
}
