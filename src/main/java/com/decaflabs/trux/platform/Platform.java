package com.decaflabs.trux.platform;

/**
 * Represents any movable and stationary object
 */
public abstract class Platform {
	
	protected int team = 0;
	
	protected double x, y;
	
	public Platform() {}
	
	public Platform(int team) {
		this.team = team;
	}
	
	public int getTeam() {
		return team;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public abstract void mutate(double delta); 
		
}
