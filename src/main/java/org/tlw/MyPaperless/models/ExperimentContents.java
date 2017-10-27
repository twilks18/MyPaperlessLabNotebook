package org.tlw.MyPaperless.models;

import java.util.ArrayList;

public class ExperimentContents {


    public static ArrayList<Intro> intros = new ArrayList<>();
    public static ArrayList<Reagent> reagents = new ArrayList<>();
    public static ArrayList<ProcObs> procobs = new ArrayList<>();
    public static ArrayList<Conclusion> conclusions = new ArrayList<>();

    //add intro
    public static void addIntro(Intro newIntro){
        intros.add(newIntro);
    }

    //get all intros
    public static ArrayList<Intro> getAllIntros(){
        return intros;
    }

    //remove
    public static void removeTitle(int id){
        Intro titleToRemove = getTitleById(id);
        intros.remove(titleToRemove);
    }

    //getById (titleId)

    public static Intro getTitleById(int id) {

        Intro titleid = null;

        for (Intro theTitle : intros) {
            if (theTitle.getTitleID() == id) {
                titleid = theTitle;
            }
        }
        return titleid;
    }

    //get all reagents
    public static ArrayList<Reagent> getAllReagents(){
        return reagents;
    }

    //add reagents
    public static void addReagent(Reagent reagent){
        reagents.add(reagent);
    }

    //get reagents by id

    public static Reagent getReagentById(int id){

        Reagent chemicalID = null;

        for (Reagent theChemical : reagents){
            if ( theChemical.getChemID() == id){
                chemicalID = theChemical;
            }
        }
        return chemicalID;
    }

    //remove reagent

    public static void removeReagent(int id){

        Reagent chemicalToRemove = getReagentById(id);
        reagents.remove(chemicalToRemove);
    }

     //add procedure and observations
    public static void addProcObs(ProcObs procob){
        procobs.add(procob);
    }
    //getall

    public static ArrayList<ProcObs> getAllProcObs(){
        return procobs;
    }
    //remove

    //add
    public static void addConclusion(Conclusion conclusion){
        conclusions.add(conclusion);
    }
    //get all conclusions
    public static ArrayList<Conclusion> getAllConclusions(){
        return conclusions;
    }
    //remove
}
