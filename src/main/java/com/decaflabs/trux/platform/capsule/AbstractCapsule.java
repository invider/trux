package com.decaflabs.trux.platform.capsule;

import com.decaflabs.trux.platform.AbstractPlatform;
import com.decaflabs.trux.platform.Platform;

public abstract class AbstractCapsule extends AbstractPlatform implements Capsule {
	
	protected static final double CLOUD_Y = 20;

	protected static final double SPEED = 5;
	
	protected Platform host = null;
	
	public AbstractCapsule() {
		this.y = CLOUD_Y;
	}
	
	@Override
	public String getType() {
		return "capsule";
	}
	
	@Override
	public boolean isCaptured() {
		return host != null;
	}

	@Override
	public Platform getHost() {
		return host;
	}
	
	@Override
	public void setHost(Platform platform) {
		this.host = platform;
	}
	
	@Override
	public void release() {
		this.host = null;
	}

	@Override
	public void mutate(double delta) {
		if (this.y > 0) {
			this.y -= SPEED * delta;
			if (this.y < 0) this.y = 0;
		}
	}

}
