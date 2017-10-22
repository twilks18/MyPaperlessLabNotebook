package org.tlw.MyPaperless.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tlw.MyPaperless.models.ExperimentContents;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "login")
public class LoginController {


    //Create a login form; path: /login
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loginForm(){


        return "paperless/login";
    }

    @RequestMapping(value = "dashboard")
    public String dashboard(@RequestParam String userName,Model model){

        model.addAttribute("title","Welcome");
        model.addAttribute("username",userName);
        model.addAttribute("titles", ExperimentContents.getAllIntros());
        return "paperless/dashboard";
    }

}

