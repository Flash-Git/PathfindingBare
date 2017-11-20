package dev.flash.pathfinding.entities;

import dev.flash.pathfinding.Handler;
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
	protected float x, y;
	protected float relativeX, relativeY;//x and y only change at end of tick
	protected double vX, vY;
	protected Rectangle bounds;
	
	
	//Entity's timers
	protected ArrayList<Timer> timers = new ArrayList<Timer>();
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0, 0, width, height);
	}
	
	
	protected void addTimers() {
	}
	
	
	//TICK RENDER ACCEPT
	
	public void tick(double delta) {
		x += relativeX;
		y += relativeY;
	}
	
	public abstract void render(Graphics g);
	
	
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
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
}
