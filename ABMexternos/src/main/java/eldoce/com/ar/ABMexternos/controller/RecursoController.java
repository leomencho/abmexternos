package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Recurso;
import eldoce.com.ar.ABMexternos.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/recursos")
public class RecursoController {

    @Autowired
    private RecursoRepository recursoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("recurso", new Recurso());
        model.addAttribute("listaRecursos", recursoRepository.findAll());
        return "fragments/abm_recurso";
    }

    @PostMapping
    public String guardar(@ModelAttribute Recurso recurso, RedirectAttributes redirect) {
        recursoRepository.save(recurso);
        redirect.addFlashAttribute("exito", "Guardado correctamente");
        return "redirect:/recursos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Recurso recurso = recursoRepository.findById(id).orElseThrow();
        model.addAttribute("recurso", recurso);
        model.addAttribute("listaRecursos", recursoRepository.findAll());
        return "fragments/abm_recurso";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        recursoRepository.deleteById(id);
        redirect.addFlashAttribute("exito", "Eliminado correctamente");
        return "redirect:/recursos";
    }
}
