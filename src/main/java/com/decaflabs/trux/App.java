package com.decaflabs.trux;

import com.decaflabs.trux.rest.TruxServer;

/**
 * Hello Geo!
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Trux Geo Simulation");

		GeoSample geo = new GeoSample();
		
		TruxServer truxServer = new TruxServer(geo);

		// wait for http to startup
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {}
		System.out.println("\n\n");
		
		// run the simulation
		double delta = 0;
		while (true) {
			double start = System.currentTimeMillis();
			//System.out.print(geo.toString());
			geo.mutate(delta);

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
			delta = (System.currentTimeMillis() - start) / 1000;
			//System.out.print(geo.erase());
		}
	}
}
