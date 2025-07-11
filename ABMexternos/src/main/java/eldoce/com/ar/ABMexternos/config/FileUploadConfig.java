package eldoce.com.ar.ABMexternos.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class FileUploadConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(15));     // archivo individual
        factory.setMaxRequestSize(DataSize.ofMegabytes(20));  // suma de todos los archivos
        return factory.createMultipartConfig();
    }
}
