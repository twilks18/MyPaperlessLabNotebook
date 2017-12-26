package org.tlw.MyPaperless.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "procobs")
public class Procobs {

    @NotNull
    @Size(min = 1,message = "You forgot something")
    @Column(name = "procedures")
    private String procedure;

    @NotNull
    @Size(min = 1,message = "You forgot something")
    @Column(name = "observations")
    private String observations;

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "procid", unique = true)
    private int procID;


    public Procobs(String procedure, String observations) {

        this.procedure = procedure;
        this.observations = observations;
    }

    public Procobs() {}

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public int getProcID() {
        return procID;
    }


}
