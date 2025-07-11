# Dockerfile
FROM tomcat:10.1

# Eliminar apps por defecto
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar WAR
COPY target/ABMexternos.war /usr/local/tomcat/webapps/ROOT.war

# Copiar archivos estáticos (imágenes y pdfs)
COPY images /usr/local/tomcat/images/
COPY pdfs /usr/local/tomcat/pdfs/

# Exponer el puerto de Tomcat
EXPOSE 8080
