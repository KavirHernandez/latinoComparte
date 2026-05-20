package org.example.latinocomparte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LatinoComparteApplication: clase principal de la aplicación Spring Boot.
 *
 * Punto de entrada de la aplicación "Argentina Comparte".
 * La anotación @SpringBootApplication activa:
 *   - @Configuration: permite definir beans
 *   - @EnableAutoConfiguration: configura automáticamente Spring según dependencias
 *   - @ComponentScan: escanea y registra todos los componentes del paquete
 */
@SpringBootApplication
public class LatinoComparteApplication {

    /**
     * Método main: inicia el servidor embebido Tomcat y la aplicación Spring Boot.
     * Por defecto escucha en el puerto definido en application.properties (8080).
     */
    public static void main(String[] args) {
        SpringApplication.run(LatinoComparteApplication.class, args);
    }
}
