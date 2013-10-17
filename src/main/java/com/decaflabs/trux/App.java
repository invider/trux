package com.decaflabs.trux;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Trux Geo Simulation");
        
        GeoSample geo = new GeoSample();
        
        double delta = 0;
        while (true) {
        	double start = System.currentTimeMillis();
        	System.out.print(geo.toString());
        	geo.mutate(delta);
        	
        	try {
				Thread.sleep(200);
			} catch (InterruptedException e) {}
        	delta = (System.currentTimeMillis() - start) / 1000;
        	System.out.print(geo.erase());
        }
    }
}
