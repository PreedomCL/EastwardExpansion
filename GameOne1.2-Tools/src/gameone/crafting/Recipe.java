package gameone.crafting;

import gameone.Handler;
import gameone.inventory.Inventory;
import gameone.item.Item;
import gameone.item.items.WoodItem;

public class Recipe {
	
	private Handler handler;
	private Item items[] = new Item[10];
	private boolean checkedItems[] = new boolean[10];
	private boolean isCraftable;
	private Item checkI, yield;
	private int length, itemsRemaining;
		
	
	public Recipe(Handler handler, Item items[], Item yield, int length) {
		this.items = items;
		this.handler = handler;
		this.length = length;
		this.yield = yield;
	}
	
	public void craft(Inventory inv) {
		for(Item cI : items) {
			if(cI == null)
				continue;
			itemsRemaining = cI.getCount();
			
			for(Item i: inv.getInventoryItems()) {
				if(i == null )
					continue;		
				if(itemsRemaining == 0)
					continue;
				
				if(i.getId() == cI.getId()) {
					
					if(i.getCount() < cI.getCount()) {
						itemsRemaining = cI.getCount() - i.getCount();
						i.setCount(0);
					}else {
						i.setCount(i.getCount() - cI.getCount());
					}
					
				}
			}
		}
		
		if(inv.acceptItem(yield)) {
			inv.addItem(yield);
		}else {
			System.out.println("You do not have enough space to accept " + yield.getCount() + " " + yield.getName() + "(s)");
		}
	}
	
	
	public boolean checkCraftable(Inventory inv) {
		isCraftable = true;
		
		for(int j = 0; j <  10; j++) {
			checkI = items[j];
			checkedItems[j] = false;
			
			
			if(checkI == null)
				continue;
			
			for(Item i: inv.getInventoryItems()) {
				if(i == null )
					continue;
				
				
				
				if(i.getId() != checkI.getId()|| checkedItems[j] == true)
					continue;
				
				
				
				if(i.getCount() >= checkI.getCount())
					checkedItems[j] = true;
			}
		}
		
		
		for(int g = 0; g < 10; g++) {
			boolean b = checkedItems[g];
						
			if(b == false && g < length)
				isCraftable = false;
		}
		
		System.out.println("Is Craftable:" + isCraftable);
		return isCraftable;
	}

}
