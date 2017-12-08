package org.tlw.MyPaperless.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Conclusion {
    @NotNull
    @Size(min = 1,message = "You forgot something")
    private String conclusions;

    private int conID;
    private static int nextConID = 1;



    public Conclusion(String conclusions) {
        this();
        this.conclusions = conclusions;
    }

    public Conclusion() {
        conID = nextConID;
        nextConID++;
    }

    public String getConclusions() {
        return conclusions;
    }

    public void setConclusions(String conclusions) {
        this.conclusions = conclusions;
    }

    public int getConID() {
        return conID;
    }

    public void setConID(int conID) {
        this.conID = conID;
    }

}
