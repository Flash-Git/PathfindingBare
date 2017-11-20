package dev.flash.pathfinding.worlds;

import com.sun.javafx.geom.Vec2d;
import dev.flash.pathfinding.Handler;
import dev.flash.pathfinding.entities.Entity;
import dev.flash.pathfinding.entities.EntityManager;
import dev.flash.pathfinding.tiles.Tile;
import dev.flash.pathfinding.tiles.TileManager;
import dev.flash.pathfinding.timers.TimerManager;
import dev.flash.pathfinding.utils.Utils;

import java.awt.*;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	
	private int[][] tiles;
	
	//Managers
	
	private TileManager tileManager;
	private TimerManager timerManager;
	private EntityManager entityManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		handler.setWorld(this);
		tileManager = new TileManager(handler);
		entityManager = new EntityManager(handler);
		timerManager = handler.getTimerManager();
		
		//	loading=true;
		loadWorld(path);
		//	loading=false;
	}
	
	//Loads a world based on text file
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		tiles = new int[width][height];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
				Tile t = getTile(x, y);
				t.setX(x);
				t.setY(y);
				tileManager.addTile(t);
				System.out.println(t.getName() + " " + t.getX() + " " + t.getY());
			}
		}
		System.out.println(tileManager.getTiles().size() + " tiles on map");
		placeEntities();
		System.out.println("Map Successfully loaded.");
	}
	
	private void placeEntities(){
		entityManager.addEntity(new Entity(handler, new Vec2d(4*32,4*32), 5));
	}
	
	public void tick(double delta) {
		timerManager.tick(delta);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {
			return Tile.grassTile;
		}
		return t;
	}
	
	public void render(Graphics g) {
		//render only the tiles on the screen
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		entityManager.render(g);
	}
	
	//Getters and setters
	
	public TileManager getTileManager() {
		return tileManager;
	}
	
	public int getSpawnX() {
		return spawnX;
	}
	
	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}
	
	public int getSpawnY() {
		return spawnY;
	}
	
	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
