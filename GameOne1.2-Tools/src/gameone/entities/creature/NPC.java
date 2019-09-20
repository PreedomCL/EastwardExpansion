package gameone.entities.creature;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameone.Handler;

public class NPC extends Creature{
	private BufferedImage texture;
	private Boolean isTalking = false;
	public NPC(Handler handler, BufferedImage texture, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		this.texture = texture;
	}

	@Override
	public void tick() {
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
			isTalking=true;
			System.out.println(isTalking);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture,(int) x,(int) y, null);
		
	}

	@Override
	public void onDie() {
		// TODO Auto-generated method stub
		
	}
	 
}
