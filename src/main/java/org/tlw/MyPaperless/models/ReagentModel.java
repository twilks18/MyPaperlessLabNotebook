package org.tlw.MyPaperless.models;

public class ReagentModel {
    private String chemName;
    private int density;
    private int mw; //molecular weight
    private  String hazard;

    public ReagentModel(String chemName, int density, int mw, String hazard) {
        this.chemName = chemName;
        this.density = density;
        this.mw = mw;
        this.hazard = hazard;
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
}
