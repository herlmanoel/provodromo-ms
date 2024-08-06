package com.provodromo.institucional.resource;

import com.provodromo.institucional.dto.request.ProfessorRequestDTO;
import com.provodromo.institucional.dto.response.ProfessorCompleteResponseDTO;
import com.provodromo.institucional.dto.response.ProfessorResponseDTO;
import com.provodromo.institucional.resource.base.BaseResource;
import com.provodromo.institucional.services.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/professor", produces = {"application/json"})
@AllArgsConstructor
public class ProfessorResource implements BaseResource<ProfessorRequestDTO, ProfessorResponseDTO> {
    private final ProfessorService professorService;

    @GetMapping
    @Override
    public Set<ProfessorResponseDTO> listar() {
        return professorService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ProfessorResponseDTO criar(@Valid @RequestBody ProfessorRequestDTO Aluno) {
        return professorService.create(Aluno);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ProfessorResponseDTO buscar(@PathVariable Long id) {
        return professorService.findById(id);
    }

    @GetMapping("/comp/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfessorCompleteResponseDTO buscarComplete(@PathVariable Long id) {
        return professorService.findCompleteById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void excluir(@PathVariable Long id) {
        professorService.deleteById(id);
    }


}
