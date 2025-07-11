package eldoce.com.ar.ABMexternos;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utileria {

    public static String guardarArchivo(MultipartFile multiPart, String rutaCarpeta, String nombreArchivo) {
        try {
            // Crear la carpeta si no existe
            File dir = new File(rutaCarpeta);
            if (!dir.exists()) dir.mkdirs();

            // Ruta completa del archivo
            Path rutaCompleta = Paths.get(rutaCarpeta, nombreArchivo);
            multiPart.transferTo(rutaCompleta); // Guarda físicamente

            return nombreArchivo; // Devolvemos solo el nombre
        } catch (IOException e) {
            System.out.println("Error al guardar imagen: " + e.getMessage());
            return null;
        }
    }
}
