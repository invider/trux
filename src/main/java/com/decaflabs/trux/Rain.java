package com.decaflabs.trux;

import com.decaflabs.trux.platform.capsule.Capsule;
import com.decaflabs.trux.platform.capsule.SampleCapsule;

public class Rain {

	private static final int CAPSULES = 1;
	
	private static final double PROBABILITY = 0.1;
	
	private double length = 0;

	public Rain(double length) {
		this.length = length;
		// disable scan for now
		/*
		 * Reflections reflections = new Reflections("my.project.prefix");
		 * 
		 * Set<Class<? extends SomeType>> subTypes =
		 * reflections.getSubTypesOf(SomeType.class);
		 */
	}

	public Capsule rain(double delta) {
		if (Math.random() < (PROBABILITY * delta)) {
			return this.createCapsule();			
		}
		return null;
	}

	private Capsule createCapsule() {
		int i = (int) (Math.random() * CAPSULES);
		switch (i) {
		case 0:
			return new SampleCapsule(place());
			// case 1:

		default:
			return new SampleCapsule(place());
		}
	}
	
	private double place() {
		return Math.random() * this.length;
	}

}
