package org.tlw.MyPaperless.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Intro {

    @NotNull
    @Size(min= 5 , max= 30, message = "Experiments need titles right?")
    private String title;

    @NotNull
    @Size(min= 5, message= " You forgot something")
    private String purpose;

    @NotNull
    @Size(min= 5, message= " You forgot something")
    private String materials;
    private int titleID;
    private static int nextTitleID = 1;

    public Intro(String title, String purpose, String materials) {
        this();
        this.title = title;
        this.purpose = purpose;
        this.materials = materials;
    }

    public Intro() {
        titleID = nextTitleID;
        nextTitleID++;
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
    public int getTitleID() {
        return titleID;
    }

    public void setTitleID(int titleID) {
        this.titleID = titleID;
    }

}
