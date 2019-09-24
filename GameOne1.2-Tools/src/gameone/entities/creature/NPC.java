package gameone.entities.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;



import gameone.Handler;
import gameone.gfx.Assets;
import gameone.gfx.Text;
import gameone.utils.Timer;

public class NPC extends Creature{
	private Rectangle activeBounds;
	private BufferedImage texture;
	private Boolean isTalking = false, inRange = false;
	private String[] speech; 
	private int phrase = 0;
	private Timer speechTimer;
	public NPC(Handler handler, BufferedImage texture, String[] speech, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		this.texture = texture;
		this.speech = speech;
		height = 64;
		speechTimer = new Timer();
		activeBounds = new Rectangle();
		activeBounds.x =(int) (-16 + x);
		activeBounds.y =(int) (-16 + y);
		activeBounds.width = width + 32;
		activeBounds.height = height + 32;
	}

	@Override
	public void tick() {
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(activeBounds))
			inRange = true;
		else {
			inRange = false;
			isTalking= false;
			
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && inRange && isTalking) {
			isTalking = false;
			
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && inRange && !isTalking) {
			isTalking = true;
			phrase= 0;
			speechTimer.stop();
			speechTimer.start(1000);
		}
		if(isTalking) {
			if(speechTimer.run () && phrase < speech.length - 1) {
				
				phrase ++;
				speechTimer.stop();
				speechTimer.start(1000);
				System.out.println(phrase);
			}
			
			
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture,(int)(x-handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()), null);
		if(inRange && !isTalking) {
			g.setColor(Color.lightGray);
			g.fillRect((int) (x + 40 - handler.getGameCamera().getxOffset()),(int) (y - 13 - handler.getGameCamera().getyOffset()), 100, 17);
			Text.drawString(g, "Press 'E' to Talk",(int) (x + 40 - handler.getGameCamera().getxOffset()) ,(int) (y - handler.getGameCamera().getyOffset()), false, Color.BLACK, Assets.font14);
		}
		
		if(isTalking) {
			
			g.setColor(Color.lightGray);
			g.fillRect(0,0, 600, 26);	
			Text.drawString(g, speech [phrase], 0, 20, false, Color.black, Assets.font20);
		}
	}
	@Override
	public void onDie() {
		// TODO Auto-generated method stub
		
	}
	 
}
