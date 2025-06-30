package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Programa;
import eldoce.com.ar.ABMexternos.repository.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/programas")
public class ProgramaController {

    @Autowired
    private ProgramaRepository programaRepository;

    @GetMapping
    public String listarProgramas(Model model) {
        List<Programa> programas = programaRepository.findAll();
        model.addAttribute("programas", programas);
        model.addAttribute("programa", new Programa()); // Para formulario nuevo
        return "fragments/abm_programa";
    }

    @PostMapping("/guardar")
    public String guardarPrograma(@ModelAttribute Programa programa) {
        programaRepository.save(programa);
        return "redirect:/programas";
    }

    @GetMapping("/editar/{id}")
    public String editarPrograma(@PathVariable Long id, Model model) {
        Programa programa = programaRepository.findById(id).orElseThrow();
        model.addAttribute("programas", programaRepository.findAll());
        model.addAttribute("programa", programa);
        return "fragments/abm_programa";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPrograma(@PathVariable Long id) {
        programaRepository.deleteById(id);
        return "redirect:/programas";
    }
}
