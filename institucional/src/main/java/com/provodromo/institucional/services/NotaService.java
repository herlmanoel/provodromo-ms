package com.provodromo.institucional.services;

import com.provodromo.institucional.domain.Aluno;
import com.provodromo.institucional.domain.Nota;
import com.provodromo.institucional.domain.Turma;
import com.provodromo.institucional.domain.Usuario;
import com.provodromo.institucional.dto.request.NotaRequestDTO;
import com.provodromo.institucional.dto.response.NotaCompleteResponseDTO;
import com.provodromo.institucional.dto.response.NotaResponseDTO;
import com.provodromo.institucional.error.exception.RegraNegocioException;
import com.provodromo.institucional.helpers.Converters;
import com.provodromo.institucional.repository.AlunoRepository;
import com.provodromo.institucional.repository.NotaRepository;
import com.provodromo.institucional.repository.TurmaRepository;
import com.provodromo.institucional.services.base.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotaService implements BaseService<NotaRequestDTO, NotaResponseDTO, Long> {
    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;
    private final NotaRepository notaRepository;
    private final UsuarioService usuarioService;
    private final Converters converters;

    @Override
    public NotaResponseDTO findById(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nota não encontrada com o ID: " + id));
        return converters.convertToNotaResponseDTO(nota);
    }

    public NotaCompleteResponseDTO findCompleteById(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nota não encontrada com o ID: " + id));
        Usuario usuario = usuarioService.findUsuarioById(nota.getAluno().getUsuarioId());
        return converters.convertToNotaCompleteResponseDTO(nota, usuario);
    }

    @Override
    public Set<NotaResponseDTO> findAll() {
        return notaRepository.findAll()
                .stream()
                .map(converters::convertToNotaResponseDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public NotaResponseDTO create(NotaRequestDTO notaRequestDTO) {
        if (notaRequestDTO == null) {
            throw new IllegalArgumentException("Dados de Nota inválidos");
        }

        Turma turma = turmaRepository.findById(notaRequestDTO.getTurmaId())
                .orElseThrow(() -> new RegraNegocioException("Turma não encontrada com o ID: " + notaRequestDTO.getTurmaId()));

        Aluno aluno = alunoRepository.findById(notaRequestDTO.getAlunoId())
                .orElseThrow(() -> new RegraNegocioException("Aluno não encontrado com o ID: " + notaRequestDTO.getAlunoId()));

        try {
            Nota nota = new Nota();
            nota.setAvaliacaoId(notaRequestDTO.getAvaliacaoId());
            nota.setValor(notaRequestDTO.getValor());
            nota.setTurma(turma);
            nota.setAluno(aluno);
            nota = notaRepository.save(nota);

            aluno.getNotas().add(nota);
            alunoRepository.save(aluno);
            return converters.convertToNotaResponseDTO(nota);
        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao salvar nota: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!notaRepository.existsById(id)) {
            throw new RegraNegocioException("Nota não encontrada com o ID: " + id);
        }
        notaRepository.deleteById(id);
    }
}
