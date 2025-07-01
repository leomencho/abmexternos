package eldoce.com.ar.ABMexternos.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatFileUploadConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> containerCustomizer() {
        return factory -> factory.addConnectorCustomizers((Connector connector) -> {
            // Aumenta el tamaño máximo de POST a 20MB
            connector.setMaxPostSize(20 * 1024 * 1024);

            // Ajuste adicional para evitar errores al descartar archivos grandes
            if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?> protocol) {
                protocol.setMaxSwallowSize(-1); // Sin límite
            }

            // Este valor lo interpreta Tomcat internamente al manejar multipart
            connector.setProperty("maxParameterCount", "10000"); // Previene errores por muchos parámetros
        });
    }
}
