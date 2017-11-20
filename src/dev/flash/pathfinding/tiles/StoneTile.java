package dev.flash.pathfinding.tiles;

import dev.flash.pathfinding.gfx.Assets;

public class StoneTile extends Tile {
	
	public StoneTile(int id) {
		super(Assets.stone1, id);
		weight = 0.7f;
	}
	
	public String getName() {
		return "Stone";
	}
	
}
