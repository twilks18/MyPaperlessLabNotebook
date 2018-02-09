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
@SessionAttributes("name")
public class LoginController extends AbstractController{

    @Autowired
    private UserDao userDao;

    @Autowired
    private IntroDao introDao;


    //Signup form; path: /signup
    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signupForm(Model model){
        model.addAttribute("title", "Login Page");
        model.addAttribute(new User());
        return "paperless/signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String welcome(HttpServletRequest request, @ModelAttribute @Valid User newUser, Errors errors,Model model){
// TODO: 2/1/2018 add validation for user and password 

        HttpSession session = request.getSession();

        /* boolean conditional that will check for errors*/

        boolean validFirstName = User.isValidName(request.getParameter("firstname"));
        boolean  validLastName = User.isValidName(request.getParameter("lastname"));
        boolean validUserName = User.isValidUserName(request.getParameter("username"));
        boolean validPassword = User.isValidPassword(request.getParameter("password"));


        if (! validFirstName){
            model.addAttribute("firstname_error", "Invalid First Name!");
            return "paperless/signup";

        } else if (! validLastName){
            model.addAttribute("lastname_error", "Invalid Last Name!");
            return "paperless/signup";
        }
        else if (! validUserName){
            model.addAttribute("username_error", "Invalid Username!");
            return "paperless/signup";
        }
        else if (! validPassword){
            model.addAttribute("password_error", "Invalid Password!");
            return "paperless/signup";
        }


         userDao.save(newUser);

//        model.addAttribute("heading", "'s Experiments");
//        model.addAttribute("name", newUser.getFirstname());

        session.setAttribute("id", newUser.getUid());
        return "redirect:/dashboard";
    }

    //Login form; path: /login
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginForm(Model model){

        model.addAttribute("title", "Welcome Back!!! Please Login Below");

        return "paperless/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();

        String username = request.getParameter("username");
       String password = request.getParameter("password");


        User user = userDao.findByUsername(username);
        Integer userid = user.getUid();
        session.setAttribute("userkey",userid);

        if (user != null && user.getPassword().equals(password)) {

//            model.addAttribute("name", user.getFirstname());
//            model.addAttribute("user", userid);
            session.setAttribute("id",userid);
            return "redirect:dashboard";
        }
        model.addAttribute("error", "Either  your username or password is incorrect");

        return "paperless/login";

    }

    // Displays all the experiments
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


    @RequestMapping(value = "removeTitle", method = RequestMethod.GET)
    public String displayRemoveExperiment(HttpServletRequest request,Model model){

        HttpSession session = request.getSession();
        Integer id = (Integer)session.getAttribute("id");
        User user = userDao.findByUid(id);
        Intro intro = introDao.findOne(id);
        List<Intro> titles = user.getIntro();

        model.addAttribute("titles", titles);
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
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

}

