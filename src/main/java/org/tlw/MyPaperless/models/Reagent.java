package org.tlw.MyPaperless.models;



import javax.persistence.*;
import javax.validation.constraints.Digits;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Reagent {

    @Id
    @GeneratedValue
    private int chemid;

    @NotNull
    @Size(min= 4, max= 50)
    private String chemName;


    @NotNull
    @Digits(integer = 3, fraction=3, message = "Enter a numerical value" )
    private String density;

    @NotNull
    @Digits(integer = 3, fraction=3, message = "Enter a numerical value" )
    private String mw; //molecular weight

    @NotNull
    @Lob
    @Size(min= 5, max= 200, message = "Must be 5 to 100 characters long including spaces")
    private String hazard;

    @ManyToOne
    @JoinColumn(name = "intro_id",nullable = false)
    private Intro intro;

    public Reagent(String chemName, String density, String mw, String hazard) {

        this.chemName = chemName;
        this.density = density;
        this.mw = mw;
        this.hazard = hazard;
        intro.addReagent(this);
    }

    public Reagent() {}

    public int getChemid() {
        return chemid;
    }

    public String getChemName() {
        return chemName;
    }

    public void setChemName(String chemName) {
        this.chemName = chemName;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getMw() {
        return mw;
    }

    public void setMw(String mw) {
        this.mw = mw;
    }

    public String getHazard() {
        return hazard;
    }

    public void setHazard(String hazard) {
        this.hazard = hazard;
    }

    public Intro getIntro() {
        return intro;
    }

    public void setIntro(Intro intro) {
        this.intro=  intro;
    }

    public static boolean isValidNumber(String number){

        Pattern isAValidNumber = Pattern.compile("[1-9][.0-9_]{0,7}");

        Matcher matcher = isAValidNumber.matcher(number);

        return matcher.matches();
    }

    public static boolean isValidChemName(String chemname){

        Pattern isAValidChemName = Pattern.compile("[A-Za-z0-9\\s]{5,}");
        Matcher matcher = isAValidChemName.matcher(chemname);
        return matcher.matches();
    }


}
