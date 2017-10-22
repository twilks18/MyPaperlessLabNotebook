package org.tlw.MyPaperless.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tlw.MyPaperless.models.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "experiment")
public class ExperimentController {

    // final page for intro and reagent contents
    @RequestMapping(value = "")
    public String introPage(Model model){
        // TODO fix the parameter for intro content, should not pass in a list BAD!!
        model.addAttribute("intros",ExperimentContents.getAllIntros());
        model.addAttribute("reagents",ExperimentContents.getAllReagents());

        return "section/introPage";
    }

    // form for title,material, purpose
    @RequestMapping(value = "addIntro", method = RequestMethod.GET)
    public String introForm(){

        return "section/introForm";
    }
    // processing of form for title,material, purpose
    @RequestMapping(value = "addIntro", method = RequestMethod.POST)
    public String processIntroForm(@RequestParam String title, @RequestParam String purpose, @RequestParam String materials, Model model){
       IntroductionModel intro = new IntroductionModel( title,purpose,materials);
        ExperimentContents.addIntro(intro);

       model.addAttribute("title",title);
       model.addAttribute("purpose",purpose);
       model.addAttribute("materials", materials);
        return "section/processIntro";
    }

    // form for reagent/chemical contents or Chemical Properties table
    @RequestMapping(value = "addReagent", method = RequestMethod.GET)
    public String reagentForm(){

        return "section/reagentForm";
    }

    //Processing of physical properties table
    @RequestMapping(value ="addReagent", method = RequestMethod.POST)
    public String processReagentForm(@RequestParam String chemical, @RequestParam int density, @RequestParam int mw, @RequestParam String hazard, Model model) {

        ReagentModel reagents = new ReagentModel(chemical,density,mw,hazard);
        ExperimentContents.addReagent(reagents);
        model.addAttribute("reagents",ExperimentContents.getAllReagents());
        return "section/reagentForm";
    }

    //form for procedure and observations
    @RequestMapping(value = "procAndObsPage",method = RequestMethod.GET)
    public String procAndObsForm(){
        return "section/procAndObsForm";
    }

    @RequestMapping(value = "procAndObsPage", method = RequestMethod.POST)
    public String processProcAndObsForm( @RequestParam String procedure, @RequestParam String observations,Model model){

        ProcObsModel procob = new ProcObsModel(procedure,observations);
        ExperimentContents.addProcObs(procob);

        // TODO check the template for necessary corrections
        model.addAttribute("procobs", procob);

        return "section/procAndObsPage";
    }

    //form for conclusion
    @RequestMapping(value ="conclusionPage", method = RequestMethod.GET)
    public String conclusionForm(){
        return "section/conclusionForm";
    }

    //processing conclusion
    @RequestMapping(value = "conclusionPage",method = RequestMethod.POST)
    public String processConclusionForm(@RequestParam String conclusion,Model model){
        ConclusionModel conclude = new ConclusionModel(conclusion);
        ExperimentContents.addConclusion(conclude);

        // TODO check template for necessary corrections
        model.addAttribute("conclusions",conclude);
        return "section/conclusionPage";
    }
}
