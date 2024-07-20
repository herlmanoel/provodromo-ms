package com.provodromo.usuario.resource.base;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

public interface BaseResource<RequestDTO, ResponseDTO> {

    @GetMapping
    Set<ResponseDTO> listar();

    @PostMapping
    ResponseDTO criar(@RequestBody RequestDTO objeto);

    @GetMapping("/{id}")
    ResponseDTO buscar(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseDTO atualizar(@PathVariable Long id, @RequestBody RequestDTO objeto);

    @DeleteMapping("/{id}")
    void excluir(@PathVariable Long id);
}
