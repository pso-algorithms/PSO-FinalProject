/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso_final;

import Visualization.PsoParticles;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author balak
 */
public class ImplementPSO {

    static int SWARM_SIZE = 100;
    static int MAX_ITERATION = 30;
    static double c1 = 1.5;
    static double c2 = 1.5;
    static int HIGHEST_VELOCITY = 100;
    static double W_UPPERBOUND = 1.0;
    static double W_LOWERBOUND = 0.0;
    static int LOWEST_VELOCITY = 0;
    static int maxDirection = 100;
    AcceptorPerson acceptor;
    double[] location;
    static int personId = 0;
    ArrayList<Particle> swarms = new ArrayList<Particle>();
    private double[] fitnessValueList = new double[SWARM_SIZE];
    private double[] pBest = new double[SWARM_SIZE];
    private ArrayList<Location> pBestLocation = new ArrayList<Location>();
    double gBest;
    Location gBestLocation;

    public Location getgBestLocation() {
        return gBestLocation;
    }

    public void setgBestLocation(Location gBestLocation) {
        this.gBestLocation = gBestLocation;
    }
    DonorPerson gBestDonor;
    PsoParticles particleVisualization;
    //static int t=0;
    public ImplementPSO(){
        particleVisualization = new PsoParticles();
        particleVisualization.pack();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = screenSize.height;
    int width = screenSize.width;
    particleVisualization.setSize(width/2, height/2);

    // center the jframe on screen
    particleVisualization.setLocationRelativeTo(null);
        location = new double[3];
        location[0] = ThreadLocalRandom.current().nextInt(10,50);
        location[1] = ThreadLocalRandom.current().nextInt(10,50);
        location[2] = ThreadLocalRandom.current().nextInt(10,50);
        Location sloc = new Location(location);
        acceptor = new AcceptorPerson(sloc, BloodGroup.randomBloodgroup());
        System.out.println("Acceptor X: " + acceptor.getLoc().getLoc()[0]);
        System.out.println("Acceptor Y: " + acceptor.getLoc().getLoc()[1]);
        System.out.println("Acceptor Z: " + acceptor.getLoc().getLoc()[2]);
        System.out.println("Required BG: " + acceptor.getRequiredBloodGroup());
    }
    
    public void initializeSwarm(){
        
        Particle particle;
        double[] loc;
        double[] vel;
        for(int i=0; i<SWARM_SIZE; i++){
            loc = new double[3];
            vel = new double[3];
            particle = new Particle();
            
            loc[0]= ThreadLocalRandom.current().nextInt(50,maxDirection);
            loc[1]= ThreadLocalRandom.current().nextInt(50,maxDirection);
            loc[2]= ThreadLocalRandom.current().nextInt(50,maxDirection);
            
            Location particlePosition = new Location(loc);
            
            vel[0] = ThreadLocalRandom.current().nextInt(1,5);
            vel[1] = ThreadLocalRandom.current().nextInt(1,5);
            vel[2] = ThreadLocalRandom.current().nextInt(1,5);
            
            Velocity velocity = new Velocity(vel);
            particle.setLocation(particlePosition);
            particle.setVelocity(velocity); 
//            System.out.println("\n Particle: "+ i);
//            System.out.println("Donor X: " + particle.getLocation().getLoc()[0]);
//            System.out.println("Donor Y: " + particle.getLocation().getLoc()[1]);
//            System.out.println("Donor Z: " + particle.getLocation().getLoc()[2]);
            swarms.add(particle);
            personId++;
            createDonor(particle, personId);
            
        }
        setFinessValues();
         for (int i = 0; i < SWARM_SIZE; i++) {
            pBest[i] = fitnessValueList[i];
            pBestLocation.add(swarms.get(i).getLocation());
        }
        int bestParticleIndex = getBestFit(fitnessValueList);
        gBest = fitnessValueList[0];
        gBestLocation = swarms.get(0).getLocation();
        gBestDonor = swarms.get(0).getDonorPerson();
        
        printParticles();
    }
    
    public void printParticles(){
        for(int i=0; i<SWARM_SIZE; i++){
            System.out.println("\n Particle: "+ i);
            System.out.println("Donor X: " + swarms.get(i).getLocation().getLoc()[0]);
            System.out.println("Donor Y: " + swarms.get(i).getLocation().getLoc()[1]);
            System.out.println("Donor Z: " + swarms.get(i).getLocation().getLoc()[2]);
            System.out.println("Donor BG: " + swarms.get(i).getDonorPerson().getBgroup());
        }
    }
    
