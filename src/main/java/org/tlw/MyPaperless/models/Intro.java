package org.tlw.MyPaperless.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Intro {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Lob
    @Size(min= 4 , max= 30)
    private String title;

    @NotNull
    @Lob
    @Size(min= 5, message= " You forgot something")
    private String purpose;

    @NotNull
    @Lob
    @Size(min= 5, message= " You forgot something")
    private String materials;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "intro_id")
    private List<Reagent> reagent = new ArrayList<>();


    @OneToOne(mappedBy = "intro", cascade = CascadeType.ALL)
    @JoinColumn(name="intro_id")
    private Conclusions conclude;

    @OneToOne(mappedBy = "intro", cascade = CascadeType.ALL)
    @JoinColumn(name="intro_id")
    private Observations observation;


    @OneToOne(mappedBy = "intro", cascade = CascadeType.ALL)
    @JoinColumn(name="intro_id")
    private Proced proceds;


    @ManyToOne
    private User user;

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

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }


    public int getId() {
        return id;
    }

    public void setReagent(List<Reagent> reagent) {
        this.reagent = reagent;
    }

    public List<Reagent> getReagent() { return reagent; }


    public Conclusions getConclude() {
        return conclude;
    }

    public void setConclude(Conclusions conclude) {
        this.conclude = conclude;
    }

    public Observations getObservation() {
        return observation;
    }

    public void setObservation(Observations observation) {
        this.observation = observation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Proced getProceds() {
        return proceds;
    }

    public void setProceds(Proced proceds) {
        this.proceds = proceds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
