package eldoce.com.ar.ABMexternos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ArchivoController {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @GetMapping("/media/{nombre:.+}")
    @ResponseBody
    public ResponseEntity<Resource> verArchivo(@PathVariable String nombre) throws MalformedURLException {
        Path path = Paths.get(uploadDir).resolve(nombre).normalize();
        Resource recurso = new UrlResource(path.toUri());

        if (!recurso.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }
}
