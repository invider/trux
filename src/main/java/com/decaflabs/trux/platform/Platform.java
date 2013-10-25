package com.decaflabs.trux.platform;

public interface Platform {
	
	public int getTeam();
	
	public double getX();
	public void setX(double x);
	
	public double getY();
	public void setY(double y);
	
	public void mutate(double d);
	
}
