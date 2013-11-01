package com.decaflabs.trux;

import com.decaflabs.trux.platform.StandingPlatform;
import com.decaflabs.trux.platform.WestMovingPlatform;
import com.decaflabs.trux.platform.capsule.AbstractCapsule;
import com.decaflabs.trux.platform.capsule.SampleCapsule;
import com.decaflabs.trux.platform.trux.RedTrux;

public class GeoSample extends Geo {

	public GeoSample() {
		super(1000.0d);
		//this.spawn(new StandingPlatform(1, this.randomTag()));
		//this.spawn(new WestMovingPlatform(1, this.randomTag()));
		//this.spawn(new StandingPlatform(2, this.randomTag()));
		//this.spawn(new WestMovingPlatform(2, this.randomTag()));
		this.spawn(new RedTrux(this.randomTag(), -1));
		this.spawn(new RedTrux(this.randomTag(), 1));
	}
}
