package com.decaflabs.trux.platform.capsule;

public class LiPolCapsule extends AbstractCapsule implements Battery {
    
    private final double CAPACITY;
    private double charge; // remained energy : V*A
    
    
    public LiPolCapsule(double x, double capacity) {
        super();
        super.setX(x);
        this.CAPACITY = capacity;
        charge = CAPACITY;
    }
    
    public void recharge(double energy) {
        if (charge < CAPACITY) {
            charge += energy;
            if (charge > CAPACITY) {
                charge = CAPACITY;
            }
        }
    }
    
    public double discharge(double energy) {
        if (charge == 0) {
            return 0;
        } else {
            double retEnergy = energy;
            charge -= energy;
            if (charge < 0) {
                retEnergy = energy + charge;
                charge = 0;
            }
            return retEnergy;
        }
    }
    
    public double getCharge() {
        return charge;
    }
    
    public double getCapacity() {
        return CAPACITY;
    }
    
    public boolean isFull() {
        return charge == CAPACITY;
    }
    
}
