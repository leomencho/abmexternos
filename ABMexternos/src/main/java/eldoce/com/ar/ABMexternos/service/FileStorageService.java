package eldoce.com.ar.ABMexternos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileStorageService {

    @Value("${app.upload.dir}")
    private String baseUploadDir;

    private static final long MAX_IMAGE_SIZE = 200 * 1024; // 200 KB

    public File createUserDirectory(Long userId) {
        File userDir = new File(baseUploadDir + File.separator + "user_" + userId);
        if (!userDir.exists()) {
            boolean created = userDir.mkdirs();
            if (!created) {
                throw new RuntimeException("No se pudo crear la carpeta del usuario: " + userDir.getAbsolutePath());
            }
        }
        return userDir;
    }

    public String saveUserImage(Long userId, MultipartFile imageFile) throws IOException {
        String contentType = imageFile.getContentType();
        if (contentType == null ||
                (!contentType.equals("image/jpeg") && !contentType.equals("image/png"))) {
            throw new IllegalArgumentException("Solo se permiten imágenes JPG o PNG");
        }

        if (imageFile.getSize() > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("La imagen no debe superar los 200KB");
        }

        File userDir = createUserDirectory(userId);
        String extension = contentType.equals("image/png") ? ".png" : ".jpg";
        File destino = new File(userDir, "foto" + extension);
        imageFile.transferTo(destino);
        return destino.getAbsolutePath(); // o solo la ruta relativa si preferís
    }

    public String saveUserPdf(Long userId, MultipartFile pdfFile) throws IOException {
        String contentType = pdfFile.getContentType();
        if (contentType == null || !contentType.equals("application/pdf")) {
            throw new IllegalArgumentException("Solo se permiten archivos PDF");
        }

        File userDir = createUserDirectory(userId);
        File destino = new File(userDir, pdfFile.getOriginalFilename());
        pdfFile.transferTo(destino);
        return destino.getAbsolutePath();
    }
}


