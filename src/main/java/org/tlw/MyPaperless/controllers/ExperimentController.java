package org.tlw.MyPaperless.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.tlw.MyPaperless.models.*;
import org.tlw.MyPaperless.models.dao.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("introid")
@RequestMapping("experiment")
public class ExperimentController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReagentDao reagentDao;

    @Autowired
    private ProcobsDao procobsDao;

    @Autowired
    private IntroDao introDao;

    @Autowired
    private ConclusionDao conclusionDao;

    /*----------- Sections ------------------*/


       // form for title,material, purpose
    @RequestMapping(value = "addIntro", method = RequestMethod.GET)
    public String introForm(Model model){

            model.addAttribute(new Intro());

        return "section/addIntroForm";
    }

     @RequestMapping(value = "addIntro",  method = RequestMethod.POST)
    public String processIntroForm(@ModelAttribute @Valid Intro intro, HttpServletRequest request, Errors errors, Model model){


        if (errors.hasErrors()){
            return "section/addIntroForm";
        }

         HttpSession session = request.getSession();
         Integer id = (Integer)session.getAttribute("id");
         User user = userDao.findOne(id);
         intro.setUser(user);
        introDao.save(intro);

        model.addAttribute("title",intro.getTitle());
        model.addAttribute("purpose",intro.getPurpose());
        model.addAttribute("materials", intro.getMaterials());
        model.addAttribute("introid", id);


        return "section/processIntro";
    }


    // form for reagent/chemical contents or Chemical Properties table
    @RequestMapping(value = "addReagent", method = RequestMethod.GET)
    public String reagentForm(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("id");
        Intro intro = introDao.findOne(id);
        List<Reagent> reagents = intro.getReagent();
        model.addAttribute("reagents", reagents);


        model.addAttribute("number", "The number is" + id);
        model.addAttribute(new Reagent());
//        Intro intro = introDao.findOne(introid);


        return "section/reagentForm";
    }

    //Processing of physical properties table
    @RequestMapping(value ="addReagent", method = RequestMethod.POST)
    public String processReagentForm(HttpServletRequest request, @ModelAttribute @Valid Reagent reagent, Errors errors, Model model) {

        boolean validChemName = Reagent.isValidChemName(request.getParameter("chemName"));
        boolean validMW = Reagent.isValidNumber(request.getParameter("mw"));
        boolean validDensity = Reagent.isValidNumber(request.getParameter("density"));

        if(! validChemName){

            model.addAttribute("chemname_error", "Invalid chemical name");
            return "section/reagentForm";
        } else if(! validMW){
            model.addAttribute("mw_error", "Must enter a numerical value greater than 1");
            return "section/reagentForm";
        } else if(! validDensity){
            model.addAttribute("density_error", "Must enter a numerical value greater than 1");
            return "section/reagentForm";

         } else if (errors.hasErrors()){
            return "section/reagentForm";
        }

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("id");
        Intro intro = introDao.findOne(id);

        reagent.setIntro(intro);
        reagentDao.save(reagent);

        List<Reagent> reagents = intro.getReagent();
        model.addAttribute("chemicals", reagents);
        model.addAttribute("reagents", reagent.getChemName() + " " + id + " has been added!");


        return "section/test";

    }


    // processing of form for title,material, purpose,  and reagent contents
    @RequestMapping(value = "introReagentPage", method = RequestMethod.GET) // may need to add this " method = RequestMethod.POST" back
    public String processReagentIntroPage(HttpServletRequest request, Model model){


        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("id");
        Intro intro = introDao.findOne(id);
        List<Reagent> reagents = intro.getReagent();
        model.addAttribute("chemicals", reagents);


        model.addAttribute("title",intro.getTitle());
        model.addAttribute("purpose",intro.getPurpose());
        model.addAttribute("materials", intro.getMaterials());

        return "section/introPage";
    }

    //form for procedure and observations
    @RequestMapping(value = "procAndObs",method = RequestMethod.GET)
    public String procAndObsForm(Model model){

       model.addAttribute(new Procobs());
        return "section/procAndObsForm";
    }

    @RequestMapping(value = "procAndObs", method = RequestMethod.POST)
    public String processProcAndObsForm(HttpServletRequest request, @ModelAttribute @Valid Procobs procob, Errors errors, Model model){

        if (errors.hasErrors()){
            return "section/procAndObsForm";
        }

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("id");
        Intro intro = introDao.findOne(id);
        procob.setIntro(intro);
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
    public String processConclusionForm(HttpServletRequest request, @ModelAttribute @Valid Conclusion conclude, Errors errors,Model model){

        if (errors.hasErrors()){
            return "section/conclusionForm";
        }

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("id");
        Intro intro = introDao.findOne(id);
        conclude.setIntro(intro);

        conclusionDao.save(conclude);


        model.addAttribute("conclusions", conclusionDao.findAll());
        return "section/conclusionPage";
    }

                             /*----------- Remove Section ------------------*/
    @RequestMapping(value = "removeReagent", method = RequestMethod.GET)
    public String displayRemoveReagent(HttpServletRequest request,  Model model){

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("id");
       Intro intro = introDao.findOne(id);
        List<Reagent> reagents = intro.getReagent();
        model.addAttribute("reagents", reagents);

        return "removeSection/removeReagent";
    }

    @RequestMapping(value = "removeReagent", method = RequestMethod.POST)
    public String processRemoveReagent(@RequestParam int[] chemIds){

        for (int chemId : chemIds) {
            reagentDao.delete(chemId);
        }

        return "redirect:removeReagent";

    }
}
