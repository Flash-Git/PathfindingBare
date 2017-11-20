package dev.flash.pathfinding.entities;

import com.sun.javafx.geom.Vec2d;
import dev.flash.pathfinding.Handler;
import dev.flash.pathfinding.gfx.Assets;
import dev.flash.pathfinding.timers.Timer;

import java.awt.*;
import java.util.ArrayList;

public abstract class Entity {
	
	//DEFAULT ENTITY VARIABLES
	protected static final int DEFAULT_WIDTH = 32;
	protected static final int DEFAULT_HEIGHT = 32;
	
	
	//GLOBAL ENTITY VARIABLES
	protected Handler handler;
	protected int width, height;
	
	//MOVEMENT
	protected Vec2d coord;
	protected Vec2d vel;
	protected float speed;
	protected Rectangle bounds;
	
	//Entity's timers
	protected ArrayList<Timer> timers = new ArrayList<>();
	
	public Entity(Handler handler, Vec2d coord, float speed, int width, int height) {
		this.handler = handler;
		this.coord = coord;
		this.speed = speed;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0, 0, width, height);
	}
	
	
	//TICK RENDER ACCEPT
	
	public void tick(double delta) {
		coord.x = +vel.x;
		coord.y = +vel.y;
		
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.sicky_up[0], (int) (coord.x - handler.getGameCamera().getxOffset()), (int) (coord.y - handler.getGameCamera().getyOffset()), width, height, null);
		
	}
	
	
	//Getters and Setters
	
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Vec2d getCoord() {
		return coord;
	}
	
	public void setCoord(Vec2d coord) {
		this.coord = coord;
	}
	
	public Vec2d getVel() {
		return vel;
	}
	
	public void setVel(Vec2d vel) {
		this.vel = vel;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public ArrayList<Timer> getTimers() {
		return timers;
	}
	
	public void setTimers(ArrayList<Timer> timers) {
		this.timers = timers;
	}
}
