package com.provodromo.provodromo.service;

import com.provodromo.provodromo.dto.request.AlternativaRequestDTO;
import com.provodromo.provodromo.dto.request.QuestaoRequestDTO;
import com.provodromo.provodromo.dto.response.AlternativaResponseDTO;
import com.provodromo.provodromo.dto.response.QuestaoResponseDTO;
import com.provodromo.provodromo.error.exception.RegraNegocioException;
import com.provodromo.provodromo.model.Alternativa;
import com.provodromo.provodromo.model.Questao;
import com.provodromo.provodromo.repository.AlternativaRepository;
import com.provodromo.provodromo.repository.QuestaoRepository;
import com.provodromo.provodromo.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuestaoService implements BaseService<QuestaoRequestDTO, QuestaoResponseDTO, Long> {

    @Autowired
    private QuestaoRepository questaoRepository;
    @Autowired
    private AlternativaRepository alternativaRepository;


    @Override
    public QuestaoResponseDTO findById(Long id) {
        Questao questao = questaoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Questão não encontrada com o ID: " + id));

        return convertToQuestaoResponseDTO(questao);
    }

    @Override
    public Set<QuestaoResponseDTO> findAll() {
        return questaoRepository.findAll().stream().map(this::convertToQuestaoResponseDTO).collect(Collectors.toSet());
    }

    @Override
    public void deleteById(Long id) {
        if (!questaoRepository.existsById(id)) {
            throw new RegraNegocioException("Questão não encontrada com o ID: " + id);
        }
        questaoRepository.deleteById(id);

    }

    @Override
    public QuestaoResponseDTO create(QuestaoRequestDTO questaoRequestDTO) {
        if (questaoRequestDTO == null || questaoRequestDTO.getTexto() == null || questaoRequestDTO.getAlternativas() == null) {
            throw new IllegalArgumentException("Dados da Questão inválidos");
        }

        return createOrUpdate(questaoRequestDTO);
    }

    @Override
    public QuestaoResponseDTO update(Long id, QuestaoRequestDTO questaoRequestDTO) {
        if (questaoRequestDTO == null || questaoRequestDTO.getTexto() == null || questaoRequestDTO.getAlternativas() == null) {
            throw new RegraNegocioException("Dados da Questão inválidos");
        }

        if (!questaoRepository.existsById(id)) {
            throw new RegraNegocioException("Questão não encontrada com o ID: " + id);
        }

        questaoRequestDTO.setId(id);

        return createOrUpdate(questaoRequestDTO);
    }

    public QuestaoResponseDTO createOrUpdate(QuestaoRequestDTO questaoRequestDTO) {
        if (questaoRequestDTO == null || questaoRequestDTO.getTexto() == null) {
            throw new IllegalArgumentException("Dados da Questão inválidos");
        }
        List<Alternativa> alternativas = questaoRequestDTO.getAlternativas().stream().map(this::convertToAlternativa).collect(Collectors.toList());
        questaoRequestDTO.setAlternativas(null);
        Questao questao = dtoToEntity(questaoRequestDTO);
        questaoRepository.save(questao);
        alternativas.forEach(alternativa -> alternativa.setQuestao(questao));
        alternativaRepository.saveAll(alternativas);
        questao.setAlternativas(alternativas);

        return convertToQuestaoResponseDTO(questao);
    }

    private QuestaoResponseDTO convertToQuestaoResponseDTO(Questao questao) {
        if (questao == null) {
            return null;
        }
        List<AlternativaResponseDTO> alternativasResponseDTO = questao.getAlternativas().stream()
                .map(this::converToAlternativaResponseDTO).collect(Collectors.toList());

        return new QuestaoResponseDTO(
                questao.getId(),
                questao.getTexto(),
                questao.getDificuldade(),
                questao.getNota(),
                alternativasResponseDTO
        );
    }

    private Questao dtoToEntity(QuestaoRequestDTO questaoRequestDTO) {
        if (questaoRequestDTO == null) {
            return null;
        }

        Questao questao = new Questao();
        questao.setId(questaoRequestDTO.getId());
        questao.setTexto(questaoRequestDTO.getTexto());
        questao.setDificuldade(questaoRequestDTO.getDificuldade());
        questao.setNota(questaoRequestDTO.getNota());
        if (questaoRequestDTO.getAlternativas() != null) {
            List<Alternativa> alternativas = questaoRequestDTO.getAlternativas().stream()
                    .map(this::convertToAlternativa)
                    .collect(Collectors.toList());
            questao.setAlternativas(alternativas);
        }

        return questao;
    }

    private AlternativaResponseDTO converToAlternativaResponseDTO(Alternativa alternativa) {
        if (alternativa == null) {
            return null;
        }
        return new AlternativaResponseDTO(alternativa.getId(), alternativa.getTexto(), alternativa.isCorreta());
    }

    private Alternativa convertToAlternativa(AlternativaRequestDTO alternativaRequestDTO) {
        if (alternativaRequestDTO == null) {
            return null;
        }
        Alternativa alternativa = new Alternativa();
        alternativa.setId(alternativaRequestDTO.getId());
        alternativa.setTexto(alternativaRequestDTO.getTexto());
        alternativa.setCorreta(alternativaRequestDTO.isCorreta());
        return alternativa;
    }
}
