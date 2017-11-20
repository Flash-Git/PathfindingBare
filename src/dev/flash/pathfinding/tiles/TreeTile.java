package dev.flash.pathfinding.tiles;

import dev.flash.pathfinding.gfx.Assets;

public class TreeTile extends Tile {
	
	public TreeTile(int id) {
		super(Assets.wall2, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
	public String getName() {
		return "Stone";
	}
	
	
}
