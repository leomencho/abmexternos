package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Perfil;
import eldoce.com.ar.ABMexternos.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("perfiles", perfilRepository.findAll());
        model.addAttribute("perfil", new Perfil());
        return "fragments/abm_perfil";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Perfil perfil) {
        perfilRepository.save(perfil);
        return "redirect:/perfiles";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("perfil", perfilRepository.findById(id).orElseThrow());
        model.addAttribute("perfiles", perfilRepository.findAll());
        return "fragments/abm_perfil";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        perfilRepository.deleteById(id);
        return "redirect:/perfiles";
    }
}
