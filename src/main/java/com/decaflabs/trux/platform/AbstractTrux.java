package com.decaflabs.trux.platform;

import java.util.LinkedList;
import java.util.List;

import com.decaflabs.trux.platform.capsule.AbstractCapsule;
import com.decaflabs.trux.platform.capsule.Capsule;

public abstract class AbstractTrux extends AbstractPlatform {
	
	protected List<Capsule> capsules = new LinkedList<Capsule>();
	
	@Override
	public void mutate(double delta) {
		for (Capsule capsule: this.capsules) {
			capsule.setX(this.x);
			capsule.setY(this.y);
		}
	}
	
	public void capture(AbstractCapsule capsule) {
		this.capsules.add(capsule);
	}
	
	public void release(AbstractCapsule capsule) {
		if (this.capsules.remove(capsule)) {
			capsule.release();
		}
	}
	
}
