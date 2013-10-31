package com.decaflabs.trux;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.decaflabs.trux.platform.AbstractTrux;
import com.decaflabs.trux.platform.Platform;
import com.decaflabs.trux.platform.capsule.Capsule;
import com.decaflabs.trux.site.Site;

/**
 * Represents the planet surface with all objects
 */
public class Geo {

	private static final int LINE_WIDTH = 120;

	private double length;

	private Rain rain;
	
	private String status = "Updating Status...";

	private Set<Platform> platforms = new HashSet<Platform>();

	private Set<Site> sites = new HashSet<Site>();

	public Geo(double length) {
		this.length = length;
		this.rain = new Rain(length);
	}

	protected double randomTag() {
		return Math.random() * this.length;
	}

	protected void spawn(Platform platform) {
		this.platforms.add(platform);
	}

	protected void kill(Platform platform) {
		this.platforms.remove(platform);
	}

	/**
	 * select all platforms within range x1..x2
	 */
	protected List<Platform> select(double x1, double x2) {
		List<Platform> res = new LinkedList<Platform>();
		for (Platform p : this.platforms) {
			if (p instanceof Capsule && ((Capsule) p).isCaptured())
				continue;
			if (p.getX() >= x1 && p.getX() < x2)
				res.add(p);
		}
		return res;
	}

	/**
	 * up
	 * 
	 * @param delta
	 */
	public void mutate(double delta) {
		for (Platform p : this.platforms) {
			p.mutate(delta);
		}
		// planet borders correction
		for (Platform p : this.platforms) {
			if (p.getX() > this.length)
				p.setX(0);
			else if (p.getX() < 0)
				p.setX(this.length);
		}
		// collision detection
		for (Platform t : this.platforms) {
			if (t instanceof AbstractTrux) {
				AbstractTrux trux = (AbstractTrux) t;
				for (Platform c : this.platforms) {
					if (c instanceof Capsule) {
						Capsule capsule = (Capsule) c;
						if (capsule.getY() == 0
								&& trux.getX() - AbstractTrux.TRUX_WIDTH <= capsule
										.getX()
								&& trux.getX() + AbstractTrux.TRUX_WIDTH >= capsule
										.getX()) {
							// collision!
							trux.capture(capsule);
						}
					}
				}
			}
		}

		// capsule rain
		Capsule newCapsule = this.rain.rain(delta);
		if (newCapsule != null) {
			this.spawn(newCapsule);
		}
	}

	/**
	 * get carriage return string to erase previous Geo print
	 */
	public String erase() {
		return "\r";
	}

	public String toJSON() {
		StringBuilder buf = new StringBuilder("");
		buf.append("{\n");
		
		buf.append("\"length\": \"").append(this.length).append("\",\n");
		buf.append("\"status\": \"").append(this.status).append("\",\n");
		
		int i = 0;
		buf.append("\"platforms\": [\n");
		for (Platform platform : this.platforms) {
			if (platform instanceof Capsule
					&& ((Capsule) platform).isCaptured())
				continue;
			
			if (i != 0) buf.append(",\n");
			buf.append(platform.toJSON());
			++i;
		}
		buf.append("\n]\n");

		int j = 0;
		for (Site site : this.sites) {
			// print site json representation
		}

		buf.append("}");
		return buf.toString();
	}

	/**
	 * get Geo string representation
	 */
	public String toString() {
		StringBuilder buf = new StringBuilder("");
		double step = this.length / this.LINE_WIDTH;
		double x = 0;
		while (x < this.length) {
			List<Platform> platformList = this.select(x, x + step);
			if (platformList.size() > 0) {
				// represent as a team number of the first element
				Platform platform = platformList.get(0);
				if (platform.getY() == 0) {
					int team = platform.getTeam();
					if (team == 0)
						buf.append('X');
					else
						buf.append("" + team);
				}
			} else {
				buf.append('_');
			}
			x += step;
		}
		return buf.toString();
	}
}
