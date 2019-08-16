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
    
}
