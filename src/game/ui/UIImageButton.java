package game.ui;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.input.ClickListener;

public class UIImageButton extends UIObject{
	
	private BufferedImage[] images;
	private ClickListener clicker;
	private boolean singleUse;
	
	public UIImageButton(float x, float y, int width, int height, boolean singleUse, BufferedImage[] images, ClickListener clicker) {
		super(null, x, y, width, height);
		this.images = images;
		this.clicker = clicker;
		this.singleUse = singleUse;
		
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		if(hovering)
			g.drawImage(images[1], (int) x, (int) y, width, height, null);
		else
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
	}

	@Override
	public void onClick() {
		if(active)
			clicker.onClick();
		if(singleUse)
			active = false;
			
	}

}
