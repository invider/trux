package com.decaflabs.trux.platform.trux;

import com.decaflabs.trux.platform.AbstractTrux;

public class RedTrux extends AbstractTrux {

	private static final int TEAM = 1;
	
	private static final double SPEED = 20d;
	
	private int NAPRAVLENIE = 0; // 0 - Stop, -1 west, +1 east
	
	private void setNapravlenie(int value) {
		switch(value){
		case -1:
			NAPRAVLENIE = -1;
			break;
		case 0:
			NAPRAVLENIE = 0;
			break;
		case 1:
			NAPRAVLENIE = 1;
			break;
		default:
			NAPRAVLENIE = 1;
			break;
		}
	}
	
	public RedTrux(double x, int napravlenie) {
		super(TEAM);
		super.setX(x);
		super.setY(0);
		
		setNapravlenie(napravlenie);
	}
	
	public void mutate(double delta) {
		this.setX(this.getX() + NAPRAVLENIE * this.SPEED * delta);
	}


}
