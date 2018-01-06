package org.tlw.MyPaperless.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name = "reagent")
public class Reagent extends AbstractModel {

    @NotNull
    @Size(min= 4,message = "You forgot something" )
    @Column(name = "chemname")
    private String chemName;

    @NotNull
    @Digits(integer = 3, fraction=3, message = "You forgot something" )
    @Min(1)
    @Column(name = "density")
    private int density;

    @NotNull
    @Digits(integer = 3, fraction=3, message = "You forgot something" )
    @Min(1)
    @Column(name = "mweight")
    private int mw; //molecular weight

    @NotNull
    @Size(min= 1, message = "You forgot something")
    @Column(name = "hazard")
    private  String hazard;

    public Reagent(String chemName, int density, int mw, String hazard) {

        this.chemName = chemName;
        this.density = density;
        this.mw = mw;
        this.hazard = hazard;
    }

    public Reagent() {}

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
