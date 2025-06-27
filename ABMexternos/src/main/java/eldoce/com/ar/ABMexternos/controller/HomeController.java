package eldoce.com.ar.ABMexternos.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

        @GetMapping("/")
        public String index(@RequestParam(required = false) String view, Model model) {
            if (view == null) {
                view = "default";
            }
            model.addAttribute("partialView", view);
            return "index";
        }

}
