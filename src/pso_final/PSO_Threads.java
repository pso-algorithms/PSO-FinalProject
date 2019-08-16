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
public class PSO_Threads implements Runnable{
        
    
    private ImplementPSO pso; 
    private int i;
    public PSO_Threads(ImplementPSO pso, int i){
        this.pso=pso;
        this.i = i;
    }
    
    @Override
    public void run(){
        pso.execute1(i);
    }
}
