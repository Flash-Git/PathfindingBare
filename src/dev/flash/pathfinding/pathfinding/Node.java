package dev.flash.pathfinding.pathfinding;

public class Node {
	
	public int x, y, w, h;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Node(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
}
