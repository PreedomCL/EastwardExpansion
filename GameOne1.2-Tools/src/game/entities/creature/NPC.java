package game.entities.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import game.Handler;
import game.crafting.CraftingMenu;
import game.crafting.Recipe;
import game.gfx.Assets;
import game.gfx.Text;
import game.utils.Timer;

public class NPC extends Creature{
	private Rectangle activeBounds;
	private BufferedImage texture;
	private Boolean isTalking = false, inRange = false, isTrading = false;
	private String[] speech;
	private Recipe[] trades;
	private CraftingMenu tradeMenu;
	private int phrase = 0;
	private Timer speechTimer;
	public NPC(Handler handler, BufferedImage texture, String[] speech, Recipe[] trades, float x, float y, int width, int height) {
		super(handler, x, y, width, height, true);
		this.texture = texture;
		this.speech = speech;
		this.trades = trades;
		tradeMenu = new CraftingMenu(handler, trades);
		
		
		bounds.x = 16;
		bounds.y = 48;
		bounds.height = 16;
		speechTimer = new Timer();
		
		activeBounds = new Rectangle();
		activeBounds.x =(int) (x);
		activeBounds.y =(int) (y-16);
		activeBounds.width = width + 32;
		activeBounds.height = height + 32;
	}

	@Override
	public void tick() {
		tradeMenu.tick();
		
		if(handler.getCurrentWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(activeBounds))
			inRange = true;
		else  {
			inRange = false;
			if(isTalking || isTrading) {
				isTalking = false;
				isTrading = false;
				tradeMenu.closeMenu();
			}
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && inRange && isTalking) {
			isTalking = false;
			isTrading = true;
			tradeMenu.loadMenu();
			
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && inRange && !isTalking) {
			isTalking = true;
			isTrading = false;
			tradeMenu.closeMenu();
			phrase= 0;
			speechTimer.stop();
			speechTimer.start(3000);
		}
		if(isTalking) {
			if(speechTimer.run ()) {
				if(phrase < speech.length - 1) {
					phrase ++;
					speechTimer.stop();
					speechTimer.start(3000);
					System.out.println(phrase);
				}else if(!isTrading) {
					isTrading = true;
					tradeMenu.loadMenu();
					isTalking = false;
				}
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture,(int)(x-handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()), null);
		if(inRange && !isTalking && !isTrading) {
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
