package game.entities.staticentity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Color;

import game.Handler;
import game.crafting.CraftingMenu;
import game.crafting.Recipe;
import game.gfx.Assets;
import game.gfx.Text;
import game.item.Item;



public class Bridge extends StaticEntity{
	
	private int targetWidth, targetHeight;
	private Boolean inRange = false, open = false, built = false;
	private CraftingMenu craftingMenu;
	private Rectangle activeBounds;
	private Handler handler;
	private static final int[] NST = {-1};
	
	public Bridge(Handler handler, float x, float y, int width, int height, int targetWidth, int targetHeight, Recipe recipe) {
		super(handler, x, y, width, height, NST);
		this.handler = handler;
		this.targetWidth = targetWidth;
		this.targetHeight = targetHeight;
		
		Recipe[] recipes = {recipe};
		
		craftingMenu = new CraftingMenu(handler,recipes);
		renderY = -64;
		health = 1000;
		solid = false;
		walkable = true;
		activeBounds = new Rectangle((int)x - 16, (int)y - 16, width + 32, height + 32);
	}

	@Override
	public void tick() {
		
		//Conditionals for open? and inRange?
		if(handler.getCurrentWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(activeBounds))
			inRange = true;
		else 
			inRange = false;
		
		if(!inRange || built)
			open = false;
		else if(inRange && handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
			open = !open;
		
		if(open && !craftingMenu.active)
			craftingMenu.loadMenu();
		else if(!open && craftingMenu.active)
			craftingMenu.closeMenu();
		
		//BridgeBuilder Item Detection
		
		if(open) {
			for(int i = 0; i < 10; i++) {
				if(handler.getCurrentWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()[i] == null)
					continue;
				if(handler.getCurrentWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()[i].getId() == 11) {
					handler.getCurrentWorld().getEntityManager().getPlayer().getInventory().removeItem(handler.getCurrentWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()[i], 1);
					built = true;
					bounds.width = targetWidth;
					bounds.height = targetHeight;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		if(built) {
			for(int x = 0; x < targetWidth; x += 32) {
				for(int y = 0; y < targetHeight; y += 32) {
					g.drawImage(Assets.woodenPlanks,(int) (x + this.x - handler.getGameCamera().getxOffset()),(int) (y + this.y - handler.getGameCamera().getyOffset()), null);
				}
			}
		}else
			g.fillRect((int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height);
		
		if(inRange && !open && !built) {
			g.setColor(Color.lightGray);
			g.fillRect((int) (x - handler.getGameCamera().getxOffset()),(int) (y - 29 - handler.getGameCamera().getyOffset()), 100, 17);
			Text.drawString(g, "Press 'E' to Open",(int) (x - handler.getGameCamera().getxOffset()) ,(int) (y - handler.getGameCamera().getyOffset() - 16), false, Color.BLACK, Assets.font14);
		}
	}

	@Override
	public void onDie() {
		
		
	}

}