    public void createDonor(Particle p,int personId){
        double[] loc = new double[3];
        loc[0]= ThreadLocalRandom.current().nextInt(1,maxDirection);
        loc[1]= ThreadLocalRandom.current().nextInt(1,maxDirection);
        loc[2]= ThreadLocalRandom.current().nextInt(1,maxDirection);
        Location l = new Location(loc);
        
        DonorPerson dp = new DonorPerson(p.getLocation(), BloodGroup.randomBloodgroup(), personId);
        p.setDonorPerson(dp);
    }
    
    
    public void setFinessValues(){
        for(int i=0; i<SWARM_SIZE; i++) {
                    fitnessValueList[i] = swarms.get(i).getFitnessValue(acceptor, swarms.get(i).getDonorPerson());
                    
                }
    }
    
    
    public void execute1(int t){
        
        for(int j=0; j<SWARM_SIZE; j++){
                if(fitnessValueList[j]<pBest[j]){
                    pBest[j] = fitnessValueList[j];
                    pBestLocation.set(j, swarms.get(j).getLocation());
                }
            }
            
            int bestParticleIndex = getBestFit(fitnessValueList);
            
            if(fitnessValueList[bestParticleIndex] < gBest){
                System.out.println("In Gbest");
                gBest = fitnessValueList[bestParticleIndex];
                gBestLocation = swarms.get(bestParticleIndex).getLocation();
                gBestDonor = swarms.get(bestParticleIndex).getDonorPerson();
                
            }
            System.out.println("Thread t: " + t);
            double w = W_UPPERBOUND - ((int) (t) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);
            double r1 = ThreadLocalRandom.current().nextInt(0, 1);
            double r2 = ThreadLocalRandom.current().nextInt(0, 1);
            
            for(int i=0; i<SWARM_SIZE; i++){
                Particle particle = swarms.get(i);
                
                double[] newVelocity = new double[3];
                
                newVelocity[0] = ((w * particle.getVelocity().getVelocity()[0]) + (r1 * c1) *(pBestLocation.get(i).getLoc()[0] - particle.getLocation().getLoc()[0]) 
                                    + (r2 * c2) * (gBestLocation.getLoc()[0] - particle.getLocation().getLoc()[0]));
                if(newVelocity[0] > maxDirection){
                    newVelocity[0] = maxDirection;
                }
                newVelocity[1] = ((w * particle.getVelocity().getVelocity()[1]) + (r1 * c1) *(pBestLocation.get(i).getLoc()[1] - particle.getLocation().getLoc()[1]) 
                                    + (r2 * c2) * (gBestLocation.getLoc()[1] - particle.getLocation().getLoc()[1]));
                if(newVelocity[1] > maxDirection){
                    newVelocity[1] = maxDirection;
                }
                newVelocity[2] = ((w * particle.getVelocity().getVelocity()[2]) + (r1 * c1) *(pBestLocation.get(i).getLoc()[2] - particle.getLocation().getLoc()[2]) 
                                    + (r2 * c2) * (gBestLocation.getLoc()[2] - particle.getLocation().getLoc()[2]));
                if(newVelocity[2] > maxDirection){
                    newVelocity[2] = maxDirection;
                }
                Velocity v = new Velocity(newVelocity);
                particle.setVelocity(v);
                
                double[] newLoc = new double[3];
                
                newLoc[0] = particle.getLocation().getLoc()[0]+newVelocity[0];
                newLoc[1] = particle.getLocation().getLoc()[1]+newVelocity[1];
                newLoc[2] = particle.getLocation().getLoc()[2]+newVelocity[2];
                
                Location newLocation  = new Location(newLoc);
                particle.setLocation(newLocation);
                updatePositions();
            }
            
//            System.out.println("ITERATION " + t + ": ");
//            System.out.println("     Best X: " + gBestLocation.getLoc()[0]);
//            System.out.println("     Best Y: " + gBestLocation.getLoc()[1]);
//            System.out.println("     Best Z: " + gBestLocation.getLoc()[2]);
            
            
            t++;
            setFinessValues();
             
            
    }


