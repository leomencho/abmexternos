package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.Utileria;
import eldoce.com.ar.ABMexternos.model.Estado;
import eldoce.com.ar.ABMexternos.model.Usuario;
import eldoce.com.ar.ABMexternos.repository.EstadoRepository;
import eldoce.com.ar.ABMexternos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(@RequestParam(value = "id", required = false) Long id, Model model) {
        Usuario usuario = (id != null)
                ? usuarioRepository.findById(id).orElse(new Usuario())
                : new Usuario();

        model.addAttribute("usuario", usuario);
        model.addAttribute("estados", estadoRepository.findAll());
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
                String nombreArchivo = "perfil.jpg";

                String archivoGuardado = Utileria.guardarArchivo(multiPart, carpetaUsuario, nombreArchivo);

                if (archivoGuardado != null) {
                    // Esto es clave: guardás la ruta relativa para el acceso vía /media/...
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

    // ✅ Exponer imágenes por URL pública
    @GetMapping("/media/{carpeta}/{archivo:.+}")
    @ResponseBody
    public ResponseEntity<Resource> verArchivo(@PathVariable String carpeta,
                                               @PathVariable String archivo) throws MalformedURLException {
        Path path = Paths.get(uploadDir).resolve(carpeta).resolve(archivo).normalize();
        Resource recurso = new UrlResource(path.toUri());

        if (!recurso.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }
}
