package org.tlw.MyPaperless.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.tlw.MyPaperless.models.User;
import org.tlw.MyPaperless.models.dao.IntroDao;
import org.tlw.MyPaperless.models.dao.UserDao;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IntroDao introDao;


    //Create a login form; path: /login
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginForm(Model model){
        model.addAttribute("title", "Login Page");
        model.addAttribute(new User());
        return "paperless/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String welcome(HttpServletRequest request, @ModelAttribute @Valid User newUser, Errors errors,Model model){
// TODO: 2/1/2018 add validation for user and password 


       // String username = request.getParameter("username");


        HttpSession session = request.getSession();

        /* boolean conditional that will check for errors*/

        boolean validFirstName = User.isValidName(request.getParameter("firstname"));
        boolean  validLastName = User.isValidName(request.getParameter("lastname"));
        boolean validUserName = User.isValidUserName(request.getParameter("username"));
        boolean validPassword = User.isValidPassword(request.getParameter("password"));


        if (! validFirstName){
            model.addAttribute("firstname_error", "Invalid First Name!");
            return "paperless/login";

        } else if (! validLastName){
            model.addAttribute("lastname_error", "Invalid Last Name!");
            return "paperless/login";
        }
        else if (! validUserName){
            model.addAttribute("username_error", "Invalid Username!");
            return "paperless/login";
        }
        else if (! validPassword){
            model.addAttribute("password_error", "Invalid Password!");
            return "paperless/login";
        }


         userDao.save(newUser);

        model.addAttribute("heading", "'s Experiments");
        model.addAttribute("name", newUser.getFirstname());
        model.addAttribute("titles",introDao.findAll() );

        session.setAttribute("id", newUser.getUid());
        return "paperless/dashboard";
    }

    // Displays all the experiments
    @RequestMapping(value = "dashboard")
    public String dashboard(Model model){

        model.addAttribute("heading", "'s Experiments");
        model.addAttribute("title","Dashboard");
        model.addAttribute("titles", introDao.findAll());
        return "paperless/dashboard";
    }


    @RequestMapping(value = "removeTitle", method = RequestMethod.GET)
    public String displayRemoveExperiment(Model model){
        model.addAttribute("titles", introDao.findAll());
        model.addAttribute("titlepage", "Remove Experiment");
        return "removeSection/removeIntro";
    }

    @RequestMapping(value = "removeTitle", method = RequestMethod.POST)
    public String processRemoveExperiment(@RequestParam int[] titleIds){

        for (int titleId : titleIds) {
            introDao.delete(titleId);
        }

        return "redirect:dashboard";

    }

}

