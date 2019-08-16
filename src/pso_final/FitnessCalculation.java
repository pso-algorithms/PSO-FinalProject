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
    
    public static double getFinessValue(AcceptorPerson ap, DonorPerson dp){
        double xDistance = Math.pow((ap.getLoc().getLoc()[0]-dp.getLoc().getLoc()[0]),2);
        double yDistance = Math.pow((ap.getLoc().getLoc()[1]-dp.getLoc().getLoc()[1]),2);
        double zdistance = Math.pow((ap.getLoc().getLoc()[2]-dp.getLoc().getLoc()[2]),2);
        
        double dis = Math.sqrt(xDistance+yDistance+zdistance);
        
        double fitValue;
        if(ap.getRequiredBloodGroup().equals(dp.getBgroup())){
            fitValue = 1; 
        }else{
            fitValue = 10;
        }
        
        double fitnessValue = (dis + fitValue);
        return fitnessValue;
    }
    
}
