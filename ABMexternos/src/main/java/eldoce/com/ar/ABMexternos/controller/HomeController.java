package eldoce.com.ar.ABMexternos.controller;


import eldoce.com.ar.ABMexternos.repository.EstadoRepository;
import eldoce.com.ar.ABMexternos.repository.FuncionRepository;
import eldoce.com.ar.ABMexternos.repository.ProgramaRepository;
import eldoce.com.ar.ABMexternos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProgramaRepository programaRepository;

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
        model.addAttribute("programas", programaRepository.findAll());
        model.addAttribute("funciones", funcionRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("estados", estadoRepository.findAll());
        model.addAttribute("archivos", List.of()); // opcional, si no hay archivos
        model.addAttribute("usuarioId", 0); // o el ID si estás editando
        return "nuevo";
    }



    @GetMapping("/modificar")
    public String modificar(Model model) {
        //model.addAttribute("usuario", new Usuario());
        model.addAttribute("programas", Collections.emptyList());
        model.addAttribute("funciones", Collections.emptyList());
        model.addAttribute("usuarios", Collections.emptyList());
        return "modificar";
    }




}
