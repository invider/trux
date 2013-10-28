package com.decaflabs.trux.site;

import com.decaflabs.trux.TruxException;

public class ConstructionSite extends AbstractSite {

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

}
