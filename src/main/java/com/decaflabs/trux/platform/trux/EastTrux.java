package com.decaflabs.trux.platform.trux;

import com.decaflabs.trux.platform.AbstractTrux;
import com.decaflabs.trux.platform.capsule.Capsule;

public class EastTrux extends AbstractTrux {

	private static final int TEAM = 7;
	private static final double SPEED = 3.0d;
	
	
	public EastTrux() {
		super(TEAM);
		
	}
	
	@Override
	public void mutate(double delta) {
		this.setX(this.getX() - this.SPEED * delta);
		
		for (Capsule capsule: this.capsules) {
			capsule.setX(this.x);
//			capsule.setY(this.y);
		}
	}

}
