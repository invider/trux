package com.decaflabs.trux;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.decaflabs.trux.platform.AbstractTrux;
import com.decaflabs.trux.platform.Platform;
import com.decaflabs.trux.platform.capsule.Capsule;
import com.decaflabs.trux.platform.capsule.AbstractCapsule;
import com.decaflabs.trux.platform.capsule.Battery;
import com.decaflabs.trux.site.Site;
import com.decaflabs.trux.site.Surface;

/**
 * Represents the planet surface with all objects
 */

public class Geo implements Surface {


	private static final int LINE_WIDTH = 120;
	private static final int EQUATOR_LENGTH = 100;
 	
 	private double length;
 	
 	private Set<Platform> platforms = new HashSet<Platform>();
	private Set<AbstractTrux> truxs = new HashSet<AbstractTrux>();
	private Set<Capsule> capsules = new HashSet<Capsule>();
	private Set<Site> sites = new HashSet<Site>();
	

	private static Geo instance = new Geo(EQUATOR_LENGTH);
	
	public static Geo getInstance(){
	    return instance;
	}
	
	private Geo(double length) {
 		this.length = length;
 	}
	
	public static boolean sunIsShining() {
        return instance.sunIsShining;
    }

    /**
     * Calculates the distance between trux and platform if trux will not change the direction
     */
    public static double directedDistanceBetween(AbstractTrux trux, Platform platform){
        double distance = platform.getX() - trux.getX();
        // comparing signums
        if (trux.getDirection() == Math.signum(distance)){
            distance = Math.abs(distance);
        } else {
            distance = Geo.EQUATOR_LENGTH - Math.abs(distance);
        }
        return distance;
    }
	

	public static final String GEO = "geo";

	private double length;

	private Rain rain;
	
	private Quake quake;
	
	private String status = "Updating Status...";

	private Set<Platform> platforms = new HashSet<Platform>();

	private Set<Site> sites = new HashSet<Site>();

	public Geo(double length) {
		this.length = length;
		this.rain = new Rain(length);
		this.quake = new Quake(length);
	}

	@Override
	public String soilSample() {
		return GEO;
	}


	protected double randomTag() {
		return Math.random() * this.length;
	}


	// drops platform from another platform
	//E.g.: Trux drops unneeded capsule
	public void drop(Platform platform){
		this.spawn(platform);
	}
	

	public void spawn(Platform platform) {

		this.platforms.add(platform);
		if (platform instanceof Capsule) {
			this.capsules.add((Capsule)platform);
		} else if (platform instanceof AbstractTrux){
			this.truxs.add((AbstractTrux)platform);
		}
	}
	
	public void spawn(Site site) {
		site.setGeo(this);
		this.sites.add(site);
	}

	public void kill(Platform platform) {
		this.platforms.remove(platform);
		this.capsules.remove(platform);
		this.truxs.remove(platform);
	}
	
