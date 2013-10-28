package com.decaflabs.trux.site;

import com.decaflabs.trux.platform.AbstractTrux;

public interface Site {
	
	public int getTeam();
	
	public void setTeam(int team);
	
	public void touch(AbstractTrux trux);

}
