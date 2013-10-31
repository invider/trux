package com.decaflabs.trux;

import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;

import com.decaflabs.trux.platform.capsule.Capsule;
import com.decaflabs.trux.site.AbstractSite;
import com.decaflabs.trux.site.Site;

public class Quake {
	
	private static final double PROBABILITY = 0.01;

	private double length = 0;
	
	Set<Class<? extends AbstractSite>> siteTypeSet;
	Class<? extends AbstractSite> siteTypes[];
	

	public Quake(double length) {
		this.length = length;
		Reflections reflections = new Reflections("com");
		siteTypeSet = reflections.getSubTypesOf(AbstractSite.class);
		Iterator<Class <? extends AbstractSite>> iter = siteTypeSet.iterator();
		System.out.println("=========================");
		System.out.println("Today is shaking:");
		System.out.println("-------------------------");
		while (iter.hasNext()) {
			System.out.println("site: " + iter.next().getSimpleName());
		}
		siteTypes = siteTypeSet.toArray(new Class[siteTypeSet.size()]);
		System.out.println("=========================");
	}
	
	public Site shake(double delta) {
		if (Math.random() < (PROBABILITY * delta)) {
			return this.createSite();
		}
		return null;
	}
	
	/**
	 * TODO add site collision recognizer
	 */
	private Site createSite() {
		int i = (int) (Math.random() * this.siteTypes.length);
		Class<? extends Site> siteType = this.siteTypes[i];
		try {
			Site site = siteType.newInstance();
			site.setX(place());
			return site;
		} catch (InstantiationException e) {
			throw new TruxException("Can't create capsule " + siteType.getSimpleName());
		} catch (IllegalAccessException e) {
			throw new TruxException("Can't create capsule " + siteType.getSimpleName());
		}
	}
	
	private double place() {
		return Math.random() * this.length;
	}

}
