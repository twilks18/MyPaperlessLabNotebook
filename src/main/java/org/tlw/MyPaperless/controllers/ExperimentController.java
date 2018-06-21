package org.tlw.MyPaperless.controllers;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.tlw.MyPaperless.models.*;
import org.tlw.MyPaperless.models.dao.*;

import org.hibernate.Session;

import org.hibernate.Transaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Configuration;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("experiment")
@SessionAttributes({"firstName","lastName"})
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
    public String processReagentIntroPage(HttpServletRequest request, @PathVariable int id, Model model){

        HttpSession session = request.getSession();

        Intro intro = introDao.findOne(id);
        List<Reagent> reagents = intro.getReagent();
        model.addAttribute("chemicals", reagents);


        model.addAttribute("title",intro.getTitle());
        model.addAttribute("purpose",intro.getPurpose());
        model.addAttribute("materials", intro.getMaterials());
        model.addAttribute("introid",intro);
        model.addAttribute("name", session.getAttribute("firstName") + " " + session.getAttribute("lastName"));

        return "section/introPage";
    }



       // form for title,material, purpose
    @RequestMapping(value = "addIntro", method = RequestMethod.GET)
    public String introForm(Model model){

            model.addAttribute(new Intro());

        return "section/addIntroForm";
    }



     @RequestMapping(value = "addIntro/save",  method = RequestMethod.POST)
    public String processIntroForm(@ModelAttribute @Valid Intro intro, BindingResult bindingResult, HttpServletRequest request, Model model){


        if (bindingResult.hasErrors()){
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
         model.addAttribute("name", session.getAttribute("firstName") + "" + session.getAttribute("lastName"));

         session.setAttribute("intro_key", intro.getId());

        return "redirect:/experiment/addReagent";
    }

    // form for reagent/chemical contents or Chemical Properties table
    @RequestMapping(value = "addReagent", method = RequestMethod.GET)
    public String reagentForm(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("intro_key");
        Intro intro = introDao.findOne(id);
        List<Reagent> reagents = intro.getReagent();

        model.addAttribute("reagents", reagents);
        model.addAttribute(new Reagent());

        return "section/reagentForm";
    }



    //Processing of physical properties table
    @RequestMapping(value ="addReagent/save", method = RequestMethod.POST)
    public String processReagentForm(HttpServletRequest request, @ModelAttribute @Valid Reagent reagent, BindingResult bindingResult, Model model) {

         if (bindingResult.hasErrors()){
            return "section/reagentForm";
        }

            HttpSession session = request.getSession();
            Integer id = (Integer) session.getAttribute("intro_key");
            Intro intro = introDao.findOne(id);

            reagent.setIntro(intro);
            reagentDao.save(reagent);

            List<Reagent> reagents = intro.getReagent();
            model.addAttribute("chemicals", reagents);
            model.addAttribute("id",id);

            return "section/processReagentForm";

    }


/*----------------------------------Procedure-----------------------------------*/

   @RequestMapping(value="procedure", method = RequestMethod.GET)
   public String displayProcedureForm(Model model){

       model.addAttribute(new Proced());

       return "section/procedureForm";
   }

   @RequestMapping(value = "procedure/save", method = RequestMethod.POST)
   public String processProcedureForm(HttpServletRequest request, @RequestParam int pid,@ModelAttribute @Valid Proced proceds, BindingResult bindingResult,Model model){
       HttpSession session = request.getSession();

       if(bindingResult.hasErrors()){
           return "section/procedureForm";
       }

       if(session.getAttribute("intro_key") != null) {

           Integer id = (Integer) session.getAttribute("intro_key");
           Intro intro = introDao.findOne(id);
           proceds.setPid(id);
           proceds.setIntro(intro);
           procedureDao.save(proceds);

           model.addAttribute("proceds", proceds.getProceds());
           model.addAttribute("name", session.getAttribute("firstName") + " " + session.getAttribute("lastName"));
           model.addAttribute("id",id);

           return "section/processProcedureForm";
       }

           Intro introid = introDao.findOne(pid);
           proceds.setIntro(introid);
           proceds.setPid(pid);
           procedureDao.save(proceds);

           model.addAttribute("proceds",proceds.getProceds());
           model.addAttribute("name", session.getAttribute("firstName") + " " + session.getAttribute("lastName"));

           return "section/processProcedureForm";

   }


   @RequestMapping(value = "procedure/{id}", method = RequestMethod.GET)
   public String displayProcedure(HttpServletRequest request, @PathVariable int id, Model model){


       HttpSession session = request.getSession();
       Proced proceds = procedureDao.findOne(id);

       model.addAttribute("proceds",proceds.getProceds());
       model.addAttribute("name", session.getAttribute("firstName") + " " + session.getAttribute("lastName"));

       return "section/processProcedureForm";
   }

    /*------------------------Observations------------------------------------- */

    @RequestMapping(value = "observations/{id}", method = RequestMethod.GET)
    public String displayObservations(HttpServletRequest request,@PathVariable int id, Model model){

        HttpSession session = request.getSession();
        Intro intro = introDao.findOne(id);
        Observations observations = intro.getObservation();

        model.addAttribute("name", session.getAttribute("firstName") + " " + session.getAttribute("lastName"));
        model.addAttribute("observations", observations.getObservation());

        return "section/processObservationsForm";
    }


    @RequestMapping(value ="observations", method = RequestMethod.GET)
    public String obsevationsForm(Model model){

        model.addAttribute(new Observations());
        return "section/observationsForm";
    }


    @RequestMapping(value = "observations/save",method = RequestMethod.POST)
    public String processObservationForm(HttpServletRequest request, @ModelAttribute @Valid Observations observations, @RequestParam int observid, BindingResult bindingResult,Model model){
        HttpSession session = request.getSession();

        if (bindingResult.hasErrors()){
            return "section/observationsForm";
        }

        if(session.getAttribute("intro_key") != null) {

            Integer id = (Integer) session.getAttribute("intro_key");
            Intro intro = introDao.findOne(id);
            observations.setIntro(intro);
            observations.setObservid(id);
            observDao.save(observations);

            model.addAttribute("name", session.getAttribute("firstName") + " " + session.getAttribute("lastName"));
            model.addAttribute("observations", observations.getObservation());
            model.addAttribute("id",id);

            return "section/processObservationsForm";

        }

        Intro introid = introDao.findOne(observid);
        observations.setIntro(introid);
        observations.setObservid(observid);
        observDao.save(observations);

        model.addAttribute("name", session.getAttribute("firstName") + " " + session.getAttribute("lastName"));
        model.addAttribute("observations", observations.getObservation());

        return "section/processObservationsForm";
    }



    /*----------- --------------------Conclusion --------------------------------*/

    @RequestMapping(value = "conclusion/{id}", method = RequestMethod.GET)
    public String displayConclusion(HttpServletRequest request, @PathVariable int id, Model model){

        HttpSession session = request.getSession();
        Intro intro = introDao.findOne(id);
       Conclusions conclude = intro.getConclude();


        model.addAttribute("conclusion", conclude.getConclusion());
        model.addAttribute("id",id);
        model.addAttribute("name", session.getAttribute("firstName") + " " + session.getAttribute("lastName"));

        return "section/processConclusion";
    }


    @RequestMapping(value ="conclusion", method = RequestMethod.GET)
    public String conclusionForm(Model model){

        model.addAttribute(new Conclusions());
        return "section/conclusionForm";
    }


    @RequestMapping(value = "conclusion/save",method = RequestMethod.POST)
    public String processConclusionForm(HttpServletRequest request, @ModelAttribute @Valid Conclusions conclusion,@RequestParam int conid, BindingResult bindingResult,Model model){

        HttpSession session = request.getSession();

        if (bindingResult.hasErrors()){
            return "section/conclusionForm";
        }

        if(session.getAttribute("intro_key") != null){

            Integer id = (Integer)session.getAttribute("intro_key");
            Intro intro = introDao.findOne(id);
            conclusion.setIntro(intro);
            conclusion.setConid(id);
            conclusionsDao.save(conclusion);


            model.addAttribute("name", session.getAttribute("firstName") + " " + session.getAttribute("lastName"));
            model.addAttribute("conclusion", conclusion.getConclusion());
            model.addAttribute("id",id);

            return "section/processConclusion";

        }


        Intro intro = introDao.findOne(conid);
        conclusion.setIntro(intro);
        conclusion.setConid(conid);
        conclusionsDao.save(conclusion);


        model.addAttribute("name", session.getAttribute("firstName") + " " + session.getAttribute("lastName"));
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

        Intro intro = introDao.findById(id);
        model.addAttribute("intro",intro);

        return "section/addIntroForm";
    }

    @RequestMapping(value="conclusion/edit/{conid}")
    public String conclusionFormEdit(@PathVariable int conid, Model model){

        Conclusions conclusion = conclusionsDao.findByConid(conid);
        model.addAttribute("conclusions",conclusion);

        return "section/conclusionForm";
    }

    @RequestMapping(value="procedure/edit/{pid}")
    public String procedureFormEdit(@PathVariable int pid, Model model){

        Proced proceds = procedureDao.findByPid(pid);
        model.addAttribute("proced",proceds);

        return "section/procedureForm";
    }

    @RequestMapping(value="observations/edit/{observid}")
    public String observationFormEdit(@PathVariable int observid, Model model){

        Observations observation = observDao.findByObservid(observid);
        model.addAttribute("observations",observation);

        return "section/observationsForm";
    }
}



