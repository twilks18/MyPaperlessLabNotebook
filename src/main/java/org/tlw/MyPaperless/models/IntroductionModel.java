package org.tlw.MyPaperless.models;

public class IntroductionModel {

    private String title;
    private String purpose;
    private String materials;
    private String chemName;
    private int density;
    private int mw; //molecular weight
    private  String hazard;

    public IntroductionModel(String title, String purpose, String materials, String chemName, int density, int mw, String hazard) {
        this.purpose = purpose;
        this.title = title;
        this.materials = materials;
        this.chemName = chemName;
        this.density = density;
        this.mw = mw;
        this.hazard = hazard;
    }

    public String getTitle() {
        return title;
    }

    public void setChemName(String chemName) {
        this.chemName = chemName;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public void setMw(int mw) {
        this.mw = mw;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getChemName() {
        return chemName;
    }

    public int getDensity() {
        return density;
    }

    public int getMw() {
        return mw;
    }

    public String getHazard() {
        return hazard;
    }

    public void setHazard(String hazard) {
        this.hazard = hazard;
    }
}
