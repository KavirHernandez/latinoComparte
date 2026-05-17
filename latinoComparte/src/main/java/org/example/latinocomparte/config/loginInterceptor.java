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

        HttpSession session = request.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute("usuario") != null);

        String uri = request.getRequestURI();

        // permitir login y recursos públicos
        if (uri.equals("/login") || uri.contains("/css") || uri.contains("/images")) {
            return true;
        }

        if (!loggedIn) {
            response.sendRedirect("/login");
            return false;
        }

        if (uri.equals("/login") ||
                uri.equals("/login/") ||
                uri.startsWith("/css") ||
                uri.startsWith("/images") ||
                uri.startsWith("/js")) {
            return true;
        }

        return true;
    }

}