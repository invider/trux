package com.decaflabs.trux;

import com.decaflabs.trux.platform.WestMovingPlatform;
import com.decaflabs.trux.platform.capsule.AbstractCapsule;

public class GeoSample extends Geo {

	public GeoSample() {
		super(1000.0d);
		this.spawn(new WestMovingPlatform(1, this.randomTag()));
		this.spawn(new WestMovingPlatform(2, this.randomTag()));
	}
}
