package dev.flash.pathfinding.tiles;

import dev.flash.pathfinding.gfx.Assets;

public class SandTile extends Tile {
	
	public SandTile(int id) {
		super(Assets.grass1, id);
		weight = 1.2f;
		
	}
	
	public String getName() {
		return "Sand";
	}
	
	
}
