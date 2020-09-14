package game.gfx;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;


public class Assets {
	
	private static final int width = 32, height = 32;
	  
	public static Font font28, font14,font20;
	
	public static File treeHit;
	
	public static BufferedImage blacksmith, basicWorkTable, boulder, mineralRock, sapling, shrub,
	apple, axe, bridgeBuilder, coin, ironOre, log, pickaxe, spear, stones, 
	unknown,
	barrier, dirt, grass, sand, stone, water, woodenPlanks,
	inventory;
	public static BufferedImage[] bridge, tree, grassBorder, sandBorder, startButton;
	public static AnimationGroup player, rockbug;
	
	public static void init() throws Exception {
		font28 = FontLoader.loadFont("/res/fonts/GermaniaOne-Regular.ttf", 28);
		font20 = FontLoader.loadFont("/res/fonts/GermaniaOne-Regular.ttf", 20);
		font14 = FontLoader.loadFont("/res/fonts/GermaniaOne-Regular.ttf", 14);
		
		treeHit = new File(System.getProperty("user.dir") + "/res/sounds/treeHit.wav");	
		
		//Sheet
		
		
		
		//Entities
		basicWorkTable = ImageLoader.loadImage("/res/textures/entity_basicWorkTable.png");
		blacksmith = ImageLoader.loadImage("/res/textures/entity_blacksmith.png");
		boulder = ImageLoader.loadImage("/res/textures/entity_boulder.png");
		bridge = loadImageArray(ImageLoader.loadImage("/res/textures/entity_bridge.png"), new int[] {88, 32, 64}, new int[] {64, 64, 64}, 3);
		mineralRock = ImageLoader.loadImage("/res/textures/entity_mineralRock.png");
		player = new AnimationGroup("/res/textures/entity_player.json"); 
		rockbug = new AnimationGroup("/res/textures/entity_rockbug.json");
		sapling = ImageLoader.loadImage("/res/textures/entity_sapling.png");
		shrub = ImageLoader.loadImage("/res/textures/entity_shrub.png");
		tree = loadImageArray(ImageLoader.loadImage("/res/textures/entity_tree.png"), 32);
		
		//Item
		apple = ImageLoader.loadImage("/res/textures/item_apple.png");
		axe = ImageLoader.loadImage("/res/textures/item_axe.png");
		bridgeBuilder = ImageLoader.loadImage("/res/textures/item_bridgeBuilder.png");
		coin = ImageLoader.loadImage("/res/textures/item_bridgeBuilder.png");
		ironOre = ImageLoader.loadImage("/res/textures/item_ironOre.png");
		log = ImageLoader.loadImage("/res/textures/item_log.png");
		pickaxe = ImageLoader.loadImage("/res/textures/item_pickaxe.png");
		spear = ImageLoader.loadImage("/res/textures/item_spear.png");
		stones = ImageLoader.loadImage("/res/textures/item_stones.png");
		
		//Misc
		unknown = ImageLoader.loadImage("/res/textures/misc_unknownTexture.png");
		
		//Tile
		barrier = ImageLoader.loadImage("/res/textures/tile_barrier.png");
		dirt = ImageLoader.loadImage("/res/textures/tile_dirt.png");
		grass = ImageLoader.loadImage("/res/textures/tile_grass.png");
		grassBorder = loadImageArray(ImageLoader.loadImage("/res/textures/tile_grassBorder.png"), new int[] {32, 32, 4, 4}, new int[] {7, 4, 32, 32}, 4);
		sand = ImageLoader.loadImage("/res/textures/tile_sand.png");
		sandBorder = loadImageArray(ImageLoader.loadImage("/res/textures/tile_sandBorder.png"), new int[] {16, 32, 16, 16, 32, 16, 16, 16}, new int[] {16, 16, 16, 16, 16, 16, 32, 32}, 8);
		water = ImageLoader.loadImage("/res/textures/tile_water.png");
		woodenPlanks = ImageLoader.loadImage("/res/textures/tile_woodenPlanks.png");
		
		//UI
		inventory = ImageLoader.loadImage("/res/textures/ui_inventoryScreen.png");
		startButton = loadImageArray(ImageLoader.loadImage("/res/textures/ui_startButton.png"), 32);
		
	}
	
	private static BufferedImage[] loadImageArray(BufferedImage source, int[] frameWidth, int[] frameHeight, int size) {
		BufferedImage[] output = new BufferedImage[size];
		int currentX = 0;
		for(int i = 0; i < size; i++) {
			output[i] = source.getSubimage(currentX, 0, frameWidth[i], frameHeight[i]);
			currentX += frameWidth[i];
		}
		return output;
	}
	private static BufferedImage[] loadImageArray(BufferedImage source,int frameWidth) {
		BufferedImage[] output = new BufferedImage[source.getWidth()/frameWidth];
		for(int i = 0; i < output.length; i++) {
			output[i] = source.getSubimage(frameWidth * i, 0, frameWidth, source.getHeight());
		}
		return output;
	}
}
