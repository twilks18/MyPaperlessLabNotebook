package org.tlw.MyPaperless.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Proced {

    @Id
    @GeneratedValue
    private int pid;

    @NotNull
    @Lob
    @Column
    @Size(min = 1, message = "You forgot something")
    private String proceds;

    @OneToOne
    Intro intro;

    public Proced(String proceds) {
        this.proceds = proceds;
    }

    public Proced() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProceds() {
        return proceds;
    }

    public void setProceds(String proceds) {
        this.proceds = proceds;
    }

    public Intro getIntro() {
        return intro;
    }

    public void setIntro(Intro intro) {
        this.intro = intro;
    }
}
