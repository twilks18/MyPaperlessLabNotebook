package org.tlw.MyPaperless.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "intro")
public class Intro {

    @Id
    @GeneratedValue()
    @NotNull
    @Column(name = "introid")
    private int introid;

    @NotNull
    @Size(min= 5 , max= 30, message = "Experiments need titles right?")
    @Column(name = "title")
    private String title;

    @NotNull
    @Size(min= 5, message= " You forgot something")
    @Column(name ="purpose")
    private String purpose;

    @NotNull
    @Size(min= 5, message= " You forgot something")
    @Column(name = "materials")
    private String materials;


    public Intro(String title, String purpose, String materials) {

        this.title = title;
        this.purpose = purpose;
        this.materials = materials;
    }

    public Intro() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPurpose() {
        return purpose;
    }

    public int getIntroid() {
        return introid;
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
