package org.example.latinocomparte.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class loginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // Obtiene la sesión actual sin crear una nueva si no existe
        HttpSession session = request.getSession(false);

        // Verifica que haya sesión Y que el atributo "usuario" esté presente
        boolean loggedIn = (session != null && session.getAttribute("usuario") != null);

        if (!loggedIn) {
            // Redirige al formulario de login si no hay sesión válida
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }

}
