package com.decaflabs.trux.platform;

import java.util.Iterator;

import com.decaflabs.trux.Geo;
import com.decaflabs.trux.platform.capsule.Battery;
import com.decaflabs.trux.platform.capsule.Capsule;

public class SolarTrux extends AbstractTrux {

    private final int POWER;
    private double speed;
    

    public SolarTrux(int team, double x, int power, Direction initialDirection) {
        super(team);
        super.setX(x);
        super.setY(0);
        POWER = power;
        direction = initialDirection;
    }
    
    @Override
    public void capture(Capsule capsule) {
        if (capsule instanceof Battery) {
            super.capture(capsule);
        }
    }
    
    @Override
    public void mutate(double delta){
        //calculate the amount of generated energy
        double energy = delta * POWER;
        
        //check the part of day. If DAY - charge battery, else - use battery power
        if (Geo.sunIsShining()) {
            //if some batteries is not full split generated energy into 2: 
            //for charging and for current movement
            double splittedEnergy = energy/2d;
            //assume that speed ~ energy
            speed = energy;
            for (Capsule c : this.capsules) {
                if (c instanceof Battery) {
                    Battery battery = (Battery)c;
                    if (!battery.isFull()) {
                        // cheat - no "break" statement here )
                        battery.recharge(splittedEnergy);
                        speed = splittedEnergy;
                    }
                }
            }
        } else {
            speed = 0;
            for (Capsule c : this.capsules) {
                if (c instanceof Battery) {
                    Battery b = (Battery)c;
                    if (b.getCharge()>0) {
                        speed = b.discharge(energy);
                        break;
                    }
                }
            }
        }
        
        setX(getX() + speed * delta * direction.factor());
        super.mutate(delta);
    }
    
    public double getSpeed(){
        return speed;
    }
    
    public String toString(){
        StringBuffer buf = new StringBuffer();
        buf.append(super.toString());
        buf.append("; capsules.count="+this.capsules.size());
        for (Capsule c : this.capsules) {
            buf.append("\n    capsule: ");
            buf.append(c.toString());
            if (c instanceof Battery) {
                Battery b = (Battery)c;
                buf.append("; C=");
                buf.append(String.format("%.1f", b.getCapacity()));
                buf.append("; E=");
                buf.append(String.format("%.1f", b.getCharge()));
            }
        }
        return buf.toString();
    }
    
}
