package com.provodromo.institucional.helpers;

import com.provodromo.institucional.domain.*;
import com.provodromo.institucional.dto.response.*;
import com.provodromo.institucional.services.UsuarioService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converters {
    public NotaResponseDTO convertToNotaResponseDTO(Nota nota) {
        if (nota == null) {
            return null;
        }

        return new NotaResponseDTO(
                nota.getId(),
                nota.getValor(),
                nota.getAvaliacaoId()
        );
    }

    public NotaCompleteResponseDTO convertToNotaCompleteResponseDTO(Nota nota, Usuario usuario) {
        if (nota == null) {
            return null;
        }

        TurmaResponseDTO turma = this.convertToTurmaResponseDTO(nota.getTurma());
        AlunoResponseDTO aluno = this.convertToAlunoResponseDTO(nota.getAluno(), usuario);

        return new NotaCompleteResponseDTO(
                nota.getId(),
                nota.getValor(),
                nota.getAvaliacaoId(),
                turma,
                aluno
        );
    }

    public AlunoResponseDTO convertToAlunoResponseDTO(Aluno aluno, Usuario usuario) {
        if (aluno == null) {
            return null;
        }

        return new AlunoResponseDTO(
                aluno.getId(),
                aluno.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

    public TurmaResponseDTO convertToTurmaResponseDTO(Turma turma) {
        if (turma == null) {
            return null;
        }
        return new TurmaResponseDTO(
                turma.getId(),
                turma.getNome()
        );
    }

    public AlunoCompleteResponseDTO convertToAlunoCompleteResponseDTO(Aluno aluno, Usuario usuario) {
        if (aluno == null) {
            return null;
        }

        List<TurmaResponseDTO> turmasDTOs = aluno.getTurmas().stream()
                .map(this::convertToTurmaResponseDTO)
                .collect(Collectors.toList());

        List<NotaResponseDTO> notasDTOs = aluno.getNotas().stream()
                .map(this::convertToNotaResponseDTO)
                .collect(Collectors.toList());

        return new AlunoCompleteResponseDTO(
                aluno.getId(),
                aluno.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                notasDTOs,
                turmasDTOs

        );
    }

    public ProfessorResponseDTO convertToProfessorResponseDTO(Professor professor, Usuario usuario) {
        if (professor == null) {
            return null;
        }
        return new ProfessorResponseDTO(
                professor.getId(),
                professor.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

    public ProfessorCompleteResponseDTO convertToProfessorCompleteResponseDTO(Professor professor, Usuario usuario) {
        if (professor == null) {
            return null;
        }

        List<TurmaResponseDTO> turmasDTOs = professor.getTurmas().stream()
                .map(this::convertToTurmaResponseDTO)
                .collect(Collectors.toList());


        return new ProfessorCompleteResponseDTO(
                professor.getId(),
                professor.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                turmasDTOs
        );
    }

}
