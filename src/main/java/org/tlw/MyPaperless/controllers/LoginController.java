package org.tlw.MyPaperless.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "login")
public class LoginController {



    //Request path:/login/notusable
    @RequestMapping(value = "notusable")
    public String hello(Model model){

        ArrayList<String> experiments = new ArrayList<>();
//        experiments.add("number1");
//        experiments.add("number2");
//        experiments.add("number3");

        model.addAttribute("titles", experiments);
        model.addAttribute("title", "My Paperless Lab Notebook");
        return "paperless/index";
    }

    //Create a login form; path: /login
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String loginForm(){


        return "paperless/login";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String dashboard(@RequestParam String userName,Model model){

        model.addAttribute("title","Welcome");
        model.addAttribute("username",userName);
        return "paperless/dashboard";
    }

}

