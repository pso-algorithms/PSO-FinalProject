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
public class DonorPerson {
    
        private Location loc;
    
    private int personId;
    
    private BloodGroup bgroup;
    
    public DonorPerson(Location loc, BloodGroup rqBg, int personId){
        this.loc = loc;
        this.personId = personId;
        this.bgroup = rqBg;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public BloodGroup getBgroup() {
        return bgroup;
    }

    public void setBgroup(BloodGroup bgroup) {
        this.bgroup = bgroup;
    }

    
  
}
