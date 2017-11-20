package dev.flash.pathfinding.tiles;

import dev.flash.pathfinding.gfx.Assets;

public class GrassTile extends Tile {
	
	public GrassTile(int id) {
		super(Assets.grass1, id);
		weight = 1;
		
	}
	
	public String getName() {
		return "Grass";
	}
	
	
}
