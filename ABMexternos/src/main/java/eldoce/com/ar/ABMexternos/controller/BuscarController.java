package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Estado;
import eldoce.com.ar.ABMexternos.model.Usuario;
import eldoce.com.ar.ABMexternos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BuscarController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ReportaRepository reportaRepository;

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private RecursoRepository recursoRepository;

    @Autowired
    private ProgramaRepository programaRepository;




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
        model.addAttribute("recursos", recursoRepository.findAll());
        model.addAttribute("reportas", reportaRepository.findAll());
        model.addAttribute("funciones", funcionRepository.findAll());
        model.addAttribute("programas", programaRepository.findAll());
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
    public String eliminarUsuario(@PathVariable Long id, RedirectAttributes redirect) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        // Limpiar relaciones muchos a muchos antes de borrar
        usuario.getReporta().clear();
        usuario.getProgramas().clear();
        usuario.getRecursos().clear();
        usuarioRepository.save(usuario);

        usuarioRepository.delete(usuario);
        redirect.addFlashAttribute("exito", "Usuario eliminado correctamente");
        return "redirect:/buscar";
    }

}
