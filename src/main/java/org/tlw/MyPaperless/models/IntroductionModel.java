package org.tlw.MyPaperless.models;

public class IntroductionModel {

    private String title;
    private String purpose;
    private String materials;
    private String chemical;
    private String hazards;
    private int density;
    private int mw;
    private String procedure;
    private String observations;
    private String conclusion;



    public IntroductionModel() {
    }

    public IntroductionModel(String title, String purpose, String materials) {
        this();
        this.title = title;
        this.purpose = purpose;
        this.materials = materials;
    }


    public IntroductionModel(String chemical, String hazards, int density, int mw) {
        this.chemical = chemical;
        this.hazards = hazards;
        this.density = density;
        this.mw = mw;
    }

    public String getChemical() {
        return chemical;
    }

    public void setChemical(String chemical) {
        this.chemical = chemical;
    }

    public String getHazards() {
        return hazards;
    }

    public void setHazards(String hazards) {
        this.hazards = hazards;
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

    public String getTitle() {
        return title;
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
    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
