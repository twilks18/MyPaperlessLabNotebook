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
    @Size(min= 4 , max= 30, message = "Experiments need titles right?")
    private String title;

    @NotNull
    @Lob
    @Size(min= 5, message= "Add a purpose")
    private String purpose;

    @NotNull
    @Lob
    @Size(min= 5, message= "Add materials")
    private String materials;

    @OneToMany(mappedBy = "intro",cascade = CascadeType.ALL)
    private List<Reagent> reagent = new ArrayList<>();


    @OneToOne(mappedBy = "intro", cascade = CascadeType.ALL)
    private Conclusions conclude;

    @OneToOne(mappedBy = "intro", cascade = CascadeType.ALL)
    private Observations observation;


    @OneToOne(mappedBy = "intro", cascade = CascadeType.ALL)
    private Proced proceds;


    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    public Intro(String title, String purpose, String materials) {

        this.title = title;
        this.purpose = purpose;
        this.materials = materials;
        user.addIntro(this);
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


    protected void addReagent(Reagent reagents) {
        reagent.add(reagents);
    }


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
