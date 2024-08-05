package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.request.ProvaFinalizadaRequestDTO;
import com.provodromo.provodromo.dto.response.ProvaFinalizadaResponseDTO;
import com.provodromo.provodromo.error.exception.RegraNegocioException;
import com.provodromo.provodromo.model.Alternativa;
import com.provodromo.provodromo.model.Prova;
import com.provodromo.provodromo.model.ProvaFinalizada;
import com.provodromo.provodromo.model.Questao;
import com.provodromo.provodromo.repository.AlternativaRepository;
import com.provodromo.provodromo.repository.ProvaFinalizadaRepository;
import com.provodromo.provodromo.repository.ProvaRepository;
import com.provodromo.provodromo.repository.QuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ProvaFinalizadaService {
    @Autowired
    private AlternativaRepository alternativaRepository;

    @Autowired
    private ProvaFinalizadaRepository provaFinalizadaRepository;

    @Autowired
    private ProvaRepository provaRepository;

    public ProvaFinalizadaResponseDTO finalizarProva(ProvaFinalizadaRequestDTO provaFinalizadaRequestDTO) {
        ProvaFinalizada provaFinalizada = new ProvaFinalizada();
        Prova prova = provaRepository.findById(provaFinalizadaRequestDTO.getProvaId()).orElseThrow(() -> new RegraNegocioException("Prova não encontrada"));
        provaFinalizada.setProva(prova);
        provaFinalizada.setUsuarioId(provaFinalizadaRequestDTO.getUsuarioId());

        Long nota = calculateNotaByRespostas(prova.getNota(), provaFinalizadaRequestDTO.getAlternativasIds());
        provaFinalizada.setNota(nota);
        provaFinalizadaRepository.save(provaFinalizada);

        ProvaFinalizadaResponseDTO provaFinalizadaResponseDTO = new ProvaFinalizadaResponseDTO();
        provaFinalizadaResponseDTO.setId(provaFinalizada.getId());
        provaFinalizadaResponseDTO.setNota(provaFinalizada.getNota());
        provaFinalizadaResponseDTO.setProvaId(provaFinalizada.getProva().getId());
        provaFinalizadaResponseDTO.setUsuarioId(provaFinalizada.getUsuarioId());

        return provaFinalizadaResponseDTO;
    }

    public ProvaFinalizadaResponseDTO retrieveProvaFinalizadaByUsuarioIdAndProvaId(Long usuarioId, Long provaId) {
        ProvaFinalizada provaFinalizada = provaFinalizadaRepository.findByUsuarioIdAndProvaId(usuarioId, provaId).orElseThrow(() -> new RegraNegocioException("Prova finalizada não encontrada"));

        ProvaFinalizadaResponseDTO provaFinalizadaResponseDTO = new ProvaFinalizadaResponseDTO();
        provaFinalizadaResponseDTO.setId(provaFinalizada.getId());
        provaFinalizadaResponseDTO.setNota(provaFinalizada.getNota());
        provaFinalizadaResponseDTO.setProvaId(provaFinalizada.getProva().getId());
        provaFinalizadaResponseDTO.setUsuarioId(provaFinalizada.getUsuarioId());

        return provaFinalizadaResponseDTO;
    }

    public List<ProvaFinalizadaResponseDTO> retrieveProvasFinalizadasByUsuarioId(Long usuarioId) {
        List<ProvaFinalizada> provasFinalizadas = provaFinalizadaRepository.findByUsuarioId(usuarioId);

        return getProvaFinalizadaResponseDTOS(provasFinalizadas);
    }

    public List<ProvaFinalizadaResponseDTO> retrieveProvasFinalizadasByProvaId(Long provaId) {
        List<ProvaFinalizada> provasFinalizadas = provaFinalizadaRepository.findByProvaId(provaId);

        return getProvaFinalizadaResponseDTOS(provasFinalizadas);
    }

    public List<ProvaFinalizadaResponseDTO> retrieveProvasFinalizadasByUsuarioIdAndTurmaId(Long usuarioId, Long turmaId) {
        List<ProvaFinalizada> provasFinalizadas = provaFinalizadaRepository.findByUsuarioIdAndTurmaId(usuarioId, turmaId);

        return getProvaFinalizadaResponseDTOS(provasFinalizadas);
    }

    private List<ProvaFinalizadaResponseDTO> getProvaFinalizadaResponseDTOS(List<ProvaFinalizada> provasFinalizadas) {
        List<ProvaFinalizadaResponseDTO> provasFinalizadasResponseDTO = new ArrayList<>();
        ProvaFinalizadaResponseDTO provaFinalizadaResponseDTO;
        for (ProvaFinalizada provaFinalizada : provasFinalizadas) {
            provaFinalizadaResponseDTO = new ProvaFinalizadaResponseDTO();
            provaFinalizadaResponseDTO.setId(provaFinalizada.getId());
            provaFinalizadaResponseDTO.setNota(provaFinalizada.getNota());
            provaFinalizadaResponseDTO.setProvaId(provaFinalizada.getProva().getId());
            provaFinalizadaResponseDTO.setUsuarioId(provaFinalizada.getUsuarioId());
            provasFinalizadasResponseDTO.add(provaFinalizadaResponseDTO);
        }

        return provasFinalizadasResponseDTO;
    }

    private Long calculateNotaByRespostas(Long nota, Set<Long> alternativasIds) {
        List<Long> questoesIds = new ArrayList<>();
        Alternativa alternativa;
        Long countCorretas = 0L;

        for (Long alternativaId : alternativasIds) {
            alternativa = alternativaRepository.findById(alternativaId).orElseThrow(() -> new RegraNegocioException("Alternativa não encontrada"));
            Long questaoId = alternativa.getQuestao().getId();
            if (questoesIds.contains(questaoId)) {
                throw new RegraNegocioException("Questão de id=" + questaoId + " foi respondida duas vezes");
            } else {
                questoesIds.add(questaoId);
                if (alternativa.isCorreta()) {
                    countCorretas++;
                }
            }
        }

        return (countCorretas * nota) / questoesIds.size();
    }
}
