package com.decaflabs.trux.platform.capsule;

import com.decaflabs.trux.platform.Platform;

public interface Capsule extends Platform {
	
	public boolean isCaptured();
	
	public Platform getHost();
	
	public void setHost(Platform platform);
	
	public void release();
	
}
