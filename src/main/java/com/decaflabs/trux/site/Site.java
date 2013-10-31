package com.decaflabs.trux.site;

import com.decaflabs.trux.platform.AbstractTrux;

public interface Site {
	
	public int getTeam();
	
	public void setTeam(int team);
	
	public String getType();
	
	public double getX();
	public void setX(double x);
	
	public double getWidth();
	
	public void touch(AbstractTrux trux);
	
	public void mutate(double delta);
	
	public String toJSON();

}
