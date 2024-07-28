package com.provodromo.apigatewayzuul.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LogManager.getLogger(CustomLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Registro da URI e do Método da Requisição
        logger.info("URI da Requisição: {}", request.getRequestURI());
        logger.info("Método da Requisição: {}", request.getMethod());

        // Registro da Autenticação
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            logger.info("Usuário '{}' está acessando com roles '{}'",
                    authentication.getName(),
                    authentication.getAuthorities());
        } else {
            logger.warn("Nenhuma autenticação encontrada no contexto de segurança");
        }

        // Continuar o filtro
        filterChain.doFilter(request, response);
    }
}
