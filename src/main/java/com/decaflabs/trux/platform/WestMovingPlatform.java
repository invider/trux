package com.decaflabs.trux.platform;

/**
 * Just a platform that always moves west at fixed speed
 */
public class WestMovingPlatform extends AbstractTrux {

	private static final double SPEED = 0.5d;
	
	public WestMovingPlatform(int team, double x) {
		super(team);
		super.setX(x);
		super.setY(0);
	}
	
	@Override
	public void mutate(double delta) {
		this.setX(this.getX() - this.SPEED * delta);
	}

	public double getSpeed(){
	    return SPEED;
	}
}
