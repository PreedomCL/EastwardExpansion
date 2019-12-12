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
	
	private Boolean inRange = false, open = false, built = false;
	private CraftingMenu craftingMenu;
	private Rectangle activeBounds;
	private Handler handler;
	
	public Bridge(Handler handler, float x, float y, int width, int height, Recipe recipe) {
		super(handler, x, y, width, height, true);
		this.handler = handler;
		
		Recipe[] recipes = {recipe};
		
		craftingMenu = new CraftingMenu(handler,recipes);
		health = 1000;
		activeBounds = new Rectangle((int)x - 16, (int)y - 16, width + 32, height + 32);
	}

	@Override
	public void tick() {
		
		//Conditionals for open? and inRange?
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(activeBounds))
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
				if(handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()[i] == null)
					continue;
				if(handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()[i].getId() == 11) {
					handler.getWorld().getEntityManager().getPlayer().getInventory().removeItem(handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems()[i], 1);
					built = true;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height);
		if(built)
			g.fillRect((int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width + 150, height);
		
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
