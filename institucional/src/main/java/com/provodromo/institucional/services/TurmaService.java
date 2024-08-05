package com.provodromo.institucional.services;

import com.provodromo.institucional.domain.Professor;
import com.provodromo.institucional.domain.Turma;
import com.provodromo.institucional.dto.request.TurmaRequestDTO;
import com.provodromo.institucional.dto.response.TurmaResponseDTO;
import com.provodromo.institucional.error.exception.RegraNegocioException;
import com.provodromo.institucional.helpers.Converters;
import com.provodromo.institucional.repository.ProfessorRepository;
import com.provodromo.institucional.repository.TurmaRepository;
import com.provodromo.institucional.services.base.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TurmaService implements BaseService<TurmaRequestDTO, TurmaResponseDTO, Long> {
    private final TurmaRepository turmaRepository;
    private final Converters converters;
    private final ProfessorRepository professorRepository;

    @Override
    public TurmaResponseDTO findById(Long id) {
        Turma turma = turmaRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Turma não encontrada com o ID: " + id));
        return converters.convertToTurmaResponseDTO(turma);
    }

    @Override
    public Set<TurmaResponseDTO> findAll() {
        return turmaRepository.findAll()
                .stream()
                .map(converters::convertToTurmaResponseDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public TurmaResponseDTO create(TurmaRequestDTO turmaRequestDTO) {
        if (turmaRequestDTO == null) {
            throw new IllegalArgumentException("Dados de Turma inválidos");
        }

        Professor professor = professorRepository
                .findById(turmaRequestDTO.getProfessorId())
                .orElseThrow(() -> new RegraNegocioException("Professor não encontrado com o ID: "
                        + turmaRequestDTO.getProfessorId()));


        try {
            Turma turma = new Turma();
            turma.setNome(turma.getNome());
            turma.setProfessor(professor);
            turma = turmaRepository.save(turma);
            professor.getTurmas().add(turma);
            turmaRepository.save(turma);
            professorRepository.save(professor);

            return converters.convertToTurmaResponseDTO(turma);
        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao salvar turma: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!turmaRepository.existsById(id)) {
            throw new RegraNegocioException("Turma não encontrada com o ID: " + id);
        }
        turmaRepository.deleteById(id);

    }

}