package gameone.item;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import gameone.Handler;

public class ItemManager {
	
	private Handler handler;
	private ArrayList<Item> items, itemsToAdd;
	
	public ItemManager(Handler handler) {
		this.handler = handler;
		items = new ArrayList<Item>();
		itemsToAdd = new ArrayList<Item>();
	}
	
	public void tick() {
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item i = it.next();
			i.tick();
			if(i.isPickedUp())
				it.remove();
		}
	}
	
	public void render(Graphics g) {
		for(Item i :  items)
			i.render(g);
	}
	
	public void addItem(Item i) {
		items.add(i);
	}

	//Getters Setters
	
	public Handler getHandler() {
		return handler;
	}

	public ArrayList<Item> getItemsToAdd() {
		return itemsToAdd;
	}

	public void setItemsToAdd(ArrayList<Item> itemsToAdd) {
		this.itemsToAdd = itemsToAdd;
	}
	
	
}
