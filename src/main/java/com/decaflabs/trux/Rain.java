package com.decaflabs.trux;

import java.util.Iterator;
import java.util.Set;


import org.reflections.Reflections;

import com.decaflabs.trux.platform.capsule.AbstractCapsule;
import com.decaflabs.trux.platform.capsule.Capsule;
public class Rain {

	private static final double PROBABILITY = 0.1;

	private double length = 0;

	Set<Class<? extends AbstractCapsule>> capsuleTypeSet;
	Class<? extends AbstractCapsule> capsuleTypes[];
	

	public Rain(double length) {
		this.length = length;
		Reflections reflections = new Reflections("com");
		capsuleTypeSet = reflections.getSubTypesOf(AbstractCapsule.class);
		Iterator<Class <? extends AbstractCapsule>> iter = capsuleTypeSet.iterator();
		System.out.println("=========================");
		System.out.println("Today is raining:");
		System.out.println("=========================");
		while (iter.hasNext()) {
			System.out.println("capsule: " + iter.next().getSimpleName());
		}
		capsuleTypes = capsuleTypeSet.toArray(new Class[capsuleTypeSet.size()]);
		System.out.println("=========================");
	}

	public Capsule rain(double delta) {
		if (Math.random() < (PROBABILITY * delta)) {
			return this.createCapsule();
		}
		return null;
	}

	private Capsule createCapsule() {
		int i = (int) (Math.random() * this.capsuleTypes.length);
		Class<? extends Capsule> capsuleType = this.capsuleTypes[i];
		try {
			Capsule capsule = capsuleType.newInstance();
			capsule.setX(place());
			return capsule;
		} catch (InstantiationException e) {
			throw new TruxException("Can't create capsule " + capsuleType.getSimpleName());
		} catch (IllegalAccessException e) {
			throw new TruxException("Can't create capsule " + capsuleType.getSimpleName());
		}
	}

	private double place() {
		return Math.random() * this.length;
	}

}
