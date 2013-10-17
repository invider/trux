package com.decaflabs.trux;

import com.decaflabs.trux.platform.StandingPlatform;
import com.decaflabs.trux.platform.WestMovingPlatform;

public class GeoSample extends Geo {

	public GeoSample() {
		super(100.0d);
		this.spawn(new StandingPlatform(0, this.randomTag()));
		this.spawn(new StandingPlatform(1, this.randomTag()));
		this.spawn(new WestMovingPlatform(2, this.randomTag()));
		this.spawn(new StandingPlatform(3, this.randomTag()));
		this.spawn(new WestMovingPlatform(4, this.randomTag()));
	}
}
