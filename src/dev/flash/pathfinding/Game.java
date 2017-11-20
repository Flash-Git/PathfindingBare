package dev.flash.pathfinding;

import dev.flash.pathfinding.display.Display;
import dev.flash.pathfinding.gfx.Assets;
import dev.flash.pathfinding.gfx.GameCamera;
import dev.flash.pathfinding.states.GameState;
import dev.flash.pathfinding.states.State;
import dev.flash.pathfinding.timers.TimerManager;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game implements Runnable {
	
	//display handles JFrame
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	
	//used for fps counter
	private int fps;
	
	//total number of ticks
	public static int tickCount;
	
	//game variables
	private int width, height;
	private String title;
	private boolean running;
	private Thread thread;
	
	private TimerManager timerManager;
	
	//States
	private State gameState;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		timerManager = new TimerManager();
		
	}
	
	private void init() {
		handler = new Handler(this);
		
		//Create window
		display = new Display(title, width, height);
		
		//Load the game's assets
		Assets.init();
		
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
	}
	
	private void tick(double delta) {
		if(State.getState() != null) {
			State.getState().tick(delta);
		}
	}
	
	private void render() {
		//Draw frames before displaying them
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(2);//amount of stored up frames ready before pushing to screen
			return;
		}
		g = bs.getDrawGraphics();
		
		//Clear Screen
		g.clearRect(0, 0, width, height);
		
		//Draw Here
		gameState.render(g);
		
		//End Draw
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		init();
		int targetFps = 144;
		double timePerTick = 1000000000 / targetFps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		long deltaTime;//better to have this be a long that converts to int or pass a long into tick()?
		long deltaLastTime = System.nanoTime();
		long deltaNow;
		
		
		while(running) {
			now = System.nanoTime();
			timer += now - lastTime;
			delta += (now - lastTime) / timePerTick;
			
			lastTime = now;
			
			if(delta >= 1) {//this should avoid lost or gained frames from speeding up or slowing down the game
				deltaNow = System.nanoTime();
				deltaTime = deltaNow - deltaLastTime;
				
				tick(deltaTime / 1000000);//converts nano to milli
				render();
				ticks++;
				delta--;
				tickCount++;
				deltaLastTime = deltaNow;
			}
			
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				fps = ticks;
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	//Creates the thread
	public synchronized void start() {
		if(running == true) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//Stops the code cleanly
	public synchronized void stop() {
		if(!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Getters and Setters
	
	
	public int getFPS() {
		return fps;
	}
	
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public TimerManager getTimerManager() {
		return timerManager;
	}
	
	public int getWidth() {
		return display.getCanvas().getWidth();
	}
	
	public int getHeight() {
		return display.getCanvas().getHeight();
	}
	
}
