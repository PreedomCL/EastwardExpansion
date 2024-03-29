package game.entities.creature;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import game.Handler;
import game.crafting.CraftingMenu;
import game.crafting.Recipe;
import game.entities.Entity;
import game.entities.staticentity.craftingstation.BasicWorkTable;
import game.entities.staticentity.craftingstation.CraftingStation;
import game.gfx.Animation;
import game.gfx.Assets;
import game.gfx.Text;
import game.gfx.lighting.LightSource;
import game.inventory.Inventory;
import game.item.BridgeBuilder;
import game.item.Item;
import game.item.placeable.BasicWorkTableItem;
import game.item.tools.AxeTool;
import game.item.tools.PickaxeTool;
import game.item.tools.SpearTool;
import game.item.tools.StaffTool;
import game.state.EndState;
import game.state.MenuState;
import game.state.State;
import game.ui.UIRecipeButton;
import game.utils.Timer;
import game.utils.Utils;

public class Player extends Creature {
	
	private long lastAttackTime, attackCooldown = 500, attackTimer = attackCooldown,lastDestroyTime, destroyCooldown = 1000, destroyTimer = destroyCooldown;
	private float mouseX, mouseY;
	private boolean isAttacking;
	private int direction;
	
	private Inventory inventory;
	private Item inventoryItems[];
	private CraftingMenu playerCrafting;
	
	private Timer starvationTimer;
	private int hunger;
	
	private static final int[] NST = {2,4};
	
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, NST);
		
		starvationTimer = new Timer();
		
	//Entity
		bounds.x = 8;
		bounds.y = 52;
		bounds.width = 32;
		bounds.height = 20;
		solid = false;
		width = 48;
		height = 72;
	//Player
		isAttacking = false;
		
		BufferedImage[] frames;
		
		inventory = new Inventory(handler);
		inventoryItems = new Item[10];
		
		hunger = 10;
		
		//inventory.addItem(new SpearTool(handler, 1, 0, 0));
		inventory.addItem(new BridgeBuilder(handler, 1, 0, 0));
	}

	@Override
	public void tick() {
		getInput();
		
		if(checkEntityCollisions(getCollisionBounds(0f,0f)))
			x += speed;
		
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		
		
	//Direction
		
		if(handler.getKeyManager().down) {
			direction = 0;
			Assets.player.getAnimation("Down").tick();
		}else if(handler.getKeyManager().up) {
			direction = 1;
			Assets.player.getAnimation("Up").tick();
		}else if(handler.getKeyManager().left) {
			direction = 2;
			Assets.player.getAnimation("Left").tick();
		}else if(handler.getKeyManager().right) {
			direction = 3;
			Assets.player.getAnimation("Right").tick();
		}
		
	
	//Animations
		
		
	//Hunger & Health Regen
		if (Utils.randomNumber (2000, 1 ) == 5)
			hunger --;
		if (hunger <= 0){
			starvationTimer.start(1000);
			
			if(starvationTimer.run()) {
				hurt(1);
				starvationTimer.stop();
			}
		}
		
		if(hunger > 8 && health <10 && Utils.randomNumber(700, 1) == 5)
			health++;
		else if(hunger > 6 && health <10 && Utils.randomNumber(1500, 1) == 5)
			health++;
		
	//Inventory
		inventoryItems = inventory.getInventoryItems();
		inventory.tick();
		
	//Test Code
//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)) {
//			handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new BasicWorkTable(handler, 200, 200));
//		}
//		
//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)) {
//			handler.setCurrentWorld(handler.getWorld3());
//		}
//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Z)) {
//			handler.setCurrentWorld(handler.getTown());
//		}
		
	//Actions
		if(handler.getMouseManager().isRightPressed()) {
			checkUse();
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
		
		
		for(Entity e : handler.getCurrentWorld().getEntityManager().getEntities()) {
			if(e.equals(this) || e.getType() == 0)
				continue;
			if(mouseX >= e.getCollisionBounds(0, 0).x && mouseX <= e.getCollisionBounds(0, 0).x + e.getCollisionBounds(0, 0).width &&
				mouseY >= e.getCollisionBounds(0, 0).y && mouseY <= e.getCollisionBounds(0, 0).y + e.getCollisionBounds(0, 0).height) {
				
				
				if(Math.hypot(Math.abs(e.getCollisionBounds(0f,0f).getCenterX() - this.getCollisionBounds(0f,0f).getCenterX()),
				Math.abs(e.getCollisionBounds(0f,0f).getCenterY() - this.getCollisionBounds(0f,0f).getCenterY())) > 100)
					return;
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
		
		for(Entity e : handler.getCurrentWorld().getEntityManager().getEntities()) {
			if(e.equals(this) || e.getType() == 1)
				continue;
			if(mouseX >= e.getCollisionBounds(0, 0).x && mouseX <= e.getCollisionBounds(0, 0).x + e.getCollisionBounds(0, 0).width &&
				mouseY >= e.getCollisionBounds(0, 0).y && mouseY <= e.getCollisionBounds(0, 0).y + e.getCollisionBounds(0, 0).height) {
				
				if(Math.hypot(Math.abs(e.getCollisionBounds(0f,0f).getCenterX() - this.getCollisionBounds(0f,0f).getCenterX()),
						Math.abs(e.getCollisionBounds(0f,0f).getCenterY() - this.getCollisionBounds(0f,0f).getCenterY())) > 50)
							return;
				
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
	
	private void checkUse() {
		for(Entity e : handler.getCurrentWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(mouseX >= e.getCollisionBounds(0, 0).x && mouseX <= e.getCollisionBounds(0, 0).x + e.getCollisionBounds(0, 0).width &&
				mouseY >= e.getCollisionBounds(0, 0).y && mouseY <= e.getCollisionBounds(0, 0).y + e.getCollisionBounds(0, 0).height) {
//				if(Math.hypot(Math.abs(e.getCollisionBounds(0f,0f).getCenterX() - this.getCollisionBounds(0f,0f).getCenterX()),
//				Math.abs(e.getCollisionBounds(0f,0f).getCenterY() - this.getCollisionBounds(0f,0f).getCenterY())) > 50)
//					return;
				
				if(inventoryItems[inventory.getSelectedItem()] != null) {
					inventoryItems[inventory.getSelectedItem()].use(e);
				}else {
					System.out.println("Useing");
					e.use();
				}
				return;
				
			}
		}
		if(inventoryItems[inventory.getSelectedItem()] != null)
			inventoryItems[inventory.getSelectedItem()].use(null);		
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
		
		double magnitude = Math.sqrt(Math.pow(xMove, 2) + Math.pow(yMove, 2));
		
		xMove /= magnitude;
		yMove /= magnitude;
		
		xMove *= speed;
		yMove *= speed;
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
		
		if(Math.round(xMove) == 0 && Math.round(yMove) == 0) {
			return Assets.player.getAnimation("Idle").getFrame(direction);
		}else {
			return Assets.player.getAnimations()[direction + 1].getCurrentFrame();
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
