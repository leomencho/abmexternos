package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Reporta;
import eldoce.com.ar.ABMexternos.model.Usuario;
import eldoce.com.ar.ABMexternos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class HomeController {

    @Value("${app.upload.dir}")
    private String uploadDir;

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

    @Autowired
    private RecursoRepository recursoRepository;

    @GetMapping("/")
    public String index(@RequestParam(required = false, defaultValue = "nuevo") String view, Model model) {
        model.addAttribute("partialView", view);
        return "index";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(@RequestParam(value = "id", required = false) Long id, Model model) {
        Usuario usuario = (id != null)
                ? usuarioRepository.findById(id).orElse(new Usuario())
                : new Usuario();

        model.addAttribute("usuario", usuario);
        model.addAttribute("estados", estadoRepository.findAll());
        model.addAttribute("recursos", recursoRepository.findAll());
        model.addAttribute("programas", programaRepository.findAll());
        model.addAttribute("reportas", reportaRepository.findAll());
        model.addAttribute("funciones", funcionRepository.findAll()); // 👈 esta línea

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
        model.addAttribute("recursos", recursoRepository.findAll());
        model.addAttribute("reportas", reportaRepository.findAll());
        return "nuevo";
    }

    @GetMapping("/ver")
    public String verUsuario(@RequestParam("id") Long id, Model model) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            model.addAttribute("usuario", usuarioOpt.get());
            return "ver";
        } else {
            return "redirect:/buscar";
        }
    }


}
