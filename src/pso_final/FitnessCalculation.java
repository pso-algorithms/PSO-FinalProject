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
public class FitnessCalculation {
    
        static AcceptorPerson tarPerson;
    
    //DonorPerson donPerson;
    
    public FitnessCalculation(AcceptorPerson ap){
        this.tarPerson = ap;
    }
    
    public static double getFinessValue(Location l, DonorPerson dp){
        double xDistance = Math.pow((tarPerson.getLoc().getLoc()[0]-l.getLoc()[0]),2);
        double yDistance = Math.pow((tarPerson.getLoc().getLoc()[1]-l.getLoc()[1]),2);
        double zdistance = Math.pow((tarPerson.getLoc().getLoc()[2]-l.getLoc()[2]),2);
        
        double fitnessvalue = Math.sqrt(xDistance+yDistance+zdistance);
        return fitnessvalue;
    }
    
}
