package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.controller.base.BaseController;
import com.provodromo.provodromo.dto.request.QuestaoRequestDTO;
import com.provodromo.provodromo.dto.response.QuestaoResponseDTO;
import com.provodromo.provodromo.service.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/questao", produces = {"application/json"})
public class QuestaoController implements BaseController<QuestaoRequestDTO, QuestaoResponseDTO> {

    @Autowired
    private QuestaoService questaoService;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Set<QuestaoResponseDTO> listar() {
        return questaoService.findAll();
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public QuestaoResponseDTO criar(@Valid @RequestBody QuestaoRequestDTO questaoRequestDTO) {

        return questaoService.create(questaoRequestDTO);
    }

    @GetMapping("/buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public QuestaoResponseDTO buscar(@PathVariable Long id) {
        return questaoService.findById(id);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public QuestaoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody QuestaoRequestDTO questaoRequestDTO) {

        return questaoService.update(id, questaoRequestDTO);
    }

    @GetMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void excluir(@PathVariable Long id) {
        questaoService.deleteById(id);
    }
}
