package com.decaflabs.trux;

import java.util.LinkedList;
import java.util.List;

public class Sun {
    
    public enum DayPart {
        DAY {
            public DayPart next() { return NIGHT; }
        },
        NIGHT {
            public DayPart next() { return DAY; }
        };
        public abstract DayPart next();
    };
    
    private DayPart state;
    private final String NAME;
    private final int DAY_INTERVAL;
    
    private List<Geo> orbits = new LinkedList<Geo>(); 
    
    public Sun(String name, int dayLength) {
        this.NAME = name;
        this.DAY_INTERVAL = dayLength;
        this.state = DayPart.DAY;
    }
    
    public boolean isShining() {
        return state == DayPart.DAY;
    }
    
    public String toString() {
        return NAME+":"+state;
    }
    
    public void registerOrbit(Geo geo) {
        this.orbits.add(geo);
        geo.notifyDayPartChanged(this.state);
    }
    
    private double elapsedTime = 0;
    public void mutate(double delta){
        elapsedTime += delta;
        if (elapsedTime >= DAY_INTERVAL) {
            elapsedTime = elapsedTime - DAY_INTERVAL;
            this.state = this.state.next();
            for (Geo iGeo : this.orbits) {
                iGeo.notifyDayPartChanged(this.state);
            }
        }
    }
}
