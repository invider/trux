package com.decaflabs.trux.platform;

import java.util.LinkedList;
import java.util.List;

import com.decaflabs.trux.platform.capsule.AbstractCapsule;
import com.decaflabs.trux.platform.capsule.Capsule;
import com.decaflabs.trux.site.Site;
import com.decaflabs.trux.site.Surface;
import com.decaflabs.trux.site.UnknownSurface;

public abstract class AbstractTrux extends AbstractPlatform {
	
	public static final double TRUX_WIDTH = 0.5d;
	
	protected List<Capsule> capsules = new LinkedList<Capsule>();
	
	protected Capsule released = null;
	
	protected Surface over = UnknownSurface.getInstance();
	
	public AbstractTrux(int team) {
		super(team);
	}
	
	@Override
	public String getType() {
		return "trux";
	}
	
	@Override
	public void mutate(double delta) {
		for (Capsule capsule: this.capsules) {
			capsule.setX(this.x);
			capsule.setY(this.y);
		}
	}
	
	public void setOver(Surface surface) {
		this.over = surface;
	}
	
	public Surface getOver() {
		return this.over;
	}
	
	public void capture(Capsule capsule) {
		if (this.released == capsule) return; // ignore previously released capsule
		this.capsules.add(capsule);
		capsule.setHost(this);
	}
	
	public void release(Capsule capsule) {
		if (this.capsules.remove(capsule)) {
			this.released = capsule;
			capsule.release();
		}
	}
	
}
