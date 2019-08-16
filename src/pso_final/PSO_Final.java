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
public class PSO_Final {

    static int NO_OF_AGENTS = 30;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        ImplementPSO imp = new ImplementPSO(NO_OF_AGENTS);
        imp.initializeSwarm();
        //imp.execute();
        Thread[] agents = new Thread[NO_OF_AGENTS];
        for(int i=0; i< NO_OF_AGENTS; i++){
            agents[i] = new Thread(new PSO_Threads(imp, i));
            agents[i].start();
        }
        
        try{
            for(int i=0; i< NO_OF_AGENTS; i++){
            
            agents[i].join();
        }
        }catch(InterruptedException e){
            System.out.println("Simulation thread interrupted.");
        }
        
        imp.printGlobalBest();
//         System.out.println("\nSolution found at iteration " + (29) + ", the solutions is:");
//	System.out.println("     Best X: "   + imp.getgBestLocation().getLoc()[0]);
//	System.out.println("     Best Y: "   + imp.getgBestLocation().getLoc()[1]);
//        System.out.println("     Best Z: "   + imp.getgBestLocation().getLoc()[2]);
//        System.out.println("     Donor BG: " + gBestDonor.getBgroup());
//        System.out.println("     Best DX: "   + gBestDonor.getLoc().getLoc()[0]);
//	System.out.println("     Best DY: "   + gBestDonor.getLoc().getLoc()[1]);
//        System.out.println("     Best DZ: "   + gBestDonor.getLoc().getLoc()[2]);
    }
    
}
