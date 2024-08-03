package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.controller.base.BaseController;
import com.provodromo.provodromo.dto.request.ProvaRequestDTO;
import com.provodromo.provodromo.dto.response.ProvaResponseDTO;
import com.provodromo.provodromo.service.ProvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/prova", produces = {"application/json"})
public class ProvaController implements BaseController<ProvaRequestDTO, ProvaResponseDTO> {

    @Autowired
    private ProvaService provaService;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Set<ProvaResponseDTO> listar() {
        return provaService.findAll();
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ProvaResponseDTO criar(@Valid @RequestBody ProvaRequestDTO provaRequestDTO) {
        return provaService.create(provaRequestDTO);
    }

    @GetMapping("/buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ProvaResponseDTO buscar(@PathVariable Long id) {
        return provaService.findById(id);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ProvaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProvaRequestDTO provaRequestDTO) {
        return provaService.update(id, provaRequestDTO);
    }

    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void excluir(@PathVariable Long id) {
        provaService.deleteById(id);
    }
}
