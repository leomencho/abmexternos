package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Reporta;
import eldoce.com.ar.ABMexternos.repository.ReportaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reporta")
public class ReportaController {

    @Autowired
    private ReportaRepository reportaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("reporta", new Reporta());
        model.addAttribute("listaReporta", reportaRepository.findAll());
        return "fragments/abm_reporta";
    }

    @PostMapping
    public String guardar(@ModelAttribute Reporta reporta, RedirectAttributes redirect) {
        reportaRepository.save(reporta);
        redirect.addFlashAttribute("exito", "Guardado correctamente");
        return "redirect:/reporta";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Reporta reporta = reportaRepository.findById(id).orElseThrow();
        model.addAttribute("reporta", reporta);
        model.addAttribute("listaReporta", reportaRepository.findAll());
        return "fragments/abm_reporta";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        reportaRepository.deleteById(id);
        redirect.addFlashAttribute("exito", "Eliminado correctamente");
        return "redirect:/reporta";
    }
}
