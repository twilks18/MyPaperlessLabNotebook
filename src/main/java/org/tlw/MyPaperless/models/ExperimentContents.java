package org.tlw.MyPaperless.models;

import java.util.ArrayList;

public class ExperimentContents {


    public static ArrayList<IntroductionModel> intros = new ArrayList<>();
    public static ArrayList<ReagentModel> reagents = new ArrayList<>();
    public static ArrayList<ProcObsModel> procobs = new ArrayList<>();
    public static ArrayList<ConclusionModel> conclusions = new ArrayList<>();

    //add intro
    public static void addIntro(IntroductionModel newIntro){
        intros.add(newIntro);
    }

    //get all intros
    public static ArrayList<IntroductionModel> getAllIntros(){
        return intros;
    }

    //remove


    //getById

    //get all reagents
    public static ArrayList<ReagentModel> getAllReagents(){
        return reagents;
    }

    //add reagents
    public static void addReagent(ReagentModel reagent){
        reagents.add(reagent);
    }

    //get reagents by id


    //remove reagent

     //add procedure and observations
    public static void addProcObs(ProcObsModel procob){
        procobs.add(procob);
    }
    //getall

    public static ArrayList<ProcObsModel> getAllProcObs(){
        return procobs;
    }
    //remove

    //add
    public static void addConclusion(ConclusionModel conclusion){
        conclusions.add(conclusion);
    }
    //get all conclusions
    public static ArrayList<ConclusionModel> getAllConclusions(){
        return conclusions;
    }
    //remove
}
