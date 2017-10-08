package org.tlw.MyPaperless.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tlw.MyPaperless.models.IntroductionModel;
import org.tlw.MyPaperless.models.ReagentModel;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "intro")
public class IntroductionController {

    ArrayList<IntroductionModel> intros = new ArrayList<IntroductionModel>();
    ArrayList<ReagentModel> reagents = new ArrayList<ReagentModel>();

    // final page for intro and reagent contents
    @RequestMapping(value = "")
    public String introPage(Model model){
        model.addAttribute("intros",intros);
        model.addAttribute("reagents",reagents);

        return "section/introPage";
    }

    // form for title,material, purpose
    @RequestMapping(value = "addIntro", method = RequestMethod.GET)
    public String introForm(){

        return "section/introForm";
    }
    // processing of intro form
    @RequestMapping(value = "addIntro", method = RequestMethod.POST)
    public String processIntroForm(@RequestParam String title, @RequestParam String purpose, @RequestParam String materials, Model model){
       IntroductionModel intro = new IntroductionModel( title,purpose,materials);
       intros.add(intro);

       model.addAttribute("intros",intros);

        return "section/processIntro";
    }

    // form for reagent/chemical contents or Chemical Properties table
    @RequestMapping(value = "addReagent", method = RequestMethod.GET)
    public String reagentForm(){

        return "section/reagentForm";
    }

    //Processing of chemical properties table
    @RequestMapping(value ="addReagent", method = RequestMethod.POST)
    public String processReagentForm(@RequestParam String chemical, @RequestParam int density, @RequestParam int mw, @RequestParam String hazard, Model model) {

        ReagentModel reagent = new ReagentModel(chemical,density,mw,hazard);
        reagents.add(reagent);
        model.addAttribute("reagents",reagents);
        return "section/reagentForm";
    }
}
