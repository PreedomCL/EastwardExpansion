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
import gameone.entities.staticentity.craftingstation.CraftingStation;
import gameone.entities.staticentity.craftingstation.BasicWorkTable;
import gameone.gfx.Animation;
import gameone.gfx.Assets;
import gameone.gfx.Text;
import gameone.inventory.Inventory;
import gameone.item.Item;
import gameone.item.tools.AxeTool;
import gameone.item.tools.PickaxeTool;
import gameone.item.tools.SpearTool;
import gameone.item.tools.StaffTool;
import gameone.state.MenuState;
import gameone.ui.UIRecipeButton;
import gameone.utils.Timer;
import gameone.utils.Utils;

public class Player extends Creature {
	
	private Animation animAttack;
	private Animation[] animPlayer;
	
	private long lastAttackTime, attackCooldown = 500, attackTimer = attackCooldown,lastDestroyTime, destroyCooldown = 500, destroyTimer = destroyCooldown;
	private float mouseX, mouseY;
	private boolean isAttacking;
	private int direction;
	
	private Inventory inventory;
	private Item inventoryItems[];
	private CraftingMenu playerCrafting;
	
	private Timer starvationTimer;
	private int hunger;
	
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, true);
		
		starvationTimer = new Timer();
		
	//Entity
		bounds.x = 16;
		bounds.y = 48;
		bounds.width = 32;
		bounds.height = 16;
		
	//Player
		isAttacking = false;
		animAttack = new Animation(500, Assets.player);
		
		BufferedImage[] frames;
		animPlayer = new Animation[4];
		
		animPlayer[0] = new Animation(300, frames = new BufferedImage[]{Assets.player[1], Assets.player[2]});
		animPlayer[1] = new Animation(300, frames = new BufferedImage[]{Assets.player[4], Assets.player[5]});
		animPlayer[2] = new Animation(300, frames = new BufferedImage[]{Assets.player[7],Assets.player[6], Assets.player[8],Assets.player[6]});
		animPlayer[3] = new Animation(300, frames = new BufferedImage[]{Assets.player[10],Assets.player[9], Assets.player[11],Assets.player[9]});
		
		inventory = new Inventory(handler);
		inventoryItems = new Item[10];
		
		hunger = 10;
	}

	@Override
	public void tick() {
		getInput();
		
		if(checkEntityCollisions(0f,0f))
			x += speed;
		
		move();
		handler.getGameCamera().centerOnEntity(this);

	//Animations
		
		animAttack.tick();
		
		for(Animation a: animPlayer) {
			a.tick();
		}
	//Direction
		
		if(handler.getKeyManager().down)
			direction = 0;
		else if(handler.getKeyManager().up)
			direction = 1;
		else if(handler.getKeyManager().right)
			direction = 2;
		else if(handler.getKeyManager().left)
			direction = 3;
		
		
		
	//Hunger
		if (Utils.randomNumber (2000, 1 ) == 5)
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
		
	//Test Code
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)) {
			handler.getWorld().getEntityManager().getEntitiesToAdd().add(new BasicWorkTable(handler, 200, 200));
		}
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)) {
			handler.setWorld(handler.getWorld2());
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Z)) {
			handler.setWorld(handler.getTown());
		}
		
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
		
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this) || e.getType() == 0)
				continue;
			if(mouseX >= e.getCollisionBounds(0, 0).x && mouseX <= e.getCollisionBounds(0, 0).x + e.getCollisionBounds(0, 0).width &&
				mouseY >= e.getCollisionBounds(0, 0).y && mouseY <= e.getCollisionBounds(0, 0).y + e.getCollisionBounds(0, 0).height) {
				
				
				if(Math.hypot(Math.abs(e.getCollisionBounds(0f,0f).getCenterX() - this.getCollisionBounds(0f,0f).getCenterX()),
				Math.abs(e.getCollisionBounds(0f,0f).getCenterY() - this.getCollisionBounds(0f,0f).getCenterY())) > 50)
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
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
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
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
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
		if(xMove == 0 && yMove == 0) {
			return Assets.player[direction * 3];
		}else {
			return animPlayer[direction].getCurrentFrame();
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
