package org.tlw.MyPaperless.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
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
@RequestMapping("experiment")
public class ExperimentController {



    @Autowired
    private UserDao userDao;

    @Autowired
    private ReagentDao reagentDao;

    @Autowired
    private ProcedureDao procedureDao;

    @Autowired
    private IntroDao introDao;

    @Autowired
    private ConclusionsDao conclusionsDao;

    @Autowired
    private ObservationsDao observDao;


    /*----------- --------------------Introduction --------------------------------*/


    @RequestMapping(value = "introReagentPage/{id}", method = RequestMethod.GET)
    public String processReagentIntroPage(@PathVariable int id, Model model){

        Intro intro = introDao.findOne(id);
        List<Reagent> reagents = intro.getReagent();
        model.addAttribute("chemicals", reagents);


        model.addAttribute("title",intro.getTitle());
        model.addAttribute("purpose",intro.getPurpose());
        model.addAttribute("materials", intro.getMaterials());

        return "section/introPage";
    }



       // form for title,material, purpose
    @RequestMapping(value = "addIntro", method = RequestMethod.GET)
    public String introForm(Model model){

            model.addAttribute(new Intro());

        return "section/addIntroForm";
    }



     @RequestMapping(value = "addIntro/save",  method = RequestMethod.POST)
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

         session.setAttribute("intro_key", intro.getId());

        return "section/processIntro";
    }




    // form for reagent/chemical contents or Chemical Properties table
    @RequestMapping(value = "addReagent", method = RequestMethod.GET)
    public String reagentForm(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("intro_key");
        Intro intro = introDao.findOne(id);
        List<Reagent> reagents = intro.getReagent();

        model.addAttribute("reagents", reagents);
        model.addAttribute("number", "The number is" + id);
        model.addAttribute(new Reagent());

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
            Integer id = (Integer) session.getAttribute("intro_key");
            Intro intro = introDao.findOne(id);

            reagent.setIntro(intro);
            reagentDao.save(reagent);

            List<Reagent> reagents = intro.getReagent();
            model.addAttribute("chemicals", reagents);
            model.addAttribute("reagents", reagent.getChemName() + " " + id + " has been added!");


            return "section/processReagentForm";

    }

/*----------------------------------Procedure-----------------------------------*/

   @RequestMapping(value="procedure", method = RequestMethod.GET)
   public String displayProcedureForm(Model model){

       model.addAttribute(new Proced ());

       return "section/procedureForm";
   }

   @RequestMapping(value = "procedure/save", method = RequestMethod.POST)
   public String processProcedureForm(HttpServletRequest request, @ModelAttribute @Valid Proced proceds, Errors errors,Model model){

       if(errors.hasErrors()){

           model.addAttribute("errors","Please add a procedure");
           return "section/procedureForm";
       }

       HttpSession session = request.getSession();
       Integer id = (Integer)session.getAttribute("intro_key");
       Intro intro = introDao.findOne(id);
       proceds.setIntro(intro);

       procedureDao.save(proceds);

       model.addAttribute("proceds",proceds.getProceds());


       return "section/processProcedureForm";
   }

   @RequestMapping(value = "procedure/{id}", method = RequestMethod.GET)
   public String displayProcedure(@PathVariable int id, Model model){

       Intro intro =introDao.findOne(id);
       Proced proceds = intro.getProceds();

       model.addAttribute("proceds",proceds.getProceds());

       return "section/processProcedureForm";
   }

    /*------------------------Observations------------------------------------- */

    @RequestMapping(value = "observations/{id}", method = RequestMethod.GET)
    public String displayObservations(@PathVariable int id, Model model){


        Intro intro = introDao.findOne(id);
        Observations observations = intro.getObservation();


        model.addAttribute("observations", observations.getObservation());

        return "section/processObservationsForm";
    }


    @RequestMapping(value ="observations", method = RequestMethod.GET)
    public String obsevationsForm(Model model){

        model.addAttribute(new Observations());
        return "section/observationsForm";
    }


    @RequestMapping(value = "observations/save",method = RequestMethod.POST)
    public String processObservationForm(HttpServletRequest request, @ModelAttribute @Valid Observations observations, Errors errors,Model model){

        if (errors.hasErrors()){
            return "section/observationsForm";
        }

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("intro_key");
        Intro intro = introDao.findOne(id);
        observations.setIntro(intro);

        observDao.save(observations);


        model.addAttribute("observations", observations.getObservation());
        return "section/processObservationsForm";
    }



    /*----------- --------------------Conclusion --------------------------------*/

    @RequestMapping(value = "conclusion/{id}", method = RequestMethod.GET)
    public String displayConclusion(@PathVariable int id, Model model){


        Intro intro = introDao.findOne(id);
       Conclusions conclude = intro.getConclude();


        model.addAttribute("conclusion", conclude.getConclusion());
        model.addAttribute("id",id);

        return "section/example";
    }


    @RequestMapping(value ="conclusion", method = RequestMethod.GET)
    public String conclusionForm(Model model){

        model.addAttribute(new Conclusions());
        return "section/conclusionForm";
    }


    @RequestMapping(value = "conclusion/save",method = RequestMethod.POST)
    public String processConclusionForm(HttpServletRequest request, @ModelAttribute @Valid Conclusions conclusion, Errors errors,Model model){

        if (errors.hasErrors()){
            return "section/conclusionForm";
        }

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("intro_key");
        Intro intro = introDao.findOne(id);
        conclusion.setIntro(intro);
        conclusionsDao.save(conclusion);



        model.addAttribute("conclusion", conclusion.getConclusion());
        return "section/processConclusion";
    }




    /*---------------------------- Remove Section -----------------------------------*/


    @RequestMapping(value = "removeReagent", method = RequestMethod.GET)
    public String displayRemoveReagent(HttpServletRequest request,  Model model){

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("intro_key");
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

     /*------------------------------ Edit Section ----------------------------------- */

    @RequestMapping(value="addIntro/edit/{id}")
    public String addIntroFormEdit(@PathVariable int id, Model model){

        Intro intro = introDao.findOne(id);
        model.addAttribute("intro",intro);

        return "section/addIntroForm";


    }
}



