package game.entities.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import game.Handler;
import game.gfx.Assets;
import game.gfx.Text;

public abstract class Vehicle extends Creature{
	protected int xRide, yRide;
	protected Rectangle activeBounds;
	protected boolean inRange = false, active = false;
	public Vehicle(Handler handler, float x, float y, int width, int height,int xRide,int yRide,boolean excused) {
		super(handler, x, y, width, height, excused);
		this.xRide = xRide;
		this.yRide= yRide;
		
		activeBounds = new Rectangle();
		activeBounds.x =(int) (x-16);
		activeBounds.y =(int) (y-16);
		activeBounds.width = width + 32;
		activeBounds.height = height + 32;
	}
	@Override
	public void tick() {
		
		activeBounds.x =(int) (x-16);
		activeBounds.y =(int) (y-16);
		
		if(handler.getCurrentWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(activeBounds))
			inRange = true;
		else  {
			inRange = false;
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && inRange) {
			active = !active;
		}
		
		if(active) {
			handler.getCurrentWorld().getEntityManager().getPlayer().setVehicle(this);
			handler.getCurrentWorld().getEntityManager().getPlayer().setSolid(false);
		}else {
			handler.getCurrentWorld().getEntityManager().getPlayer().setVehicle(null);
			handler.getCurrentWorld().getEntityManager().getPlayer().setSolid(true);
		}
	}
	
	protected void Message(Graphics g) {
		if(inRange && !active) {
			g.setColor(Color.lightGray);
			g.fillRect((int) (x + 40 - handler.getGameCamera().getxOffset()),(int) (y - 13 - handler.getGameCamera().getyOffset()), 100, 17);
			Text.drawString(g, "Press 'E' to Ride",(int) (x + 40 - handler.getGameCamera().getxOffset()) ,(int) (y - handler.getGameCamera().getyOffset()), false, Color.BLACK, Assets.font14);
		}
	}
	
	//G&S
	public int getxRide() {
		return xRide;
	}

	public void setxRide(int xRide) {
		this.xRide = xRide;
	}

	public int getyRide() {
		return yRide;
	}

	public void setyRide(int yRide) {
		this.yRide = yRide;
	}

}
