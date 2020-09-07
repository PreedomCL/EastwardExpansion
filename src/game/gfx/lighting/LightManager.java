package game.gfx.lighting;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

import game.Handler;
import game.gfx.Assets;
import game.state.GameState;

public class LightManager {
	
	private ArrayList<LightSource> lights = new ArrayList<LightSource>();
	private Handler handler;
	
	public float baseLight = 255;
	public int lightRes = 4;
	private BufferedImage lightMap;
	
	public LightManager(Handler handler) {
		this.handler = handler;
		
	}
	
	public void tick() {
		int t = handler.getGame().gameState.getTime();
		float lightDifference =handler.getCurrentWorld().getSunLight() - handler.getCurrentWorld().getMoonLight();
//		if(t > 500 && t <= 800) {
//			baseLight +=(lightDifference/300);
//			//updateLights();
//		}else if(t > 1700 && t <= 2000) {
//			baseLight -=(lightDifference/300);
//			//updateLights();
//		}
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(lightMap, 0, 0, handler.getWidth(), handler.getHeight(), null);
		updateLights();
		
	}
	
	public void updateLights() {
		if(lightMap == null) {
			lightMap = new BufferedImage(handler.getWidth() / lightRes, handler.getHeight() / lightRes, BufferedImage.TYPE_INT_ARGB);
		}
		WritableRaster raster = lightMap.getRaster();
		
		for(int x = 0; x < lightMap.getWidth(); x ++) {
			for(int y = 0; y < lightMap.getHeight(); y ++) {
				float newX = (x * lightRes)+handler.getGameCamera().getxOffset(), newY = (y * lightRes)+handler.getGameCamera().getyOffset();
				
				LightSource light = null;
				ArrayList<LightSource> possibleLights = new ArrayList<LightSource>();
				
				for(LightSource l: lights) {
					if(l.getArea().contains(new Point2D.Float(newX, newY))) {
						possibleLights.add(l);
					}
				}
				
				if(possibleLights.size() > 1) {
					for(LightSource l: possibleLights) {
						if(light == null) {
							light = l;
						}else if((int) (light.getLightLevel() - (Math.hypot((newX)-light.getX(), (newY)-light.getY()))) <
						(int) (l.getLightLevel() - (Math.hypot((newX)-l.getX(), (newY)-l.getY())))) {
							light = l;
						}
					}
				}else if(possibleLights.size() == 1) {
					light = possibleLights.get(0);
				}
				
				int lightLevel;
				
				if(light == null) {
					lightLevel = (int)baseLight;
					raster.setPixel(x, y, new int[] {0,0,0,255-lightLevel});
					continue;
				}
				
				
				
				lightLevel = (int) (light.getLightLevel() - (Math.hypot((newX)-light.getX(), (newY)-light.getY())) / 2);
				if(lightLevel < 0) {
					lightLevel = 0;
				}
				lightLevel += baseLight;
				if(lightLevel > 255) {
					lightLevel = 255;
				}
				
				if(light.getLightColor() != null) {
					int r_ = (int) (((float)(lightLevel-lightRes*4)/255) * light.getLightColor().getRed());
					int g_ = (int) (((float)(lightLevel-10)/255) * light.getLightColor().getBlue());
					int b_ = (int) (((float)(lightLevel-10)/255) * light.getLightColor().getGreen());
					if(r_ < 0) {
						r_ = 0;
					}
					if(g_ < 0) {
						g_ = 0;
	 				}
					if(b_ < 0) {
						b_ = 0;
					}
					raster.setPixel(x, y, new int[]{r_,g_,b_,255-lightLevel});
				}else {
					raster.setPixel(x, y, new int[]{0,0,0,255-lightLevel});
				}
			}
		}
	}

	public void addLight(LightSource light) {
		lights.add(light);
	}
	
	public ArrayList<LightSource> getLights() {
		return lights;
	}

	public void setLights(ArrayList<LightSource> lights) {
		this.lights = lights;
	}
	
	
}
