package com.cmscproject.collegerun.Sprites;

import com.badlogic.gdx.Gdx;

/**
 * HealthManager
 * Class health manager is used to keep track of the energy for player
 * @author Harrison Cook
 *
 */
public class HealthManager {
    private float energy;
    public HealthManager(){
        energy = 1;
    }

    public float getEnergy(){
        return energy;
    }

    public void lowerEnergy(){
        if(energy>0) {
            energy = energy - 0.0009f;
        }
    }

    public void addEnergy(float energyAdd){
        energy = energy+energyAdd;
        if(energy>1){
            energy = 1;
        }
    }

    public float drawSize(float buffer){
        return energy*(Gdx.graphics.getWidth()/3-2*buffer);
    }
}
