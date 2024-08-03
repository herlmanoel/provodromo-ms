package com.provodromo.provodromo.controller.base;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

public interface BaseController<RequestDTO, ResponseDTO> {

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
