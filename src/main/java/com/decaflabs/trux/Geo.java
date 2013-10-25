package com.decaflabs.trux;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.decaflabs.trux.platform.Platform;
import com.decaflabs.trux.platform.capsule.Capsule;

/**
 * Represents the planet surface with all objects
 */
public class Geo {
	
	private static final int LINE_WIDTH = 120;
	
	private double length;
	
	private Set<Platform> platforms = new HashSet<Platform>();
	
	public Geo(double length) {
		this.length = length;
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
		for(Platform p: this.platforms) {
			if (p instanceof Capsule && ((Capsule)p).isCaptured()) continue;			
			if (p.getX() >= x1 && p.getX() < x2) res.add(p);
		}
		return res;
	}
	
	/**
	 * up
	 * @param delta
	 */
	public void mutate(double delta) {
		for (Platform p: this.platforms) {
			p.mutate(delta);
		}
		// planet borders correction
		for (Platform p: this.platforms) {
			if (p.getX() > this.length) p.setX(0);
			else if (p.getX() < 0) p.setX(this.length);
		}
	}
	
	/**
	 * get carriage return string to erase previous Geo print
	 */
	public String erase() {
		return "\r";
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
					if (team == 0) buf.append('X');
					else buf.append("" + team);
				}
			} else {
				buf.append('_');
			}
			x += step;
		}
		return buf.toString();
	}
}
