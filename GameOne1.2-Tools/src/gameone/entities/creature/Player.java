package gameone.entities.creature;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import gameone.Handler;
import gameone.crafting.CraftingMenu;
import gameone.crafting.Recipe;
import gameone.entities.Entity;
import gameone.gfx.Animation;
import gameone.gfx.Assets;
import gameone.gfx.Text;
import gameone.inventory.Inventory;
import gameone.item.Item;
import gameone.item.tools.SpearTool;
import gameone.item.tools.StaffTool;
import gameone.state.MenuState;
import gameone.utils.Timer;
import gameone.utils.Utils;

public class Player extends Creature {
	
	private Animation animAttack;
	
	private long lastAttackTime, attackCooldown = 500, attackTimer = attackCooldown,lastDestroyTime, destroyCooldown = 500, destroyTimer = destroyCooldown;
	private float mouseX, mouseY;
	private boolean isAttacking;
	
	private Inventory inventory;
	private Item inventoryItems[];
	private CraftingMenu playerCrafting;
	
	private Timer starvationTimer;
	private int hunger;
	
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		starvationTimer = new Timer();
		
		//Entity
		
		bounds.x = 16;
		bounds.y = 48;
		bounds.width = 32;
		bounds.height = 16;
		
		//Player
		isAttacking = false;
		animAttack = new Animation(500, Assets.player);
		
		inventory = new Inventory(handler);
		inventoryItems = new Item[10];
		inventory.addItem(new StaffTool(handler, 1, 0, 0));
		inventory.addItem(new SpearTool(handler, 1, 0, 0));
		
		
		Recipe[] recipes = {handler.getGame().getRecipeManager().getRecipes()[0]};
		playerCrafting = new CraftingMenu(handler, recipes);
		playerCrafting.loadMenu();
		
		hunger = 10;
		
	}

	@Override
	public void tick() {
		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		animAttack.tick();
		
		
		
		//Hunger
		if (Utils.randomNumber (500, 1 ) == 5)
			hunger --;
		if (hunger <= 0){
			starvationTimer.start(1000);
			
			if(starvationTimer.run()) {
				hurt(1);
				starvationTimer.stop();
			}
		}
		
		
		//Inventory
		inventoryItems = inventory.getInventoryItems();
		inventory.tick();
		
		//Actions
		
		//Crafting Test Code
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)) {
			if(handler.getGame().getRecipeManager().getRecipes()[1].checkCraftable(inventory)) {
				handler.getGame().getRecipeManager().getRecipes()[1].craft(inventory);
			}
		}
		//
		
		
		if(handler.getMouseManager().isRightPressed() && inventoryItems[inventory.getSelectedItem()] != null) {
			inventoryItems[inventory.getSelectedItem()].use(this);
		}else if(handler.getMouseManager().isLeftPressed()){
			checkAttacks();
			checkDestroy();
		}
		if(!handler.getMouseManager().isLeftPressed())
			isAttacking = false;
		
		
		
		
		
	}
	
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTime;
		lastAttackTime = System.currentTimeMillis();
		
		if(attackTimer < attackCooldown)
			return;
		
		attackTimer = 0;
		
		mouseX = handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset();
		mouseY = handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset();
		
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this) || e.getType() == 0)
				continue;
			if(mouseX >= e.getCollisionBounds(0, 0).x && mouseX <= e.getCollisionBounds(0, 0).x + e.getCollisionBounds(0, 0).width &&
				mouseY >= e.getCollisionBounds(0, 0).y && mouseY <= e.getCollisionBounds(0, 0).y + e.getCollisionBounds(0, 0).height) {
				
				isAttacking = true;
				if(inventoryItems[inventory.getSelectedItem()] != null)
					inventoryItems[inventory.getSelectedItem()].attack(e);
				else
					e.hurt(1);
				
				if(e.getHealth() == 0)
					isAttacking = false;
				return;
				
			}else {
				isAttacking = false;
			}	
		}
		
	}
	
	private void checkDestroy() {
		destroyTimer += System.currentTimeMillis() - lastDestroyTime;
		lastDestroyTime = System.currentTimeMillis();
		
		if(destroyTimer < destroyCooldown)
			return;
		
		destroyTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this) || e.getType() == 1)
				continue;
			if(mouseX >= e.getCollisionBounds(0, 0).x && mouseX <= e.getCollisionBounds(0, 0).x + e.getCollisionBounds(0, 0).width &&
				mouseY >= e.getCollisionBounds(0, 0).y && mouseY <= e.getCollisionBounds(0, 0).y + e.getCollisionBounds(0, 0).height) {
				
				isAttacking = true;
				
				if(inventoryItems[inventory.getSelectedItem()] != null)
					inventoryItems[inventory.getSelectedItem()].destroy(e);
				else
					e.hurt(1);
				
				if(e.getHealth() == 0)
					isAttacking = false;
				return;
				
			}else {
				isAttacking = false;
			}	
		}
		
	}
	
	@Override
	public void onDie() {
		System.out.println("You Lose");
		//handler.getGame().gameState.setState(handler.getGame().menuState);
		//handler.getMouseManager().setUIManager();
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()) , width, height, null);
		
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
		
		g.setColor(Color.GRAY);
		g.fillRect(409, 575, 302, 16);
		
		g.setColor(Color.RED);
		g.fillRect(410, 576, hunger * 30, 14);
		
		Text.drawString(g, "Hunger", 560, 583, true, Color.BLACK, Assets.font14);
		
		
		g.setColor(Color.GRAY);
		g.fillRect(409, 599, 302, 16);
		
		g.setColor(Color.YELLOW);
		g.fillRect(410, 600, health * 30, 14);
		
		Text.drawString(g, "Health", 560, 607, true, Color.BLACK, Assets.font14);
	//the thing is so cool
		
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(isAttacking) {
			return animAttack.getCurrentFrame();
		}else {
			return Assets.player[0];
		}
	}
	
	
	//G&S
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = hunger;
	}
	
	

}
