package com.decaflabs.trux.platform;

/**
 * Represents any movable and stationary object
 */
public abstract class AbstractPlatform implements Platform {
	
	protected int team = 0;
	
	protected double x, y;
	
	public AbstractPlatform() {}
	
	public AbstractPlatform(int team) {
		this.team = team;
	}
	
	public int getTeam() {
		return team;
	}
	
	public String getType() {
		return "platform";
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
	
	@Override
	public String getLabel() {
		return "";
	}
	
	@Override
	public String toJSON() {
		StringBuilder buf = new StringBuilder("{");
		buf.append("  ").append("\n  \"type\": \"").append(this.getType())
			.append("\",\n  \"team\": \"").append(this.getTeam())
			.append("\",\n  \"label\": \"").append(this.getLabel())
			.append("\",\n  \"x\": \"").append(this.getX())
			.append("\",\n  \"y\": \"").append(this.getY())
			.append("\"\n}");
			
		return buf.toString();
	}
		
}
