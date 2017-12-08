package org.tlw.MyPaperless.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Reagent {
    @NotNull
    @Size(min= 4,message = "You forgot something" )
    private String chemName;

    @NotNull
    @Size(min= 1 , max= 3,message = "You forgot something" )
    private int density;

    @NotNull
    @Size(min= 1, max= 3, message = "You forgot something" )
    private int mw; //molecular weight

    @NotNull
    @Size(min= 1, message = "You forgot something")
    private  String hazard;
    private int chemID;
    private static int nextChemID = 1;

    public Reagent(String chemName, int density, int mw, String hazard) {
        this();
        this.chemName = chemName;
        this.density = density;
        this.mw = mw;
        this.hazard = hazard;
    }

    public Reagent() {
        chemID = nextChemID;
        nextChemID++;
    }

    public String getChemName() {
        return chemName;
    }

    public void setChemName(String chemName) {
        this.chemName = chemName;
    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public int getMw() {
        return mw;
    }

    public void setMw(int mw) {
        this.mw = mw;
    }

    public String getHazard() {
        return hazard;
    }

    public void setHazard(String hazard) {
        this.hazard = hazard;
    }

    public int getChemID() {
        return chemID;
    }

    public void setChemID(int chemID) {
        this.chemID = chemID;
    }
}
