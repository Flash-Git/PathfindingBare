package dev.flash.pathfinding.tiles;

import dev.flash.pathfinding.gfx.Assets;

public class WallTile extends Tile {
	
	public WallTile(int id) {
		super(Assets.wall1, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
	public String getName() {
		return "Wall";
	}
	
}
