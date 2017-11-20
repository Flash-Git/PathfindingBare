package dev.flash.pathfinding.entities;

import dev.flash.pathfinding.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityManager {
	
	private Handler handler;
	private ArrayList<Entity> entities;
	private ArrayList<Entity> toAdd;
	private ArrayList<Entity> toRemove;
	
	//Sorts entities by their vertical position so they can get rendered properly TODO doesn't always work for some reason
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		@Override
		public int compare(Entity a, Entity b) {
			if(a.getCoord().x + a.getHeight() < b.getCoord().y + b.getHeight()) {
				return -1;
			} else if(a.getCoord().x + a.getHeight() < b.getCoord().y + b.getHeight()) {
				return +1;
			} else {
				return 0;
			}
		}
	};
	
	public EntityManager(Handler handler) {
		this.handler = handler;
		entities = new ArrayList<>();
		toAdd = new ArrayList<>();
		toRemove = new ArrayList<>();
	}
	
	public void tick(double delta) {
		for(Entity e:entities){
			e.tick(delta);
		}
	}
	
	public void render(Graphics g) {
		entities.sort(renderSorter);//TODO is adding this here best option
		float xCamOffset = handler.getGameCamera().getxOffset();
		float yCamOffset = handler.getGameCamera().getyOffset();
		int windowWidth = handler.getWidth();
		int windowHeight = handler.getHeight();
		
		for(Entity e : entities) {
			if(e.getCoord().x < xCamOffset - 64) {
				continue;
			}
			if(e.getCoord().x > windowWidth + xCamOffset + 64) {
				continue;
			}
			if(e.getCoord().y < yCamOffset - 64) {
				continue;
			}
			if(e.getCoord().y > windowHeight + yCamOffset + 64) {
				continue;
			}
			e.render(g);
		}
	}
}
