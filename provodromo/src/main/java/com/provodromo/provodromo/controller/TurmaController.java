package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.controller.base.BaseController;
import com.provodromo.provodromo.dto.request.TurmaRequestDTO;
import com.provodromo.provodromo.dto.response.TurmaResponseDTO;
import com.provodromo.provodromo.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/turma", produces = {"application/json"})
public class TurmaController implements BaseController<TurmaRequestDTO, TurmaResponseDTO> {
    @Autowired
    private TurmaService turmaService;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Set<TurmaResponseDTO> listar() {
        return turmaService.findAll();
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public TurmaResponseDTO criar(@Valid @RequestBody TurmaRequestDTO turmaRequestDTO) {
        return turmaService.create(turmaRequestDTO);
    }

    @GetMapping("/buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public TurmaResponseDTO buscar(@PathVariable Long id) {
        return turmaService.findById(id);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public TurmaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody TurmaRequestDTO turmaRequestDTO) {
        return turmaService.update(id, turmaRequestDTO);
    }

    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void excluir(@PathVariable Long id) {
        turmaService.deleteById(id);
    }
}
