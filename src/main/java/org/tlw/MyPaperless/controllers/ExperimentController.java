package org.tlw.MyPaperless.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tlw.MyPaperless.models.*;
import org.tlw.MyPaperless.models.dao.ConclusionDao;
import org.tlw.MyPaperless.models.dao.IntroDao;
import org.tlw.MyPaperless.models.dao.ProcobsDao;
import org.tlw.MyPaperless.models.dao.ReagentDao;

import javax.validation.Valid;

@Controller
public class ExperimentController {

    @Autowired
    private ReagentDao reagentDao;

    @Autowired
    private ProcobsDao procobsDao;

    @Autowired
    private IntroDao introDao;

    @Autowired
    private ConclusionDao conclusionDao;
    /*----------- Sections ------------------*/


    // final page for intro and reagent contents
    @RequestMapping(value = "intro")
    public String introPage(Model model){
        // TODO fix the parameter for intro content, should not pass in a list BAD!!
        model.addAttribute("intros", introDao.findAll());
        model.addAttribute("reagents",ExperimentContents.getAllReagents());

        return "section/introPage";
    }


    // form for title,material, purpose
    @RequestMapping(value = "addIntro", method = RequestMethod.GET)
    public String introForm(Model model){

            model.addAttribute(new Intro());
        return "section/introForm";
    }


    // processing of form for title,material, purpose
    @RequestMapping(value = "addIntro", method = RequestMethod.POST)
    public String processIntroForm(@ModelAttribute @Valid Intro intro, Errors errors, Model model){

        if (errors.hasErrors()){
            return "section/introForm";
        }

        introDao.save(intro);
       model.addAttribute("title",intro.getTitle());
       model.addAttribute("purpose",intro.getPurpose());
       model.addAttribute("materials", intro.getMaterials());
        return "section/processIntro";
    }



    // form for reagent/chemical contents or Chemical Properties table
    @RequestMapping(value = "addReagent", method = RequestMethod.GET)
    public String reagentForm(Model model){

        model.addAttribute(new Reagent());
        return "section/reagentForm";
    }

    //Processing of physical properties table
    @RequestMapping(value ="addReagent", method = RequestMethod.POST)
    public String processReagentForm(@ModelAttribute @Valid Reagent reagent, Errors errors, Model model) {

        if (errors.hasErrors()){
            return "section/reagentForm";
        }

        reagentDao.save(reagent);
        model.addAttribute("reagents",ExperimentContents.getAllReagents());

        return "section/reagentForm";

    }

    //form for procedure and observations
    @RequestMapping(value = "procAndObs",method = RequestMethod.GET)
    public String procAndObsForm(Model model){

       model.addAttribute(new Procobs());
        return "section/procAndObsForm";
    }

    @RequestMapping(value = "procAndObs", method = RequestMethod.POST)
    public String processProcAndObsForm(@ModelAttribute @Valid Procobs procob, Errors errors, Model model){

        if (errors.hasErrors()){
            return "section/procAndObsForm";
        }


        procobsDao.save(procob);

        model.addAttribute("procob", procob.getProcedure());
        model.addAttribute("procob", procob.getObservations());

        return "section/procAndObsPage";
    }

    /*---form for conclusion---*/
    @RequestMapping(value ="conclusion", method = RequestMethod.GET)
    public String conclusionForm(Model model){

        model.addAttribute(new Conclusion());
        return "section/conclusionForm";
    }

    /*----processing conclusion*---*/
    @RequestMapping(value = "conclusion",method = RequestMethod.POST)
    public String processConclusionForm(@ModelAttribute @Valid  Conclusion conclude, Errors errors,Model model){

        if (errors.hasErrors()){
            return "section/conclusionForm";
        }

        conclusionDao.save(conclude);


        model.addAttribute("conclusions", ExperimentContents.getAllConclusions());
        return "section/conclusionPage";
    }

                             /*----------- Remove Section ------------------*/
    @RequestMapping(value = "removeReagent", method = RequestMethod.GET)
    public String displayRemoveReagent(Model model){
        model.addAttribute("reagents", ExperimentContents.getAllReagents());

        return "removeSection/removeReagent";
    }

    @RequestMapping(value = "removeReagent", method = RequestMethod.POST)
    public String processRemoveReagent(@RequestParam int[] chemIds){

        for (int chemId : chemIds) {
            ExperimentContents.removeReagent(chemId);
        }

        return "redirect:addReagent";

    }
}
