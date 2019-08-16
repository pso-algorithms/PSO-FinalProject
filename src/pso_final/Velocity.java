/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso_final;

/**
 *
 * @author balak
 */
public class Velocity {
    
        private double[] velocity;
    
    public Velocity(double[] velocity){
        this.velocity = velocity;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }
    
}
