package org.tlw.MyPaperless.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "intro")
public class IntroductionController {

    @RequestMapping(value = "")
    public String introPage(){



        return "section/introPage";
    }

    @RequestMapping(value = "addIntro", method = RequestMethod.GET)
    public String introForm(){

        return "section/introForm";
    }

    @RequestMapping(value = "addIntro", method = RequestMethod.POST)
    public String processIntroForm(@RequestParam String title, @RequestParam String purpose, @RequestParam String materials, Model model){
        model.addAttribute("title",title);
        model.addAttribute("purpose",purpose);
        model.addAttribute("materials",materials);

        return "redirect:";
    }
}
