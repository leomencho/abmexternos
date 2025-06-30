package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Funcion;
import eldoce.com.ar.ABMexternos.repository.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/funciones")
public class FuncionController {

    @Autowired
    private FuncionRepository funcionRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("funciones", funcionRepository.findAll());
        model.addAttribute("funcion", new Funcion());
        return "fragments/abm_funcion";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Funcion funcion) {
        funcionRepository.save(funcion);
        return "redirect:/funciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("funcion", funcionRepository.findById(id).orElseThrow());
        model.addAttribute("funciones", funcionRepository.findAll());
        return "fragments/abm_funcion";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        funcionRepository.deleteById(id);
        return "redirect:/funciones";
    }
}
