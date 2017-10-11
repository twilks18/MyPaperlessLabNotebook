package org.tlw.MyPaperless.models;

public class ProcObsModel {

    private String procedure;
    private String observations;

    public ProcObsModel(String procedure, String observations) {
        this.procedure = procedure;
        this.observations = observations;
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
}
