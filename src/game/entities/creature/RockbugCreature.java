package game.entities.creature;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import game.Handler;
import game.gfx.Assets;
import game.item.Item;
import game.item.StoneItem;
import game.item.WoodItem;
import game.pathfinding.PathNode;
import game.utils.Utils;


public class RockbugCreature extends Creature{
	
	Random rand;
	private int moveDirection, moveLength;
	private long moveTimer, moveLastTime, attackTimer, lastAttackTime, attackCooldown = 2000;
	private static final int[] NST = {2, 4};
	private boolean angered = false;
	
	public RockbugCreature(Handler handler, float x, float y) {
		super(handler, x, y, 32, 32, NST);
		rand = new Random();
		moveDirection = rand.nextInt(10);
		moveLength = rand.nextInt(100);
		moveTimer = 0;
		speed = 1;
		health = 5;
		solid = false;
		
		activeBounds = new Rectangle(-64, -64, width + 128, height + 128);
	}

	@Override
	public void tick() {
		if(checkActiveBounds()) {
			angered = true;
		}
		
		movement();
		move();
		checkAttacks();
	}

	@Override
	public void render(Graphics g) {
		if(xMove == 0 && yMove == 0) {
			g.drawImage(Assets.rockbug[4], (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
		}else if(yMove < 0){
			g.drawImage(Assets.rockbug[3], (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
		}else if(yMove > 0){
			g.drawImage(Assets.rockbug[2], (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
		}else if(xMove > 0){
			g.drawImage(Assets.rockbug[1], (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
		}else if(xMove < 0){
			g.drawImage(Assets.rockbug[0], (int)(x - handler.getGameCamera().getxOffset()), (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
		}
	}

	@Override
	public void onDie() {
			handler.getCurrentWorld().getItemManager().getItemsToAdd().add(new StoneItem(handler,Utils.randomNumber(4, 2), x, y));
	}
	
	public void movement() {
		
		if(bounds.intersects(handler.getCurrentWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f))) {
			return;
		}
		
		if(Math.hypot(Math.abs(x-handler.getCurrentWorld().getEntityManager().getPlayer().getcX()), Math.abs(y-handler.getCurrentWorld().getEntityManager().getPlayer().getcY())) < 400 && angered) {
			ArrayList<PathNode>path = handler.getCurrentWorld().getPathFinder().findPath(handler.getCurrentWorld().getPathFinder().getNode((int) getcX(),(int) getcY()), handler.getCurrentWorld().getPathFinder().getNode((int) handler.getCurrentWorld().getEntityManager().getPlayer().getcX(),(int) handler.getCurrentWorld().getEntityManager().getPlayer().getcY()), 2);
			PathNode destination;
			
			if(!path.isEmpty()) {
				if(path.size() > 2)
					destination = path.get(path.size()-1);
				else
					destination = path.get(0);
				xMove = (destination.getcX() - getcX()) / 16;
				yMove = (destination.getcY() - getcY()) / 16;
			}
		}else {
			angered = false;
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
