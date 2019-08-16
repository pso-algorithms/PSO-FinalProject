/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso_final;

import Visualization.PsoParticles;
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
}
