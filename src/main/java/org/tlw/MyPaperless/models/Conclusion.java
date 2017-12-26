package org.tlw.MyPaperless.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "conclusion")
public class Conclusion {
    @Column(name = "conslusions")
    @NotNull
    @Size(min = 1,message = "You forgot something")
    private String conclusions;

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "conid", unique = true)
    private int conID;

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

    public int getConID() {
        return conID;
    }



}
