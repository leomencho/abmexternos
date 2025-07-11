package eldoce.com.ar.ABMexternos.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return factory -> factory.addConnectorCustomizers(connector -> {
            connector.setMaxPostSize(20 * 1024 * 1024); // 20 MB
            connector.setProperty("maxParameterCount", "10000"); // por si tenés muchos campos
            connector.setProperty("maxFileCount", "10"); // <<--- lo importante
        });
    }
}
