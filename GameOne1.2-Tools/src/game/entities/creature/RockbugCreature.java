package game.entities.creature;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.Handler;
import game.gfx.Assets;
import game.item.Item;
import game.item.StoneItem;
import game.item.WoodItem;
import game.utils.Utils;


public class RockbugCreature extends Creature{
	
	Random rand;
	private int moveDirection, moveLength;
	private long moveTimer, moveLastTime, attackTimer, lastAttackTime, attackCooldown = 500;
	public RockbugCreature(Handler handler, float x, float y) {
		super(handler, x, y, 32, 32, false);
		rand = new Random();
		moveDirection = rand.nextInt(10);
		moveLength = rand.nextInt(100);
		moveTimer = 0;
		speed = 1;
		health = 5;
	}

	@Override
	public void tick() {
		movement();
		move();
		checkAttacks();
	}

	@Override
	public void render(Graphics g) {
		if(xMove == 0 && yMove == 0) {
			g.drawImage(Assets.rock, (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
		}else {
			g.drawImage(Assets.rockbug, (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
			
		}
	}

	@Override
	public void onDie() {
		if(health > -10) {
			handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new RockbugCreature(handler, x + 64, y - 64));
			handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new RockbugCreature(handler, x - 64, y + 64));
			handler.getCurrentWorld().getEntityManager().getEntitiesToAdd().add(new RockbugCreature(handler, x - 64, y - 64));
			handler.getCurrentWorld().getItemManager().getItemsToAdd().add(new StoneItem(handler,Utils.randomNumber(4, 2), x, y));
		}
	}
	
	public void movement() {
		
		
		moveTimer += System.currentTimeMillis() - moveLastTime;
		moveLastTime = System.currentTimeMillis();
		if(moveLength < moveTimer) {
			
			moveTimer = 0;
			moveLength = (rand.nextInt(1000) + 500);
			moveDirection = rand.nextInt(20);
			
			switch(moveDirection){
				case 0 :
					xMove = -speed;
					break;
				case 1 :
					yMove = -speed;
					break;
				case 2 :
					xMove = speed;
					break;
				case 3 :
					yMove = speed;
					break;
				default :
					xMove = 0;
					yMove = 0;
					break;
			}
		}
	}
	
	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTime;
		lastAttackTime = System.currentTimeMillis();
		
		if(attackTimer < attackCooldown)
			return;
		
		Rectangle cb =  getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();
		int arSize = cb.width + 4;
		ar.width = arSize;
		ar.height = arSize;
		ar.x = cb.x - 2;
		ar.y = cb.y - 2;
		
		attackTimer = 0;
		
		if(ar.intersects(handler.getCurrentWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f))) {
			handler.getCurrentWorld().getEntityManager().getPlayer().hurt(1);
		}
	}

}
