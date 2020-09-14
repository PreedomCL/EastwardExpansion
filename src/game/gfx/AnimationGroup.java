package game.gfx;

import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
public class AnimationGroup {
	
	private JSONObject animObj;
	private Animation[] animations;
	public AnimationGroup(String pathToJSON) throws Exception{
		animObj = (JSONObject) new JSONParser().parse(new FileReader(System.getProperty("user.dir") + pathToJSON));
		readJSON();
	}
	
	private void readJSON() {
		JSONArray frameData = (JSONArray) animObj.get("frames");
		JSONObject meta = (JSONObject)animObj.get("meta");
		JSONArray tags = (JSONArray) meta.get("frameTags");
		animations = new Animation[tags.size()];
		BufferedImage image = ImageLoader.loadImage("/res/textures/" + meta.get("image").toString());
		for(int i = 0; i < tags.size(); i ++) {
			JSONObject currentTag = (JSONObject) tags.get(i);
			long from = (long) currentTag.get("from"), to = (long) currentTag.get("to");
			BufferedImage[] frames = new BufferedImage[(int) (to - from + 1)];
			int[] duration = new int[(int) (to-from+1)];
			
			for(int j = (int) from; j < (int) to + 1; j ++) {
				JSONObject frame = (JSONObject) frameData.get(j);
				frames[j-(int) from] = image.getSubimage( (int)(long)((JSONObject)frame.get("frame")).get("x"), (int)(long)((JSONObject)frame.get("frame")).get("y"), (int)(long)((JSONObject)frame.get("frame")).get("w"), (int)(long)((JSONObject)frame.get("frame")).get("h"));
				duration[j-(int) from] = (int)(long)frame.get("duration");
			}
			
			animations[i] = new Animation(duration, frames, currentTag.get("name").toString());
		}
	}

	public Animation[] getAnimations() {
		return animations;
	}
	
	public Animation getAnimation(String tag) {
		
		for(Animation a:animations) {
			if(a.getTag().contentEquals(tag)) {
				return a;
			}
		}
		
		new Throwable("No animation with the specified tag was found: \"" + tag + "\"").printStackTrace();
		return animations[0];
	}
}
