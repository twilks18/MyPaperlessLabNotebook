import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;w bnme org.tlw.MyPaperless.controllers;

23w4r57uop[springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.tlw.MyPaperless.models.IntroductionModel;

import java.util.ArrayList;


@Controller
@RequestMapping(value="intro")
public class IntroductionController {

    static ArrayList <IntroductionModel> intros = new ArrayList<>();


    @RequestMapping(value = "addIntro", method = RequestMethod.POST)
    public String introPage(@RequestParam String title, @RequestParam String purpose, @RequestParam String materials,
                            @RequestParam String chemical, @RequestParam int mw, @RequestParam int density,@RequestParam String hazard, Model model) {

        IntroductionModel intro = new IntroductionModel(title,purpose,materials,chemical,density,mw,hazard);
        intros.add(intro);

        model.addAttribute("intros",intros);
        model.addAttribute("title", title);
        model.addAttribute("purpose", purpose);
        model.addAttribute("materials", materials);

        return "section/introPage";/
    }

    @RequestMapping(value = "addIntro", method = RequestMethod.GET)
    public String introForm() {
        return "section/introForm";
    }


}
