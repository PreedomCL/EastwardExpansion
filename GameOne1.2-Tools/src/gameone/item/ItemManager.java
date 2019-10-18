package gameone.item;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import gameone.Handler;
import gameone.item.food.AppleFood;
import gameone.item.placeable.BasicWorkTableItem;
import gameone.item.tools.AxeTool;
import gameone.item.tools.PickaxeTool;
import gameone.item.tools.SpearTool;
import gameone.item.tools.StaffTool;

public class ItemManager {
	
	private Handler handler;
	private ArrayList<Item> items, itemsToAdd;
	
	//handler
	
	
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
	
	public Item getItem(int index, int count) {
		switch(index) {
			case 1:
				return new WoodItem(handler, count, 0, 0);
			case 2:
				return new StoneItem(handler, count, 0, 0);
			case 3:
				return new StaffTool(handler, count, 0, 0);
			case 4:
				return new SpearTool(handler, count, 0, 0);
			case 5:
				return new AppleFood(handler, count, 0, 0);
			case 6:
				return new AxeTool(handler, count, 0, 0);
			case 7:
				return new PickaxeTool(handler, count, 0, 0);
			case 8:
				return new BasicWorkTableItem(handler, count, 0, 0);
			case 9:
				return new CoinItem(handler, count, 0, 0);
		}
		return null;
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
