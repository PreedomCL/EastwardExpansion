package gameone.gfx;



import java.awt.Font;
import java.awt.image.BufferedImage;


public class Assets {
	
	private static final int width = 32, height = 32;
	  
	public static Font font28, font14;
	
	public static BufferedImage grass, dirt, rock, barrier, sapling, log, rockbug, stones, unknown;
	public static BufferedImage[] start, tree, player; 
	public static BufferedImage inventoryScreen;

	
	public static void init() {
		font28 = FontLoader.loadFont(System.getProperty("user.dir") + "/res/fonts/GermaniaOne-Regular.ttf", 28);
		font14 = FontLoader.loadFont(System.getProperty("user.dir") + "/res/fonts/GermaniaOne-Regular.ttf", 14);
		
		SpriteSheet PlayerSheet = new SpriteSheet(ImageLoader.loadImage(System.getProperty("user.dir") + "/res/textures/PlayerSheet.png"));
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(System.getProperty("user.dir") + "/res/textures/SpriteSheet.png"));
		
		inventoryScreen = ImageLoader.loadImage(System.getProperty("user.dir") + "/res/textures/InventoryScreen.png");
		
		start = new BufferedImage[2];
		tree = new BufferedImage[4];
		player = new BufferedImage[12];
		
		//Player
		for(int p = 0; p < player.length; p++) {
			player[p] = PlayerSheet.crop(p * 64, 0, 64, 64);
		}
		
		//Sheet
		
		//row 1
		grass = sheet.crop(0, 0, height, width);
		dirt = sheet.crop(width, 0, height, width);
		barrier = sheet.crop(width * 2, 0, height, width);
		rock = sheet.crop(width * 3, 0, height, width);
		log = sheet.crop(width * 4, 0, height, width);
		
		//row 2 row 3
		tree[0] = sheet.crop(0, height, height*2, width);
		tree[1] = sheet.crop(width, height, height*2, width);
		tree[2] = sheet.crop(width * 2, height, height*2, width);
		tree[3] = sheet.crop(width * 3, height, height*2, width);
		rockbug = sheet.crop(width * 4, height, height, width);
		stones = sheet.crop(width * 4,height * 2, height, width);
		
		//row 4
		start[0] = sheet.crop(0, height * 3, height/2, width);
		start[1] = sheet.crop(0, (height * 3) + (height/2), height/2, width);
		sapling = sheet.crop(width*3, height*3, height, width);
		unknown = sheet.crop(width * 4, height * 3, height, width);
	}
}
