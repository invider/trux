package com.decaflabs.trux.site;

import com.decaflabs.trux.Geo;
import com.decaflabs.trux.platform.AbstractTrux;
import com.decaflabs.trux.platform.capsule.Capsule;

public abstract class AbstractSite implements Site {
	
	protected final static double DEFAULT_WIDTH = 40;
	
	protected Geo geo;
	
	protected int team;
	
	protected double x;
	
	protected double width = DEFAULT_WIDTH;
	
	@Override
	public int getTeam() {
		return team;
	}

	@Override
	public void setTeam(int team) {
		this.team = team;
	}
	
	@Override
	public void setGeo(Geo geo) {
		this.geo = geo;
	}

	@Override
	public String getType() {
		return "site";
	}

	@Override
	public String soilSample() {
		return this.getType();
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public double getWidth() {
		return width;
	}
	
	@Override
	public void mutate(double delta) {	
	}

	@Override
	public void touch(AbstractTrux trux) {		
	}

	@Override
	public void touch(Capsule capsule) {	
	}
	
	@Override
	public String getLabel() {
		return "site";
	}

	@Override
	public String toJSON() {
		StringBuilder buf = new StringBuilder("{");
		buf.append("  ").append("\n  \"type\": \"").append(this.getType())
			.append("\",\n  \"team\": \"").append(this.getTeam())
			.append("\",\n  \"label\": \"").append(this.getLabel())
			.append("\",\n  \"x\": \"").append(this.getX())
			.append("\",\n  \"w\": \"").append(this.getWidth())
			.append("\"\n}");
			
		return buf.toString();
	}

}