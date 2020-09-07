package game.gfx;



import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;


public class Assets {
	
	private static final int width = 32, height = 32;
	  
	public static Font font28, font14,font20;
	
	public static File treeHit;
	
	public static BufferedImage grass, dirt, stone, rock, barrier, water, sand, sapling, log, stones, coin, unknown, craftingStation,
	spear, axe, pickaxe, apple, blacksmith, donkey, woodenPlanks, mineralRock, ironOre, bridgeBuilder, boulder, tallGrass;
	public static BufferedImage[] start, tree, player, grassBorder, bridge, rockbug; 
	public static BufferedImage inventoryScreen;

	
	public static void init() {
		font28 = FontLoader.loadFont(System.getProperty("user.dir") + "/res/fonts/GermaniaOne-Regular.ttf", 28);
		font20 = FontLoader.loadFont(System.getProperty("user.dir") + "/res/fonts/GermaniaOne-Regular.ttf", 20);
		font14 = FontLoader.loadFont(System.getProperty("user.dir") + "/res/fonts/GermaniaOne-Regular.ttf", 14);
		
		treeHit = new File(System.getProperty("user.dir") + "/res/sounds/TreeHit.wav");
		
		SpriteSheet PlayerSheet = new SpriteSheet(ImageLoader.loadImage(System.getProperty("user.dir") + "/res/textures/PlayerSheet.png"));
		SpriteSheet BuildingSheet = new SpriteSheet(ImageLoader.loadImage(System.getProperty("user.dir") + "/res/textures/BuildingSheet.png"));
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(System.getProperty("user.dir") + "/res/textures/SpriteSheet.png"));
		
		inventoryScreen = ImageLoader.loadImage(System.getProperty("user.dir") + "/res/textures/InventoryScreen.png");
		
		start = new BufferedImage[2];
		tree = new BufferedImage[4];
		player = new BufferedImage[12];
		grassBorder = new BufferedImage[4];
		bridge = new BufferedImage[3];
		rockbug = new BufferedImage[5];
		
		//Player
		for(int p = 0; p < player.length; p++) {
			player[p] = PlayerSheet.crop(p * 64, 0, 64, 64);
		}
		
		//Sheet
		
		//row 1
		grass = sheet.crop(0, 0, height, width);
		dirt = sheet.crop(width, 0, height, width);
		barrier = sheet.crop(width * 2, 0, height, width);
		unknown = sheet.crop(width * 3, 0, height, width);
		water = sheet.crop(width * 4, 0, height, width);
		sand = sheet.crop(width * 5, 0, height, width);
		stone = sheet.crop(width * 6, 0, height, width);
		woodenPlanks = sheet.crop(width * 7, 0, height, width);
		grassBorder[0] = sheet.crop(width * 8, 0, height, width);
		grassBorder[1] = sheet.crop(width * 9, 0, height, width);
		grassBorder[2] = sheet.crop(width * 10, 0, height, width);
		grassBorder[3] = sheet.crop(width * 11, 0, height, width);
		bridgeBuilder = sheet.crop(width * 12, 0, height, width);
		tallGrass = sheet.crop(width * 6, height + 16, 48, 48);
		
		//row 2 row 3
		tree[0] = sheet.crop(0, height, height*2, width);
		tree[1] = sheet.crop(width, height, height*2, width);
		tree[2] = sheet.crop(width * 2, height, height*2, width);
		tree[3] = sheet.crop(width * 3, height, height*2, width);
		rock = sheet.crop(width * 4, height, height, width);
		craftingStation = sheet.crop(width * 4,height * 2, height, width);
		sapling = sheet.crop(width * 5, height, height, width);
		mineralRock = sheet.crop(width * 5, height * 2, height, width);
		bridge[0] = sheet.crop(width * 8, height, 64, 86);
		bridge[1] = sheet.crop(width * 8 + 86, height, 58, 45);
		bridge[2] = sheet.crop(width * 8 + 131, height, 64, 68);
		boulder = sheet.crop(width * 15, height, height * 2, width * 2);
		
		//row 4
		start[0] = sheet.crop(0, height * 3, height/2, width);
		start[1] = sheet.crop(0, (height * 3) + (height/2), height/2, width);
		
		//row 5
		log = sheet.crop(0, height * 4, height, width);
		stones = sheet.crop(width, height * 4, height, width);
		spear = sheet.crop(width * 2, height * 4, height, width);
		axe = sheet.crop(width * 3, height * 4, height, width);
		pickaxe = sheet.crop(width * 4, height * 4, height, width);
		apple = sheet.crop(width * 5, height * 4, height, width);
		coin = sheet.crop(width * 6, height * 4, height, width);
		ironOre = sheet.crop(width * 7, height * 4, height, width);
		//row 6
		rockbug[0] = sheet.crop(0, height * 5, height, width);
		rockbug[1] = sheet.crop(width, height * 5, height, width);
		rockbug[2] = sheet.crop(width * 2, height * 5, height, width);
		rockbug[3] = sheet.crop(width * 3, height * 5, height, width);
		rockbug[4] = sheet.crop(width * 4, height * 5, height, width);
		
		donkey = sheet.crop(185, 202, 64, 80);
		
		//buildings
		blacksmith = BuildingSheet.crop(0 , 0, 128, 220);
	}
}
