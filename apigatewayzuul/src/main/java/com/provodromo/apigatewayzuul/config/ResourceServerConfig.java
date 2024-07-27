package com.provodromo.apigatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    @Autowired
    private JwtTokenStore tokenStore;
    
    private static final String[] PUBLIC = {"/oauth/oauth/token"};
    private static final String[] USUARIO = {"/usuario/**"};

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
                .anyRequest().authenticated());
    }

    

}
