package com.provodromo.institucional.resource;

import com.provodromo.institucional.dto.request.TurmaRequestDTO;
import com.provodromo.institucional.dto.response.TurmaResponseDTO;
import com.provodromo.institucional.resource.base.BaseResource;
import com.provodromo.institucional.services.TurmaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/turma", produces = {"application/json"})
@AllArgsConstructor
public class TurmaResource implements BaseResource<TurmaRequestDTO, TurmaResponseDTO> {
    private final TurmaService turmaService;

    @GetMapping
    @Override
    public Set<TurmaResponseDTO> listar() {
        return turmaService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public TurmaResponseDTO criar(@Valid @RequestBody TurmaRequestDTO turma) {
        return turmaService.create(turma);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public TurmaResponseDTO buscar(@PathVariable Long id) {
        return turmaService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void excluir(@PathVariable Long id) {
        turmaService.deleteById(id);
    }

}
