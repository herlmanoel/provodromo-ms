package com.provodromo.institucional.feignClients;

import com.provodromo.institucional.domain.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "usuario", path = "/usuario")
public interface UsuarioFeignClient {
    @GetMapping(value = "/{id}")
    ResponseEntity<Usuario> findUsuarioById(@RequestParam Long id);
}