    public void execute() {
        //initializeSwarm();
        setFinessValues();

//        for (int i = 0; i < SWARM_SIZE; i++) {
//            pBest[i] = fitnessValueList[i];
//            pBestLocation.add(swarms.get(i).getLocation());
//        }
        
        int t=0;
        
        while(t < MAX_ITERATION){
            
            for(int j=0; j<SWARM_SIZE; j++){
                if(fitnessValueList[j]<pBest[j]){
                    pBest[j] = fitnessValueList[j];
                    pBestLocation.set(j, swarms.get(j).getLocation());
                }
            }
            
            int bestParticleIndex = getBestFit(fitnessValueList);
            
            if(t==0 || fitnessValueList[bestParticleIndex] < gBest){
                
                gBest = fitnessValueList[bestParticleIndex];
                gBestLocation = swarms.get(bestParticleIndex).getLocation();
                gBestDonor = swarms.get(bestParticleIndex).getDonorPerson();
                
            }
            double w = W_UPPERBOUND - ((int) (t) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);
            double r1 = ThreadLocalRandom.current().nextInt(0, 1);
            double r2 = ThreadLocalRandom.current().nextInt(0, 1);
            
            for(int i=0; i<SWARM_SIZE; i++){
                Particle particle = swarms.get(i);
                
                double[] newVelocity = new double[3];
                
                newVelocity[0] = ((w * particle.getVelocity().getVelocity()[0]) + (r1 * c1) *(pBestLocation.get(i).getLoc()[0] - particle.getLocation().getLoc()[0]) 
                                    + (r2 * c2) * (gBestLocation.getLoc()[0] - particle.getLocation().getLoc()[0]));
                if(newVelocity[0] > maxDirection){
                    newVelocity[0] = maxDirection;
                }
                newVelocity[1] = ((w * particle.getVelocity().getVelocity()[1]) + (r1 * c1) *(pBestLocation.get(i).getLoc()[1] - particle.getLocation().getLoc()[1]) 
                                    + (r2 * c2) * (gBestLocation.getLoc()[1] - particle.getLocation().getLoc()[1]));
                if(newVelocity[1] > maxDirection){
                    newVelocity[1] = maxDirection;
                }
                newVelocity[2] = ((w * particle.getVelocity().getVelocity()[2]) + (r1 * c1) *(pBestLocation.get(i).getLoc()[2] - particle.getLocation().getLoc()[2]) 
                                    + (r2 * c2) * (gBestLocation.getLoc()[2] - particle.getLocation().getLoc()[2]));
                if(newVelocity[2] > maxDirection){
                    newVelocity[2] = maxDirection;
                }
                Velocity v = new Velocity(newVelocity);
                particle.setVelocity(v);
                
                double[] newLoc = new double[3];
                
                newLoc[0] = particle.getLocation().getLoc()[0]+newVelocity[0];
                newLoc[1] = particle.getLocation().getLoc()[1]+newVelocity[1];
                newLoc[2] = particle.getLocation().getLoc()[2]+newVelocity[2];
                
                Location newLocation  = new Location(newLoc);
                particle.setLocation(newLocation);
                //updatePositions();
            }
            
//            System.out.println("ITERATION " + t + ": ");
//            System.out.println("     Best X: " + gBestLocation.getLoc()[0]);
//            System.out.println("     Best Y: " + gBestLocation.getLoc()[1]);
//            System.out.println("     Best Z: " + gBestLocation.getLoc()[2]);
            
            
            t++;
            setFinessValues();
            
        }
        
        System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
	System.out.println("     Best X: "   + gBestLocation.getLoc()[0]);
	System.out.println("     Best Y: "   + gBestLocation.getLoc()[1]);
        System.out.println("     Best Z: "   + gBestLocation.getLoc()[2]);
        System.out.println("     Donor BG: " + gBestDonor.getBgroup());
        System.out.println("     Best DX: "   + gBestDonor.getLoc().getLoc()[0]);
	System.out.println("     Best DY: "   + gBestDonor.getLoc().getLoc()[1]);
        System.out.println("     Best DZ: "   + gBestDonor.getLoc().getLoc()[2]);
    }
    
    public int getBestFit(double[] fitnessList){
        double minValue = fitnessList[0];
        int index = 0;
        
        for(int i=1; i<fitnessList.length; i++){
            if(fitnessList[i] < minValue){
                minValue = fitnessList[i];
                index = i;
            }
        }
        
        return index;
    }
    
    public void printGlobalBest(){
        System.out.println("\nSolution found at iteration " + (29) + ", the solutions is:");
	System.out.println("     Best X: "   + gBestLocation.getLoc()[0]);
	System.out.println("     Best Y: "   + gBestLocation.getLoc()[1]);
        System.out.println("     Best Z: "   + gBestLocation.getLoc()[2]);
        System.out.println("     Donor BG: " + gBestDonor.getBgroup());
        System.out.println("     Best DX: "   + gBestDonor.getLoc().getLoc()[0]);
	System.out.println("     Best DY: "   + gBestDonor.getLoc().getLoc()[1]);
        System.out.println("     Best DZ: "   + gBestDonor.getLoc().getLoc()[2]);  
    }
    
    public void updatePositions(){
         
         //particleVisualization.getGraphics().setPaintMode();
         particleVisualization.getGraphics().fill3DRect(0, 0, particleVisualization.getWidth(), particleVisualization.getHeight(),true);
         acceptor.draw(particleVisualization.getGraphics(),Color.ORANGE,acceptor.getLoc());
         for(Particle p : swarms ){
             
             p.drawParticle(particleVisualization.getGraphics(), Color.GREEN, p.getLocation());
             
         }
         
         for(Location l : pBestLocation ){
             
             acceptor.draw(particleVisualization.getGraphics(), Color.blue, l);             
         }
         
         for(int i =0; i<SWARM_SIZE; i++){
             if(acceptor.getRequiredBloodGroup().equals(swarms.get(i).getDonorPerson().getBgroup())){
                 acceptor.draw(particleVisualization.getGraphics(), Color.pink, pBestLocation.get(i));
                 //acceptor.draw(particleVisualization.getGraphics(), Color.DARK_GRAY, swarms.get(i).getLocation());
             }
         }
         
         acceptor.drawBest(particleVisualization.getGraphics(), Color.RED, gBestLocation);
     }

}
