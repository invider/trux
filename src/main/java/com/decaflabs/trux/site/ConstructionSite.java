package com.decaflabs.trux.site;

import com.decaflabs.trux.TruxException;
import com.decaflabs.trux.platform.capsule.Capsule;
import com.decaflabs.trux.platform.capsule.SmartDustCapsule;

public class ConstructionSite extends AbstractSite {
	
	public static final String CONSTRUCTION = "construction";
	
	public static final String BUILDING = "building";
	
	private int upgrade = 0;

	public ConstructionSite() {}
	
	@Override
	public int getTeam() {
		// construction site is owned by nobody
		return 0;
	}

	@Override
	public void setTeam(int team) {
		throw new TruxException("Can't set team to a construction site");
	}

	@Override
	public String getType() {
		if (upgrade == 0) return CONSTRUCTION;
		else return BUILDING;
	}

	@Override
	public void touch(Capsule capsule) {
		// capsule is over
		if (capsule instanceof SmartDustCapsule) {
			// build !!!
			this.upgrade++;
			this.geo.kill(capsule);
		}
	}

	@Override
	public String getLabel() {
		return "waiting for smart dust...";
	}
	
}
