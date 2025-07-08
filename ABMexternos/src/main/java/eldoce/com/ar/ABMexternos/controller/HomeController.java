package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Reporta;
import eldoce.com.ar.ABMexternos.model.Usuario;
import eldoce.com.ar.ABMexternos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private ReportaRepository reportaRepository;

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/")
    public String index(@RequestParam(required = false, defaultValue = "nuevo") String view, Model model) {
        model.addAttribute("partialView", view);
        return "index";
    }

    @GetMapping("/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("programas", programaRepository.findAll());
        model.addAttribute("funciones", funcionRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("estados", estadoRepository.findAll());
        return "nuevo";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        model.addAttribute("usuario", usuario);
        model.addAttribute("programas", programaRepository.findAll());
        model.addAttribute("funciones", funcionRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("estados", estadoRepository.findAll());
        return "nuevo";
    }

    @GetMapping("/reporta1")
    public String abmReporta(Model model) {
        model.addAttribute("partialView", "fragments/abm_reporta");
        model.addAttribute("reporta", new Reporta());
        model.addAttribute("listaReporta", reportaRepository.findAll());
        return "index";
    }

}
