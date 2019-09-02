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
	private Item checkI;
	private int length;
		
	
	public Recipe(Handler handler, Item items[], Item yeild, int length) {
		this.items = items;
		this.handler = handler;
		this.length = length;
	}
	
	public void onCraft(Inventory inv) {
		
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
