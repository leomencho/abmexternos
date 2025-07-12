package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.Utileria;
import eldoce.com.ar.ABMexternos.model.Estado;
import eldoce.com.ar.ABMexternos.model.Usuario;
import eldoce.com.ar.ABMexternos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {

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

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(@RequestParam(value = "id", required = false) Long id, Model model) {
        Usuario usuario = (id != null)
                ? usuarioRepository.findById(id).orElse(new Usuario())
                : new Usuario();

        model.addAttribute("usuario", usuario);
        model.addAttribute("estados", estadoRepository.findAll());
        model.addAttribute("recursos", recursoRepository.findAll());  // si lo tenés
        model.addAttribute("programas", programaRepository.findAll()); // idem
        model.addAttribute("reportas", reportaRepository.findAll()); // idem
        model.addAttribute("funciones", funcionRepository.findAll()); // ⚠️ ESTA LÍNEA

        return "nuevo";
    }

    @PostMapping
    public String guardarUsuario(@ModelAttribute Usuario usuario,
                                 @RequestParam("archivoFoto") MultipartFile multiPart,
                                 RedirectAttributes redirectAttributes) {
        try {
            Usuario guardado = usuarioRepository.save(usuario);

            if (!multiPart.isEmpty()) {
                String carpetaUsuario = uploadDir + "/" + guardado.getIdUsuarios();
                String nombreArchivo = multiPart.getOriginalFilename(); // nombre real

                // ✅ Guardar archivo físicamente
                String archivoGuardado = Utileria.guardarArchivo(multiPart, carpetaUsuario, nombreArchivo);

                if (archivoGuardado != null) {
                    // ✅ Guardar en base de datos la ruta completa relativa
                    String rutaRelativaWeb = "/media/" + guardado.getIdUsuarios() + "/" + nombreArchivo;
                    guardado.setFoto(rutaRelativaWeb);
                    usuarioRepository.save(guardado);
                }
            }

            redirectAttributes.addFlashAttribute("exito", "Usuario guardado con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }

        return "redirect:/nuevo";
    }
    @GetMapping("/eliminar")
    public String eliminarUsuario(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            usuarioRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("exito", "Usuario eliminado con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar usuario: " + e.getMessage());
        }

        return "redirect:/buscar";
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
