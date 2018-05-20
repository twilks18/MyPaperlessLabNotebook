package org.tlw.MyPaperless.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Conclusions {
    @Id
    @GeneratedValue
    private int conid;

    @NotNull
    @Lob
    @Size(min=1000, message = "Either you did not add a conclusion or its just too short")
    private String conclusion;

    @OneToOne
    private Intro intro;

    public Conclusions(String conclusion) {
        this.conclusion = conclusion;
    }

    public Conclusions() {
    }

    public int getConid() {
        return conid;
    }

    public void setConid(int conid) {
        this.conid = conid;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public Intro getIntro() {
        return intro;
    }

    public void setIntro(Intro intro) {
        this.intro = intro;
    }
}


