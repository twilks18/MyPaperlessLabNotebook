package org.tlw.MyPaperless.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "conclusion")
public class Conclusion{

    @Id
    @GeneratedValue
    private int conid;

    @Column(name = "conslusions")
    @NotNull
    @Size(min = 1,message = "You forgot something")
    private String conclusions;

    @OneToOne
    private Intro intro;

    public Conclusion(String conclusions) {

        this.conclusions = conclusions;

    }

    public Conclusion() {}

    public String getConclusions() {
        return conclusions;
    }

    public void setConclusions(String conclusions) {
        this.conclusions = conclusions;
    }

    public int getConid() {
        return conid;
    }

    public void setConid(int conid) {
        this.conid = conid;
    }

    public Intro getIntro() {
        return intro;
    }

    public void setIntro(Intro intro) {
        this.intro = intro;
    }
}
