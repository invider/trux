package com.decaflabs.trux.platform.capsule;

import com.decaflabs.trux.platform.AbstractPlatform;

public abstract class AbstractCapsule extends AbstractPlatform {
	
	protected static final double CLOUD_Y = 20;

	protected static final double SPEED = 5;
	
	public AbstractCapsule() {
		this.y = CLOUD_Y;
	}
	
	@Override
	public void mutate(double delta) {
		if (this.y > 0) {
			this.y -= SPEED * delta;
			if (this.y < 0) this.y = 0;
		}
	}

}
