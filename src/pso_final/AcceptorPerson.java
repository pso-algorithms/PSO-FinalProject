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
public class AcceptorPerson {

    private Location loc;

    private int personId;

    private BloodGroup requiredBloodGroup;

    public AcceptorPerson(Location lc, BloodGroup bg) {
        this.loc = lc;
        this.requiredBloodGroup = bg;
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

    public BloodGroup getRequiredBloodGroup() {
        return requiredBloodGroup;
    }

    public void setRequiredBloodGroup(BloodGroup requiredBloodGroup) {
        this.requiredBloodGroup = requiredBloodGroup;
    }

    public void draw(Graphics gh, Color c, Location l) {

        int x_coordinate = (int) (l.getLoc()[0] * 2);
        int y_coordinate = (int) (l.getLoc()[1] * 2);
        gh.setColor(c);
        gh.drawOval(x_coordinate, y_coordinate, 20, 20);

    }

    public void drawBest(Graphics gh, Color c, Location l) {

        int x_coordinate = (int) (l.getLoc()[0] * 2);
        int y_coordinate = (int) (l.getLoc()[1] * 2);
        gh.setColor(c);
        gh.drawOval(x_coordinate, y_coordinate, 20, 20);

    }

}
