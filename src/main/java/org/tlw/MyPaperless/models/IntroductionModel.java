package org.tlw.MyPaperless.models;

public class IntroductionModel {

    private String title;
    private String purpose;
    private String materials;

    public IntroductionModel(String title, String purpose, String materials) {
        this.title = title;
        this.purpose = purpose;
        this.materials = materials;
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
}
