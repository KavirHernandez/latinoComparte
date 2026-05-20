package org.example.latinocomparte.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {

    @Autowired
    private loginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                // Intercepta TODAS las rutas...
                .addPathPatterns("/**")
                // ...excepto las que son públicas (no requieren login)
                .excludePathPatterns(
                        "/login",           // Página de login
                        "/login/**",        // Sub-rutas de login (ej: /login/invitado)
                        "/css/**",          // Recursos estáticos CSS
                        "/js/**",           // Recursos estáticos JavaScript
                        "/images/**"        // Imágenes estáticas
                );
    }
}
