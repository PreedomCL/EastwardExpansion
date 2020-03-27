package game.gfx.lighting;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

public class LightSource {
	
	
	float x, y;
	Color lightColor;
	float lightLevel;
	
	public LightSource(float x, float y, Color lightColor, float lightLevel) {
		
		this.x = x;
		this.y = y;
		this.lightColor = lightColor;
		this.lightLevel = lightLevel;
		
	}
	
	public void render(Graphics g) {
		
		Graphics2D gg = (Graphics2D) g;
		Point2D center = new Point2D.Float(x,y);
		float[] distance = {0f, 1.0f};
		Color[] colors = {lightColor, Color.black};
		RadialGradientPaint p = new RadialGradientPaint(center, lightLevel, distance, colors);
		gg.setPaint(p);
		gg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .50f));
		gg.fillRect((int)x,(int) y,(int) x +(int) lightLevel,(int) y + (int)lightLevel);
		
		
	}
}
