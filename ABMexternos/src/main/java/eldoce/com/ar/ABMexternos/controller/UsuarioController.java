package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Estado;
import eldoce.com.ar.ABMexternos.model.Recurso;
import eldoce.com.ar.ABMexternos.model.Usuario;
import eldoce.com.ar.ABMexternos.repository.EstadoRepository;
import eldoce.com.ar.ABMexternos.repository.RecursoRepository;
import eldoce.com.ar.ABMexternos.repository.UsuarioRepository;
import eldoce.com.ar.ABMexternos.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
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

    @Autowired
    private RecursoRepository recursoRepository;

    // 👉 GET para mostrar la vista "nuevo"
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("estados", estadoRepository.findAll());
        return "nuevo";
    }

    // ✅ Crear usuario con estado
    @PostMapping
    public String crearConArchivos(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("dni") String dni,
            @RequestParam("cuil") String cuil,
            @RequestParam("fecha_nac") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento,
            @RequestParam("mail") String mail,
            @RequestParam("pass") String pass,
            @RequestParam("telefono") String telefono,
            @RequestParam("comentario") String comentario,
            @RequestParam("estadoId") Long estadoId,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Usuario nuevo = new Usuario();
            nuevo.setNombre(nombre);
            nuevo.setApellido(apellido);
            nuevo.setDni(dni);
            nuevo.setCuil(cuil);
            nuevo.setFechaNacimiento(fechaNacimiento);
            nuevo.setMail(mail);
            nuevo.setPass(pass);
            nuevo.setTelefono(telefono);
            nuevo.setComentario(comentario);

            Estado estado = estadoRepository.findById(estadoId)
                    .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"));
            nuevo.setEstado(estado);

            usuarioRepository.save(nuevo);

            redirectAttributes.addFlashAttribute("exito", "Usuario creado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear usuario: " + e.getMessage());
        }

        return "redirect:/api/usuarios/nuevo";
    }

    // Otros métodos existentes (foto, pdf, getById, etc.) los dejás como están...
}
