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

        // Redirigir a controlador con lógica real
        switch (view) {
            case "abm_programa":
                return "redirect:/programas";
            case "abm_funcion":
                return "redirect:/funciones";
            case "abm_estado":
                return "redirect:/estados";
            // Agregá más si querés
        }

        // Para vistas estáticas
        model.addAttribute("partialView", view);
        return "index";
    }


}
