package org.tlw.MyPaperless.models;

import java.util.ArrayList;

public class ExperimentContents {


    public static ArrayList<Intro> intros = new ArrayList<>();
    public static ArrayList<Reagent> reagents = new ArrayList<>();
    public static ArrayList<Procobs> procobs = new ArrayList<>();
    public static ArrayList<Conclusion> conclusions = new ArrayList<>();

    /*----------------------Intro(Title) Section----------------------------*/
    //add intro
    public static void addIntro(Intro newIntro){
        intros.add(newIntro);
    }

    //get all intros
    public static ArrayList<Intro> getAllIntros(){
        return intros;
    }

    //remove title
    public static void removeIntro(int id){
        Intro titleToRemove = getIntroById(id);
        intros.remove(titleToRemove);
    }

    //getById (titleId)
    public static Intro getIntroById(int id) {

        Intro introid = null;

        for (Intro theIntro : intros) {
            if (theIntro.getIntroid() == id) {
                introid = theIntro;
            }
        }
        return introid;
    }

    /*----------------------Reagent Section----------------------------*/

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

    /*----------------------Procedure and Observation Section----------------------------*/

     //add procedure and observations
    public static void addProcObs(Procobs procob){
        procobs.add(procob);
    }

    //get all procedures and observations
    public static ArrayList<Procobs> getAllProcObs(){
        return procobs;
    }

    // get procedures and observations by id
    public static Procobs getProcobsById(int id){

        Procobs procobID = null;

        for (Procobs theProcob : procobs){
            if ( theProcob.getProcID() == id){
                procobID = theProcob;
            }
        }
        return procobID;
    }

    //remove procedures and observations
    public static void removeProcob(int id) {
        Procobs procobsToRemove = getProcobsById(id);
        procobs.remove(procobsToRemove);
    }

    /*----------------------Conclusion Section----------------------------*/

    //add conclusion
    public static void addConclusion(Conclusion conclusion){
        conclusions.add(conclusion);
    }
    //get all conclusions
    public static ArrayList<Conclusion> getAllConclusions(){
        return conclusions;
    }

    //get conclusions by id
    public static Conclusion getConclusionById(int id){

       Conclusion conclusionID = null;

        for (Conclusion theConclusion : conclusions){
            if ( theConclusion.getConID() == id){
                conclusionID = theConclusion;
            }
        }
        return conclusionID;
    }

    //remove conclusions
    public static void removeConclusion(int id) {
       Conclusion conclusionToRemove = getConclusionById(id);
        conclusions.remove(conclusionToRemove);
    }
}
