package org.tlw.MyPaperless.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Observations {

    @Id
    private int observid;

    @NotNull
    @Lob
    @Size(min=1000, message = "Either you did not add observations or its just too short")
    private String observation;

    @OneToOne
    @JoinColumn(name="intro_id",nullable = false)
    private Intro intro;

    public Observations(String observation) {
        this.observation = observation;
    }

    public Observations() {
    }

    public int getObservid() {
        return observid;
    }

    public void setObservid(int observid) {
        this.observid = observid;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Intro getIntro() {
        return intro;
    }

    public void setIntro(Intro intro) {
        this.intro = intro;
    }
}
