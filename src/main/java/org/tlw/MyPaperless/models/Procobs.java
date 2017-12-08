package org.tlw.MyPaperless.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Procobs {

    @NotNull
    @Size(min = 1,message = "You forgot something")
    private String procedure;

    @NotNull
    @Size(min = 1,message = "You forgot something")
    private String observations;

    private int procID;
    private static int nextProcID = 1;

    public Procobs(String procedure, String observations) {
        this();
        this.procedure = procedure;
        this.observations = observations;
    }

    public Procobs() {
        procID = nextProcID;
        nextProcID++;
    }

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

    public void setProcID(int procID) {
        this.procID = procID;
    }
}
