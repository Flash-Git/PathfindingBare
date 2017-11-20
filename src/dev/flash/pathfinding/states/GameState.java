package dev.flash.pathfinding.states;

import dev.flash.pathfinding.Handler;
import dev.flash.pathfinding.worlds.World;

import java.awt.*;

public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		//inputManager = null;
		world = new World(handler, "res/worlds/world3.txt");
		handler.setWorld(world);
	}
	
	@Override
	public void tick(double delta) {
		world.tick(delta);
		
	}
	
	@Override
	public void render(Graphics g) {
		world.render(g);
	}
	
}