	public void kill(Site site) {
		this.sites.remove(site);
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
	
	protected Site selectSite(double x) {
		for (Site site: this.sites) {
			if (site.getX() <= x && (site.getX() + site.getWidth()) >= x) {
				// site is under x
				return site;
			}
		}
		return null;
	}

	/**
	 * up
	 * 
	 * @param delta
	 */
	public void mutate(double delta) {

	    for (Platform p: this.platforms) {
			p.mutate(delta);
		}
		// planet borders correction
		for (Platform p: this.platforms) {
			if (p.getX() > this.length) { 
			    p.setX(p.getX() - this.length);
			} else if (p.getX() < 0) { 
			    p.setX(this.length + p.getX());
			}
		}
		// collision detection
		for (AbstractTrux iTrux : this.truxs){
		    for (Iterator<Capsule> capsIter = this.capsules.iterator(); capsIter.hasNext(); ){
		        Capsule capsule = capsIter.next();
		        if (iTrux.collisionWith(capsule)) {
		            iTrux.capture(capsule);
		            capsIter.remove();
		            platforms.remove(capsule);
		        }
		    }

		// detect surfaces
		for (Platform p : this.platforms) {
			Site site = selectSite(p.getX());
			if (p instanceof AbstractTrux) {
				AbstractTrux trux = (AbstractTrux) p;
				if (site == null) {
					// we are over geo
					trux.setOver(this);
				} else {
					// we are over some site
					Surface prevSurface = trux.getOver();
					if (prevSurface != site) {
						// just touched the site
						trux.setOver(site);
						site.touch(trux);
					}
				}
			} else if (p instanceof Capsule){
				Capsule c = (Capsule) p;
				if (site != null && c.getY() == 0 && c.getTeam() != 0 && !c.isCaptured()) {
					// touch only if capsule is dropped, captured and released by a trux
					site.touch(c);
				}
			}
		}
		
		// move platforms
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
						if (!capsule.isCaptured()
								&& capsule.getY() == 0
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
		Capsule newCapsule = this.rain.fall(delta);
		if (newCapsule != null) {
			this.spawn(newCapsule);
		}
		
		// quake
		Site newSite = this.quake.shake(delta);
		if (newSite != null) {
			this.spawn(newSite);
		}
	}

	/**
	 * get carriage return string to erase previous Geo print
	 */
	public String erase() {
		return App.SET_CURSOR_POSITION;
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
		buf.append("\n],\n");

		int j = 0;
		buf.append("\"sites\": [\n");
		for (Site site : this.sites) {
			if (j != 0) buf.append(",\n");
			buf.append(site.toJSON());
			++j;
		}
		buf.append("\n]\n");

		buf.append("}");
		return buf.toString();
	}

	/**
	 * get Geo string representation
	 */
	public String toString() {
		//graphics output
	    StringBuilder buf = new StringBuilder("");
		buf.append(App.FONT_BOLD);
		if (this.sunIsShining) {
		    buf.append(App.COLOR_BACKGROUND_WHITE);
        } else {
            buf.append(App.COLOR_BACKGROUND_BLACK);
        }
		double step = this.length / this.LINE_WIDTH;
		double x = 0;
		while (x < this.length) {
			List<Platform> platformList = this.select(x, x + step);
			if (platformList.size() > 0) {
				// represent as a team number of the first element
				Platform platform = platformList.get(0);
				if (platform.getY() == 0) {
					int team = platform.getTeam();

					if (team == 0) {
					    buf.append(App.COLOR_BLUE);
					    buf.append('#');
					} else {
					    buf.append(App.COLOR_RED);
					    buf.append(team);
					}

					if (team == 0)
						buf.append('X');
					else
						buf.append("" + team);

				}
			} else {
			    buf.append(App.COLOR_GREEN);
			    buf.append("_");
			}
			x += step;
		}
		
		//information in text form
		buf.append("\n\n");
		buf.append(App.COLOR_BACKGROUND_BLACK);
		buf.append(App.COLOR_WHITE);
		buf.append(App.FONT_DIM);
		buf.append(App.ERASE_DOWN);
		buf.append("\n\nGeo.state:\n  ");
		buf.append(this.sunIsShining ? "DAY" : "NIGHT");
		
		StringBuffer truxs = new StringBuffer("\n\nGeo.Truxs");
		truxs.append(" ["+this.truxs.size()+"]:");
		for (AbstractTrux t : this.truxs){
	        truxs.append("\n  ");
	        truxs.append(t.toString());
		}		
		StringBuffer capsules = new StringBuffer("\n\nGeo.Capsules");
		capsules.append(" ["+this.capsules.size()+"]:");
		for (Capsule c : this.capsules){
	        capsules.append("\n  ");
	        capsules.append(c.toString());
		}
		
		buf.append(truxs);
		buf.append(capsules);
		return buf.toString();
	}
	
	public void notifyDayPartChanged(Sun.DayPart state) {
	    this.sunIsShining = state == Sun.DayPart.DAY;
	}
	
}
