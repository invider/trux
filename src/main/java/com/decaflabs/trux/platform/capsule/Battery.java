package com.decaflabs.trux.platform.capsule;

public interface Battery {

    public void recharge(double energy);

    public double discharge(double energy);

    public double getCharge();

    public double getCapacity();
    
    public boolean isFull();

}