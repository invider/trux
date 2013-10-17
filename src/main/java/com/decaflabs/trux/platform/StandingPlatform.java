package com.decaflabs.trux.platform;

public class StandingPlatform extends Platform {
	
	public StandingPlatform(int team, double x) {
		super(team);
		super.setX(x);
		super.setY(0);
	}

	@Override
	public void mutate(double delta) {
		// do nothing - standing platform is not mutating
	}

}
