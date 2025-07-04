package eldoce.com.ar.ABMexternos.controller;

import eldoce.com.ar.ABMexternos.model.Recurso;
import eldoce.com.ar.ABMexternos.model.Usuario;
import eldoce.com.ar.ABMexternos.repository.RecursoRepository;
import eldoce.com.ar.ABMexternos.repository.UsuarioRepository;
import eldoce.com.ar.ABMexternos.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private RecursoRepository recursoRepository;


    // ✅ Crear usuario
    @PostMapping("/simple")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario guardado = usuarioRepository.save(usuario);
        fileStorageService.createUserDirectory(guardado.getIdUsuarios());
        return ResponseEntity.ok(guardado);
    }

    // ✅ Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Listar todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // ✅ Subir imagen JPG o PNG (máx 200 KB)
    @PostMapping("/{id}/foto")
    public ResponseEntity<?> subirFoto(@PathVariable Long id,
                                       @RequestParam("imagen") MultipartFile imagen) {
        try {
            String path = fileStorageService.saveUserImage(id, imagen);
            Usuario usuario = usuarioRepository.findById(id).orElseThrow();
            usuario.setFoto(path); // guardás la ruta
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("Imagen subida exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error al guardar la imagen");
        }
    }

    // ✅ Subir archivo PDF
    @PostMapping("/{id}/pdf")
    public ResponseEntity<?> subirPdf(@PathVariable Long id,
                                      @RequestParam("archivo") MultipartFile pdf) {
        try {
            fileStorageService.saveUserPdf(id, pdf);
            return ResponseEntity.ok("PDF subido exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error al guardar el PDF");
        }
    }

    @PostMapping()
    public ResponseEntity<?> crearConArchivos(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("dni") String dni,
            @RequestParam("cuil") String cuil,
            @RequestParam("fecha_nac") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento,
            @RequestParam("mail") String mail,
            @RequestParam("pass") String pass,
            @RequestParam("telefono") String telefono,
            @RequestParam("comentario") String comentario
            // Eliminamos: programa, estado, perfil, funcion, reporta
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

            Usuario guardado = usuarioRepository.save(nuevo);
            return ResponseEntity.ok("Usuario creado correctamente");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear usuario: " + e.getMessage());
        }
    }


}
