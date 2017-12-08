package org.tlw.MyPaperless.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tlw.MyPaperless.models.ExperimentContents;
import org.tlw.MyPaperless.models.NewUser;
import org.tlw.MyPaperless.models.User;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller

public class LoginController {


    //Create a login form; path: /login
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginForm(Model model){
        model.addAttribute("title", "Login Page");
        model.addAttribute(new User());
        return "paperless/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String welcome(@ModelAttribute @Valid User newUser,Errors errors,Model model){

        /* boolean conditional that will check for errors*/
        if (errors.hasErrors()){
            model.addAttribute("title", "Login Page");
            return "paperless/login";
        }

         NewUser.addUser(newUser);

        model.addAttribute("title","Welcome");
        model.addAttribute("username", newUser.getName());
        model.addAttribute("titles", ExperimentContents.getAllIntros());
        return "paperless/dashboard";
    }

    // Displays all the experiments
    @RequestMapping(value = "dashboard")
    public String dashboard(Model model){
        model.addAttribute("title","Dashboard");
        model.addAttribute("titles", ExperimentContents.getAllIntros());
        return "paperless/dashboard";
    }


    @RequestMapping(value = "removeTitle", method = RequestMethod.GET)
    public String displayRemoveExperiment(Model model){
        model.addAttribute("titles", ExperimentContents.getAllIntros());
        model.addAttribute("titlepage", "Remove Experiment");
        return "removeSection/removeIntro";
    }

    @RequestMapping(value = "removeTitle", method = RequestMethod.POST)
    public String processRemoveExperiment(@RequestParam int[] titleIds){

        for (int titleId : titleIds) {
            ExperimentContents.removeTitle(titleId);
        }

        return "redirect:dashboard";

    }

}

