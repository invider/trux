package com.decaflabs.trux.site;

import com.decaflabs.trux.Geo;
import com.decaflabs.trux.platform.AbstractTrux;
import com.decaflabs.trux.platform.capsule.Capsule;

public interface Site extends Surface {
	
	public int getTeam();
	
	public void setTeam(int team);
	
	public void setGeo(Geo geo);
	
	public String getType();
	
	public double getX();
	public void setX(double x);
	
	public double getWidth();
	
	/**
	 * This event is triggered once when a trux drives over the site
	 */
	public void touch(AbstractTrux trux);
	
	/**
	 * This event is triggered all the time while capsule is over the site
	 */
	public void touch(Capsule capsule);
	
	public void mutate(double delta);
	
	public String getLabel();
	
	public String toJSON();

}
