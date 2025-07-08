package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Estado;
import eldoce.com.ar.ABMexternos.model.Usuario;
import eldoce.com.ar.ABMexternos.repository.EstadoRepository;
import eldoce.com.ar.ABMexternos.repository.UsuarioRepository;
import eldoce.com.ar.ABMexternos.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private FileStorageService fileStorageService;

    // ✅ GET para formulario nuevo o editar
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(
            @RequestParam(value = "id", required = false) Long id,
            Model model
    ) {
        Usuario usuario = (id != null)
                ? usuarioRepository.findById(id).orElse(new Usuario())
                : new Usuario();

        model.addAttribute("usuario", usuario);
        model.addAttribute("estados", estadoRepository.findAll());
        return "nuevo";
    }

    // ✅ POST para guardar (crear o editar)
    @PostMapping
    public String guardarUsuario(
            @ModelAttribute Usuario usuario,
            @RequestParam("estado.idestado") Optional<Long> estadoId,
            RedirectAttributes redirectAttributes
    ) {
        try {
            if (estadoId.isPresent()) {
                Estado estado = estadoRepository.findById(estadoId.get())
                        .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"));
                usuario.setEstado(estado);
            }

            usuarioRepository.save(usuario);
            redirectAttributes.addFlashAttribute("exito", "Usuario guardado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar usuario: " + e.getMessage());
        }

        return "redirect:/nuevo";
    }

}
