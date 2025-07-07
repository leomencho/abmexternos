package eldoce.com.ar.ABMexternos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioVistaController {

    @GetMapping("/usuarios/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        return "nuevo"; // Asegurate de tener templates/usuarios/nuevo.html
    }
}
