package game.gfx.lighting;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class LightSource {
	
	
	private float x, y;
	private boolean active = true;
	private Color lightColor = null;
	private float lightLevel;
	private Ellipse2D area;
	public LightSource(float x, float y, float lightLevel) {
		
		this.x = x;
		this.y = y;
		this.lightLevel = lightLevel;
		
		area = new Ellipse2D.Float(x-lightLevel * 2, y-lightLevel * 2, lightLevel * 4, lightLevel * 4);
	}
	public LightSource(float x, float y, float lightLevel, Color color) {
		
		this.x = x;
		this.y = y;
		this.lightLevel = lightLevel;
		this.lightColor = color;
		
		area = new Ellipse2D.Float(x-lightLevel * 2, y-lightLevel * 2, lightLevel * 4, lightLevel * 4);
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Color getLightColor() {
		return lightColor;
	}
	public void setLightColor(Color lightColor) {
		this.lightColor = lightColor;
	}
	public float getLightLevel() {
		return lightLevel;
	}
	public void setLightLevel(float lightLevel) {
		this.lightLevel = lightLevel;
	}
	public Ellipse2D getArea() {
		return area;
	}
	public void setArea(Ellipse2D area) {
		this.area = area;
	}
	
	
}
