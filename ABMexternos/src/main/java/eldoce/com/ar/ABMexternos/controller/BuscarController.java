package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Programa;
import eldoce.com.ar.ABMexternos.model.Usuario;
import eldoce.com.ar.ABMexternos.repository.ProgramaRepository;
import eldoce.com.ar.ABMexternos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BuscarController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/buscar")
    public String buscarUsuarios(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) String dni,
            Model model
    ) {
        List<Usuario> usuarios;

        boolean hayFiltro = (nombre != null && !nombre.isBlank())
                || (apellido != null && !apellido.isBlank())
                || (dni != null && !dni.isBlank());

        if (hayFiltro) {
            usuarios = usuarioRepository.buscarPorNombreApellidoDni(nombre, apellido, dni);
        } else {
            usuarios = usuarioRepository.findAll();
        }

        model.addAttribute("usuarios", usuarios);
        return "buscar";
    }

}


