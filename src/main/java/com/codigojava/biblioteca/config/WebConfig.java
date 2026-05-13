package com.codigojava.biblioteca.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public WebConfig() {
        System.out.println(">>> WebConfig CARGADO <<<");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Usa la ruta absoluta del proyecto
        String projectPath = System.getProperty("user.dir");

        // 🟦 Portadas de libros
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/home/sam/SAM-PROYECTOS/Biblioteca-codigojava/uploads/");

        // 🟩 Archivos PDF de libros
        registry.addResourceHandler("/uploads/file/**")
                .addResourceLocations("file:/home/sam/SAM-PROYECTOS/Biblioteca-codigojava/uploads/file/");

    }
}
