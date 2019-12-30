package game;

import game.entities.Entity;
import game.entities.creature.Player;
import game.gfx.GameCamera;
import game.input.KeyManager;
import game.input.MouseManager;
import game.worlds.World;

public class Handler {
	private Game game;
	private World currentWorld, town, world2, world3;
	private Player player;
	
	public Handler(Game game) {
		this.game = game;
		
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getCurrentWorld() {
		return currentWorld;
	}

	public void setCurrentWorld(World world) {
		this.currentWorld = world;
	}

	public World getTown() {
		return town;
	}

	public void setTown(World town) {
		this.town = town;
	}

	public World getWorld2() {
		return world2;
	}

	public void setWorld2(World world2) {
		this.world2 = world2;
	}
	

	public World getWorld3() {
		return world3;
	}

	public void setWorld3(World world3) {
		this.world3 = world3;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	
	
}
