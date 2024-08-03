package com.provodromo.apigatewayzuul.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    private static final Logger logger = LogManager.getLogger(ResourceServerConfig.class);

    @Autowired
    private JwtTokenStore tokenStore;

    private static final String[] PUBLIC = {"/oauth/oauth/token"};
    private static final String[] USUARIO = {"/usuario/**"};
    private static final String[] INSTITUCIONAL = {"/institucional/**"};
    private static final String[] PROVODROMO = {"/provodromo/**"};


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests(requests -> requests
                .antMatchers(PUBLIC).permitAll()
                // Usuário
                .antMatchers(HttpMethod.GET, USUARIO).hasAnyRole("COMUM", "MODERADOR", "CONVIDADO", "VISITANTE", "PROFESSOR", "ADMINISTRADOR")
                .antMatchers(HttpMethod.POST, USUARIO).hasAnyRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.PUT, USUARIO).hasAnyRole("MODERADOR", "ADMINISTRADOR")
                .antMatchers(HttpMethod.DELETE, USUARIO).hasRole("ADMINISTRADOR")
                // Institucional
                .antMatchers(HttpMethod.GET, INSTITUCIONAL).hasAnyRole("COMUM", "MODERADOR", "CONVIDADO", "VISITANTE", "PROFESSOR", "ADMINISTRADOR")
                .antMatchers(HttpMethod.POST, INSTITUCIONAL).hasAnyRole("COMUM", "MODERADOR", "CONVIDADO", "VISITANTE", "PROFESSOR", "ADMINISTRADOR")
                .antMatchers(HttpMethod.PUT, INSTITUCIONAL).hasAnyRole("COMUM", "MODERADOR", "CONVIDADO", "VISITANTE", "PROFESSOR", "ADMINISTRADOR")
                .antMatchers(HttpMethod.DELETE, INSTITUCIONAL).hasAnyRole("COMUM", "MODERADOR", "CONVIDADO", "VISITANTE", "PROFESSOR", "ADMINISTRADOR")
                // Provodrmo
                .antMatchers(HttpMethod.GET, PROVODROMO).hasAnyRole("COMUM", "MODERADOR", "CONVIDADO", "VISITANTE", "PROFESSOR", "ADMINISTRADOR")
                .antMatchers(HttpMethod.POST, PROVODROMO).hasAnyRole("COMUM", "MODERADOR", "CONVIDADO", "VISITANTE", "PROFESSOR", "ADMINISTRADOR")
                .antMatchers(HttpMethod.PUT, PROVODROMO).hasAnyRole("COMUM", "MODERADOR", "CONVIDADO", "VISITANTE", "PROFESSOR", "ADMINISTRADOR")
                .antMatchers(HttpMethod.DELETE, PROVODROMO).hasAnyRole("COMUM", "MODERADOR", "CONVIDADO", "VISITANTE", "PROFESSOR", "ADMINISTRADOR")
                .anyRequest().authenticated())
                .addFilterBefore(new CustomLoggingFilter(), UsernamePasswordAuthenticationFilter.class);

                http.addFilterAfter((request, response, chain) -> {
                    logger.info("iniciando");
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    if (authentication != null) {
                        logger.info("User '{}' is accessing with roles '{}'",
                                    authentication.getName(),
                                    authentication.getAuthorities());
                    }
                    chain.doFilter(request, response);
                }, UsernamePasswordAuthenticationFilter.class);

                logger.info("HTTP Security configured successfully");
    }



}
