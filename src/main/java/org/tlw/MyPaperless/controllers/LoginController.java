package org.tlw.MyPaperless.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.tlw.MyPaperless.models.Intro;
import org.tlw.MyPaperless.models.User;
import org.tlw.MyPaperless.models.dao.IntroDao;
import org.tlw.MyPaperless.models.dao.UserDao;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IntroDao introDao;

    /*---------------------------- Home Page-----------------------------***/
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String homePage(){
        return "paperless/landingPage";
    }


    /*----------- --------------------Sign Up--------------------------------*/
    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signupForm(Model model){
        model.addAttribute("title", "Sign Up ");
        model.addAttribute(new User());
        return "paperless/signup";
    }


    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String welcome(HttpServletRequest request, @ModelAttribute User newUser,Model model){
// TODO: 2/1/2018 add validation for user and password 

        HttpSession session = request.getSession();

        /* boolean conditional that will check for errors*/

        boolean validFirstName = User.isValidName(request.getParameter("firstname"));
        boolean  validLastName = User.isValidName(request.getParameter("lastname"));
        boolean validUserName = User.isValidUserName(request.getParameter("username"));
        boolean validPassword = User.isValidPassword(request.getParameter("password"));
        boolean verifyPassword = request.getParameter("password").equals(request.getParameter("verify"));

        Integer newuserId = newUser.getUid();
        if (! validFirstName){
            model.addAttribute("error", "Invalid First Name!");
            return "paperless/signup";

        } else if (! validLastName){
            model.addAttribute("error", "Invalid Last Name!");
            return "paperless/signup";
        }
        else if (! validUserName){
            model.addAttribute("error", "Invalid Username!");
            return "paperless/signup";
        }
        else if (! validPassword){
            model.addAttribute("error", "Invalid Password!");
            return "paperless/signup";
        }

        else if(!verifyPassword){
            model.addAttribute("error", "Passwords don't match");
            return "paperless/signup";
        }else

         userDao.save(newUser);


        session.setAttribute("id", newUser.getUid());
        return "redirect:/dashboard";
    }



    /*----------- --------------------Login--------------------------------*/



    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginForm(Model model){
        return "paperless/login";
    }



    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");


        try {

            if (username == "" || password == "") {
                model.addAttribute("error", "Login requires a username and password");
                return "paperless/login";
            }


            User returnUser = userDao.findByUsername(username);

            Integer userid = returnUser.getUid();

            if (returnUser != null && returnUser.getPassword().equals(password)) {

                session.setAttribute("id", userid);
                return "redirect:dashboard";
            }else if
                    (returnUser !=null && !returnUser.getPassword().equals(password)) {
                model.addAttribute("error", "Invalid password");
                return "paperless/login";

            }
        } catch (NullPointerException e) {
            model.addAttribute("error", "Username does not exist");

            return "paperless/login";

        }

        return "paperless/login";
    }



    /*----------- --------------------Dashboard--------------------------------*/


    @RequestMapping(value = "dashboard")
    public String dashboard(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("id");
        User user = userDao.findByUid(id);
        Intro intro = introDao.findOne(id);
        List<Intro> titles = user.getIntro();


        model.addAttribute("name", user.getFirstname());
        model.addAttribute("title","Dashboard");
        model.addAttribute("titles", titles);
        model.addAttribute("user", id);
        return "paperless/dashboard";
    }



    /*----------- --------------------Remove Experiment--------------------------------*/


    @RequestMapping(value = "removeExperiment", method = RequestMethod.GET)
    public String displayRemoveExperiment(HttpServletRequest request,Model model){

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("id");
        User user = userDao.findByUid(id);
        Intro intro = introDao.findOne(id);
        List<Intro> titles = user.getIntro();

        model.addAttribute("titles", titles);
        model.addAttribute("titlepage", "Remove Experiment");
        return "removeSection/removeExperiment";
    }



    @RequestMapping(value = "removeExperiment", method = RequestMethod.POST)
    public String processRemoveExperiment(@RequestParam int[] titleIds){

        for (int titleId : titleIds) {
            introDao.delete(titleId);
        }

        return "redirect:dashboard";

    }


    /*----------- --------------------Logout--------------------------------*/

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

}

