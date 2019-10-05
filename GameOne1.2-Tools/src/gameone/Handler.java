package gameone;

import gameone.entities.Entity;
import gameone.entities.creature.Player;
import gameone.gfx.GameCamera;
import gameone.input.KeyManager;
import gameone.input.MouseManager;
import gameone.worlds.World;

public class Handler {
	private Game game;
	private World world, town, world2;
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

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	
	
}
