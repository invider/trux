package com.decaflabs.trux.platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.decaflabs.trux.Geo;
import com.decaflabs.trux.platform.capsule.Capsule;
import com.decaflabs.trux.site.Site;
import com.decaflabs.trux.site.Surface;
import com.decaflabs.trux.site.UnknownSurface;

public abstract class AbstractTrux extends AbstractPlatform {
	
	public static final double TRUX_WIDTH = 0.5d;
	
	protected List<Capsule> capsules = new ArrayList<Capsule>();
	
	public enum Direction {
        LEFT {
            public int factor() {
                return -1;
            }
        },
        RIGHT {
            public int factor() {
                return 1;
            }
        };
        public abstract int factor();
    };
    protected Direction direction;
	
	private class Distance {
	    double distance;
	    public Distance(double d){
	        this.distance=d;
	    }
    }
	
	private Map<Platform, Distance> prevDistanceTo = new HashMap<Platform, Distance>();
	
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
			Geo.getInstance().drop(capsule);
		}
	}
	
	
	
	public boolean collisionWith(Platform platform) {
	    double currDistance = Geo.directedDistanceBetween(this, platform);
	    Distance prev = prevDistanceTo.get(platform);
	    if (prev != null) {
	        double tt = prev.distance;
	        prev.distance = currDistance;
    	    return currDistance > tt;  //This means that trux passed the platform
	    } else {
	        prevDistanceTo.put(platform, new Distance(currDistance));
	        return false;
	    }
	}
	
	public int getDirection() {
	    return this.direction.factor();
	}
	
	public String toString() {
	    StringBuffer buf = new StringBuffer();
	    buf.append(this.getClass().getSimpleName());
	    buf.append("; team=");
        buf.append(this.getTeam());
        buf.append("; ");
        buf.append(this.getDirection()==1 ? "RIGHT" : "LEFT");
        buf.append(String.format("; pos=(%1$.1f, %2$.1f)",this.getX(), this.getY()));
        buf.append("; V=");
        buf.append(String.format("%.1f", this.getSpeed()));
        return buf.toString();
	}
	
	
	public abstract double getSpeed();
	
	
}
