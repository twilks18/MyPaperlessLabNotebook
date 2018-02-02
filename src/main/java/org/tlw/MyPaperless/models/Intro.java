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
    @Size(min= 4 , max= 30, message = "Experiments need titles right?")
    private String title;

    @NotNull
    @Size(min= 5, message= " You forgot something")
    private String purpose;

    @NotNull
    @Size(min= 5, message= " You forgot something")
    private String materials;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "intro_id")
    private List<Reagent> reagent = new ArrayList<>();

    @OneToOne(mappedBy = "intro" , cascade = CascadeType.ALL)
    @JoinColumn(name= "intro_id")
    private Procobs procobs;

    @OneToOne(mappedBy = "intro", cascade = CascadeType.ALL)
    @JoinColumn(name="intro_id")
    private Conclusion conclude;

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

    public Procobs getProcobs() {
        return procobs;
    }

    public void setProcobs(Procobs procobs) {
        this.procobs = procobs;
    }

    public Conclusion getConclude() {
        return conclude;
    }

    public void setConclude(Conclusion conclude) {
        this.conclude = conclude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
