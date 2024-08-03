package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.request.ProvaRequestDTO;
import com.provodromo.provodromo.dto.response.ProvaResponseDTO;
import com.provodromo.provodromo.model.Prova;
import com.provodromo.provodromo.model.Questao;
import com.provodromo.provodromo.repository.ProvaRepository;
import com.provodromo.provodromo.repository.QuestaoRepository;
import com.provodromo.provodromo.repository.TurmaRepository;
import com.provodromo.provodromo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProvaService implements BaseService<ProvaRequestDTO, ProvaResponseDTO, Long> {

    @Autowired
    private ProvaRepository provaRepository;
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private QuestaoRepository questaoRepository;

    @Override
    public ProvaResponseDTO findById(Long id) {
        Prova prova = provaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prova não encontrada com o id: " + id));

        return convertToProvaResponseDTO(prova);
    }

    @Override
    public Set<ProvaResponseDTO> findAll() {
        return provaRepository.findAll().stream()
                .map(this::convertToProvaResponseDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public ProvaResponseDTO update(Long aLong, ProvaRequestDTO provaRequestDTO) {
        if (provaRequestDTO == null || provaRequestDTO.getTitulo() == null || provaRequestDTO.getQuestoesIds() == null || provaRequestDTO.getTurmaId() == null) {
            throw new RuntimeException("Prova não encontrada com o id: " + aLong);
        }

        if (!provaRepository.existsById(aLong)) {
            throw new RuntimeException("Prova não encontrada com o id: " + aLong);
        }

        return convertToProvaResponseDTO(provaRepository.save(convertToProva(provaRequestDTO)));
    }

    @Override
    public ProvaResponseDTO create(ProvaRequestDTO provaRequestDTO) {
        if (provaRequestDTO == null || provaRequestDTO.getTitulo() == null || provaRequestDTO.getQuestoesIds() == null || provaRequestDTO.getTurmaId() == null) {
            throw new RuntimeException("Prova não encontrada com o id: " + provaRequestDTO.getId());
        }

        return convertToProvaResponseDTO(provaRepository.save(convertToProva(provaRequestDTO)));
    }

    @Override
    public void deleteById(Long aLong) {
        if (!provaRepository.existsById(aLong)) {
            throw new RuntimeException("Prova não encontrada com o id: " + aLong);
        }

        provaRepository.deleteById(aLong);
    }

    private ProvaResponseDTO convertToProvaResponseDTO(Prova prova) {
        if (prova == null) {
            return null;
        }
        Set<Long> questoesIds = prova.getQuestoes().stream()
                .map(Questao::getId)
                .collect(Collectors.toSet());

        return new ProvaResponseDTO(
                prova.getId(),
                prova.getTitulo(),
                prova.getTurma().getId(),
                prova.getNota(),
                questoesIds
        );
    }

    private Prova convertToProva(ProvaRequestDTO provaRequestDTO) {
        if (provaRequestDTO == null) {
            return null;
        }

        Prova prova = new Prova();
        prova.setId(provaRequestDTO.getId());
        prova.setTurma(turmaRepository.findById(provaRequestDTO.getTurmaId()).orElseThrow(() -> new RuntimeException("Turma não encontrada com o id: " + provaRequestDTO.getTurmaId())));
        prova.setNota(provaRequestDTO.getNota());
        prova.setTitulo(provaRequestDTO.getTitulo());
        prova.setQuestoes(provaRequestDTO.getQuestoesIds().stream()
                .map(id -> questaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Questão não encontrada com o id: " + id)))
                .collect(Collectors.toSet()));

        return prova;
    }
}
