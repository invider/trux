package com.decaflabs.trux.platform;

import com.decaflabs.trux.platform.capsule.Capsule;
import com.decaflabs.trux.platform.capsule.SmartDustCapsule;
import com.decaflabs.trux.site.ConstructionSite;

/**
 * Just a platform that always moves west at fixed speed
 */
public class WestMovingPlatform extends AbstractTrux {

	private static final double SPEED = 5d;
	
	public WestMovingPlatform(int team, double x) {
		super(team);
		super.setX(x);
		super.setY(0);
	}
	
	@Override
	public void mutate(double delta) {
		this.setX(this.getX() - this.SPEED * delta);
		
		// take a soil sample
		String soil = this.over.soilSample();
		if (soil.equals(ConstructionSite.CONSTRUCTION)) {
			// we are over a construction site
			// look for a smart dust capsule
			Capsule smartDust = null;
			for (Capsule capsule: this.capsules) {
				if (capsule instanceof SmartDustCapsule) {
					smartDust = capsule;
				}
			}
			if (smartDust != null) {
				// found, drop it!
				this.release(smartDust);
			}
		}
	}

}
