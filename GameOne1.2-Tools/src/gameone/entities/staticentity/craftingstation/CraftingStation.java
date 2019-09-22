package gameone.entities.staticentity.craftingstation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.crafting.CraftingMenu;
import gameone.crafting.Recipe;
import gameone.entities.staticentity.StaticEntity;
import gameone.gfx.Assets;
import gameone.gfx.Text;

public abstract class CraftingStation extends StaticEntity{
	
	protected Recipe recipes[];
	protected CraftingMenu craftingMenu;
	protected int instance;
	protected boolean open = false, inRange = false;
	protected BufferedImage texture;
	protected Rectangle activeBounds;
	
	public CraftingStation(Handler handler, BufferedImage texture, float x, float y, Recipe recipes[]) {
		super(handler, x, y, 32, 32);
		this.recipes = recipes;
		this.texture = texture;
		craftingMenu = new CraftingMenu(handler, recipes);
		//
		System.out.println(handler.getWorld());
		activeBounds = new Rectangle();
		activeBounds.x = (int) (-16 + x);
		activeBounds.y = (int) (-16 + y);
		activeBounds.width = 64;
		activeBounds.height = 64;
		
	}
	
	@Override
	public void tick() {
		
		craftingMenu.tick();
		
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(activeBounds))
			inRange = true;
		else {
			inRange = false;
			open = false;
			craftingMenu.closeMenu();
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && inRange && open) {
			open = false;
			craftingMenu.closeMenu();
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && inRange && !open) {
			open = true;
			craftingMenu.loadMenu();
		}
		
	
	}
	
	public void render(Graphics g) {
		g.drawImage(texture, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
		if(inRange && !open) {
			g.setColor(Color.lightGray);
			g.fillRect((int) (x + 40 - handler.getGameCamera().getxOffset()),(int) (y - 13 - handler.getGameCamera().getyOffset()), 100, 17);
			Text.drawString(g, "Press 'E' to Open",(int) (x + 40 - handler.getGameCamera().getxOffset()) ,(int) (y - handler.getGameCamera().getyOffset()), false, Color.BLACK, Assets.font14);
		}
	}
	
}
