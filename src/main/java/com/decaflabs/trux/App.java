package com.decaflabs.trux;

import com.decaflabs.trux.platform.AbstractTrux.Direction;
import com.decaflabs.trux.platform.SolarTrux;
import com.decaflabs.trux.platform.capsule.LiPolCapsule;

/**
 * Hello world!
 *
 */
public class App 
{
    //terminal text handling
    static final char ESC = (char)27;
    static final String ERASE_LINE = ESC + "[2K";
    static final String ERASE_DOWN = ESC + "[J";
    static final String SET_CURSOR_POSITION = ESC + "[10;0H"; //row;column
    static final String CLEAR_SCREEN = ESC + "[2J";
    static final String FONT_BOLD = ESC + "[1m";
    static final String FONT_DIM = ESC + "[2m";
    static final String COLOR_BACKGROUND_BLACK = ESC + "[40m";
    static final String COLOR_BACKGROUND_WHITE = ESC + "[47m";
    //terminal colors
    static final String COLOR_RED = ESC + "[31m";
    static final String COLOR_GREEN = ESC + "[32m";
    static final String COLOR_YELLOW = ESC + "[33m";
    static final String COLOR_BLUE = ESC + "[34m";
    static final String COLOR_MAGENTA = ESC + "[35m";
    static final String COLOR_CYAN = ESC + "[36m";
    static final String COLOR_WHITE = ESC + "[37m";
    static final String COLOR_BLACK = ESC + "[39m";

    public static void main( String[] args )
    {
        System.out.println("Trux Geo Simulation");
        System.out.print(CLEAR_SCREEN + SET_CURSOR_POSITION + FONT_BOLD);
        
        Sun sun = new Sun("Sun", 10);
        Geo geo = Geo.getInstance();
        sun.registerOrbit(geo);
        
        geo.spawn(new SolarTrux(1, geo.randomTag(), 10, Direction.LEFT));
        geo.spawn(new LiPolCapsule(geo.randomTag(), 90));
        geo.spawn(new LiPolCapsule(geo.randomTag(), 70));
        geo.spawn(new LiPolCapsule(geo.randomTag(), 50));
        geo.spawn(new LiPolCapsule(geo.randomTag(), 40));
        
        double delta = 0;
        while (true) {
        	double start = System.currentTimeMillis();
        	System.out.println(geo.toString());
        	sun.mutate(delta);
        	geo.mutate(delta);
        	
        	try {
				Thread.sleep(200);
			} catch (InterruptedException e) {}
        	delta = (System.currentTimeMillis() - start) / 1000;
        	System.out.print(geo.erase());
        }
    }
}
