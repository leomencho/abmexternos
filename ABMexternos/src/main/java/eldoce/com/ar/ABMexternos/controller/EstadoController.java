package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Estado;
import eldoce.com.ar.ABMexternos.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("estados", estadoRepository.findAll());
        model.addAttribute("estado", new Estado());
        return "fragments/abm_estado";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Estado estado) {
        estadoRepository.save(estado);
        return "redirect:/estados";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("estado", estadoRepository.findById(id).orElseThrow());
        model.addAttribute("estados", estadoRepository.findAll());
        return "fragments/abm_estado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        estadoRepository.deleteById(id);
        return "redirect:/estados";
    }
}
