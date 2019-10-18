package gameone.item;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;



import gameone.Handler;
import gameone.entities.Entity;
import gameone.gfx.Assets;
import gameone.tiles.Tile;

public abstract class Item {
	
	//Class
	public static final int ITEMWIDTH = 28, ITEMHEIGHT = 28;
	
	protected Handler handler;
	protected String name;
	protected final int id;
	protected float x, y;
	protected BufferedImage texture;
	protected Rectangle bounds;
	
	protected int count;
	
	protected boolean pickedUp = false;
	
	
	
	
	public Item(Handler handler,BufferedImage texture,String name, int id, int count, float x, float y) {
		this.name = name;
		this.id = id;
		this.handler = handler;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.count = count;
		bounds = new Rectangle( (int)x, (int)y, ITEMWIDTH, ITEMHEIGHT);
		
		
	}
	
	public void tick() {
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds) && handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)) {
			if(handler.getWorld().getEntityManager().getPlayer().getInventory().acceptItem(this)) {
				pickedUp= true;
				handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
			}
		}
		
		if(handler.getWorld().getTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, (int) (y + bounds.y) / Tile.TILEHEIGHT).isSolid())
			pickedUp = true;
	}
	
	public void render(Graphics g) {
		if(handler == null)
			return;
		render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}
	
	public void use(Entity e) {
		if(e == null)
			return;
		e.use();
	}
	public void attack(Entity e) {
		e.hurt(1);
	}
	public void destroy(Entity e) {
		
		e.hurt(1);
	}
	
	
	
	
	
	//Getters and Setters
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
	
	
	
	
}