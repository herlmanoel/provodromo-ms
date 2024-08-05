package com.provodromo.institucional.resource.base;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

public interface BaseResource<RequestDTO, ResponseDTO> {

    @GetMapping
    Set<ResponseDTO> listar();

    @PostMapping
    ResponseDTO criar(@RequestBody RequestDTO objeto);

    @GetMapping("/{id}")
    ResponseDTO buscar(@PathVariable Long id);

    @DeleteMapping("/{id}")
    void excluir(@PathVariable Long id);
}
