package com.provodromo.institucional.services;

import com.provodromo.institucional.domain.Usuario;
import com.provodromo.institucional.feignClients.UsuarioFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private static Logger logger = LoggerFactory.getLogger(Usuario.class);

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;

    public Usuario findUsuarioById(Long id) {
        Usuario usuario = usuarioFeignClient.buscar(id);
        if (usuario == null) {
            logger.error("Usuario not found: " + id);
            throw new IllegalArgumentException("Usuario not found");
        }
        logger.info("Usuario found: " + id);
        return usuario;
    }
}