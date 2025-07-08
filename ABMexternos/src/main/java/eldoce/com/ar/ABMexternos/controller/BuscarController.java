package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Estado;
import eldoce.com.ar.ABMexternos.model.Usuario;
import eldoce.com.ar.ABMexternos.repository.EstadoRepository;
import eldoce.com.ar.ABMexternos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BuscarController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/buscar")
    public String buscarUsuarios(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) String dni,
            Model model
    ) {
        List<Usuario> usuarios;
        boolean hayFiltro = (nombre != null && !nombre.isBlank()) || (apellido != null && !apellido.isBlank()) || (dni != null && !dni.isBlank());

        if (hayFiltro) {
            usuarios = usuarioRepository.buscarPorNombreApellidoDni(nombre, apellido, dni);
        } else {
            usuarios = usuarioRepository.findAll();
        }

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("estados", estadoRepository.findAll());
        return "buscar";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        model.addAttribute("usuario", usuario);
        model.addAttribute("estados", estadoRepository.findAll());
        return "nuevo"; // usa el formulario de alta para editar
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario, @RequestParam("estadoId") Long estadoId) {
        Estado estado = estadoRepository.findById(estadoId).orElse(null);
        usuario.setEstado(estado);
        usuarioRepository.save(usuario);
        return "redirect:/buscar";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/buscar";
    }
}
