package eldoce.com.ar.ABMexternos.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(@RequestParam(required = false, defaultValue = "nuevo") String view, Model model) {
        model.addAttribute("partialView", view);
        return "index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("programas", Collections.emptyList());
        model.addAttribute("funciones", Collections.emptyList());
        model.addAttribute("usuarios", Collections.emptyList());
        return "nuevo";
    }


    @GetMapping("/modificar")
    public String modificar(Model model) {
        //model.addAttribute("usuario", new Usuario());
        model.addAttribute("programas", Collections.emptyList());
        model.addAttribute("funciones", Collections.emptyList());
        model.addAttribute("usuarios", Collections.emptyList());
        return "modificar";
    }




}
