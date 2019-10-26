package game.inventory;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.Handler;
import game.gfx.Assets;
import game.gfx.Text;
import game.item.Item;
import game.ui.UIManager;
import game.ui.UIRecipeButton;

public class Inventory {
	private Handler handler;
	private boolean active = true;
	private Item inventoryItems[];
	private int selectedItem = 0;
	
	
	private int invX = 320, invY = 519, invSpacing = 48;
	
	public Inventory(Handler handler) {
		this.handler = handler;
		this.inventoryItems = new Item[10];
	}
	
	public void tick() {
//		If we want active/inactive inventory
//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
//			active = !active;
		
		if(!active)
			return;	
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1)) {
			selectedItem = 0;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2)) {
			selectedItem = 1;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3)) {
			selectedItem = 2;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4)) {
			selectedItem = 3;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_5)) {
			selectedItem = 4;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_6)) {
			selectedItem = 5;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_7)) {
			selectedItem = 6;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_8)) {
			selectedItem = 7;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_9)) {
			selectedItem = 8;
		}else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_0)) {
			selectedItem = 9;
		}
		
		for(int i = 0; i < inventoryItems.length; i++) {
			if(inventoryItems[i] == null)
				continue;
			if(inventoryItems[i].getCount() <= 0) {
				inventoryItems[i] = null;
			}
		}
	}
	
	public void render(Graphics g) {
		if(!active)
			return;
		
		g.drawImage(Assets.inventoryScreen, 0, 0, 1120 , 630,null);
		
		for(int i = 0; i < 10; i++) {
			if(inventoryItems[i] == null)
				continue;
			
			g.drawImage(inventoryItems[i].getTexture(), invX + (invSpacing * i), invY, 48, 48, null);
			
			if(selectedItem == i) {
				Text.drawString(g, Integer.toString(inventoryItems[i].getCount()), invX + (invSpacing * i) + 24, invY + 24, true, Color.YELLOW, Assets.font28);
				Text.drawString(g, inventoryItems[i].getName(), invX + (invSpacing * i) + 24, invY - 14 , true, Color.YELLOW, Assets.font14);
				
			}else {
				Text.drawString(g, Integer.toString(inventoryItems[i].getCount()), invX + (invSpacing * i) + 24, invY + 24, true, Color.WHITE, Assets.font28);
				Text.drawString(g, inventoryItems[i].getName(), invX + (invSpacing * i) + 24, invY - 14, true, Color.WHITE, Assets.font14);
			}
		}
		
		//Text.drawString(g, "Heyyyo", 200, 200, false, Color.WHITE, Assets.font28);
		
	}
	
	//Inventory Methods
	public boolean acceptItem(Item item) {
		for(Item i : inventoryItems) {
			if(i == null)
				return true;
			if(i.getId() == item.getId() && i.getCount() <=50) {
				return true;
			}
		}
		return false;
	}
	
	public void addItem(Item item) {
		for(Item i : inventoryItems) {
			if(i == null) {
				continue;
			}
			if(i.getId() == item.getId() && i.getCount() <=50) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		
		
		for(int j = 0; j < inventoryItems.length; j++) {
			if(inventoryItems[j] == null) {
				inventoryItems[j] = item;
				return;
			}
		}
	}
	
	public void removeItem (Item item, int count) {	
		
		if(inventoryItems[selectedItem] == item) {
			inventoryItems[selectedItem].setCount(inventoryItems[selectedItem].getCount() - count);
			return;
		}
		for (Item i : inventoryItems) {
			if(i == null)
				continue;
			if(i.getId() == item.getId()) {
				i.setCount(i.getCount() - count);
				return;
			}
		}
		
	}
	
	
	//G&S
	
	
	public Handler getHandler() {
		return handler;
	}

	public Item[] getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(Item[] inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public int getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(int selectedItem) {
		this.selectedItem = selectedItem;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
}
