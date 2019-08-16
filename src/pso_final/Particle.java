/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso_final;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author aravi
 */
public class Particle {
    
    private Velocity velocity;

    private Location location;

    private double fitnessValue;
    
    private DonorPerson donorPerson;

    public Particle() {

    }

//    public Particle( Velocity velocity, Location location) {
//        //this.fitnessValue = fitnessValue;
//        this.velocity = velocity;
//        this.location = location;
//    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
//    
//    public double getFitnessValue() {
//        return fitnessValue;
//    }
//
//    public void setFitnessValue(double fitnessValue) {
//        this.fitnessValue = fitnessValue;
//    }

    public DonorPerson getDonorPerson() {
        return donorPerson;
    }

    public void setDonorPerson(DonorPerson donorPerson) {
        this.donorPerson = donorPerson;
    }
    
    
    public double getFitnessValue(AcceptorPerson ap, DonorPerson dp){
         double xDistance = Math.pow((ap.getLoc().getLoc()[0]-this.getLocation().getLoc()[0]),2);
        double yDistance = Math.pow((ap.getLoc().getLoc()[1]-this.getLocation().getLoc()[1]),2);
        //double zdistance = Math.pow((ap.getLoc().getLoc()[2]-this.getLocation().getLoc()[2]),2);
        
        //double fitnessvalue = Math.sqrt(xDistance+yDistance+zdistance);
        double dis = Math.sqrt(xDistance+yDistance);
        double fitValue;
        if(ap.getRequiredBloodGroup().equals(dp.getBgroup())){
            fitValue = 1; 
        }else{
            fitValue = 10;
        }
        
        fitnessValue = (dis + fitValue);
        return fitnessValue;
    }
    
     public void drawParticle( Graphics gh, Color c, Location l){
        
        int  x_coordinate = (int) (l.getLoc()[0] * 2);
        int  y_coordinate = (int) (l.getLoc()[1] * 2);
        gh.setColor(c);
        gh.fillOval(x_coordinate, y_coordinate,  20, 20);
        
    }
}
