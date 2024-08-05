package com.provodromo.institucional.services;

import com.provodromo.institucional.domain.Aluno;
import com.provodromo.institucional.domain.Nota;
import com.provodromo.institucional.domain.Turma;
import com.provodromo.institucional.domain.Usuario;
import com.provodromo.institucional.dto.request.AlunoRequestDTO;
import com.provodromo.institucional.dto.request.NotaRequestDTO;
import com.provodromo.institucional.dto.response.AlunoCompleteResponseDTO;
import com.provodromo.institucional.dto.response.AlunoResponseDTO;
import com.provodromo.institucional.dto.response.NotaResponseDTO;
import com.provodromo.institucional.dto.response.TurmaResponseDTO;
import com.provodromo.institucional.error.exception.RegraNegocioException;
import com.provodromo.institucional.helpers.Converters;
import com.provodromo.institucional.repository.AlunoRepository;
import com.provodromo.institucional.repository.NotaRepository;
import com.provodromo.institucional.repository.TurmaRepository;
import com.provodromo.institucional.services.base.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlunoService implements BaseService<AlunoRequestDTO, AlunoResponseDTO, Long> {
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final UsuarioService usuarioService;
    private final Converters converters;

    @Override
    public AlunoResponseDTO findById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Aluno não encontrado com o ID: " + id));
        Usuario usuario = usuarioService.findUsuarioById(aluno.getUsuarioId());

        return converters.convertToAlunoResponseDTO(aluno, usuario);
    }

    public AlunoCompleteResponseDTO findCompleteById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Aluno não encontrado com o ID: " + id));
        Usuario usuario = usuarioService.findUsuarioById(aluno.getUsuarioId());

        return converters.convertToAlunoCompleteResponseDTO(aluno, usuario);
    }

    @Override
    public Set<AlunoResponseDTO> findAll() {
        return alunoRepository.findAll()
                .stream()
                .map(aluno -> {
                    Usuario usuario = usuarioService.findUsuarioById(aluno.getUsuarioId());
                    return converters.convertToAlunoResponseDTO(aluno, usuario);
                })
                .collect(Collectors.toSet());
    }

    @Override
    public AlunoResponseDTO create(AlunoRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Dados de usuário inválidos");
        }

        if (alunoRepository.existsByUsuarioId(dto.getUsuarioId())) {
            throw new RegraNegocioException("Já existe um aluno com o id " + dto.getUsuarioId());
        }

        return createOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new RegraNegocioException("Aluno não encontrado com o ID: " + id);
        }
        alunoRepository.deleteById(id);
    }

    @Transactional
    public void deleteByUsuarioId(Long usuarioid) {
        if (!alunoRepository.existsByUsuarioId(usuarioid)) {
            throw new RegraNegocioException("Aluno não encontrado com o usuario ID: " + usuarioid);
        }
        alunoRepository.deleteByUsuarioId(usuarioid);
    }

    @Transactional
    public AlunoCompleteResponseDTO associarTurma(Long AlunoId, Long turmaId) {
        Aluno aluno = alunoRepository.findById(AlunoId)
                .orElseThrow(() -> new RegraNegocioException("Aluno não encontrado com o ID: " + AlunoId));

        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RegraNegocioException("Turma não encontrada com o ID: " + turmaId));

        aluno.getTurmas().add(turma);
        turma.getAlunos().add(aluno);

        turmaRepository.save(turma);

        var alunoSaved = alunoRepository.save(aluno);

        Usuario usuario = usuarioService.findUsuarioById(alunoSaved.getUsuarioId());

        return converters.convertToAlunoCompleteResponseDTO(alunoSaved, usuario);
    }



    private AlunoResponseDTO createOrUpdate(AlunoRequestDTO dto) {
        try {
            Aluno aluno = new Aluno();
            aluno.setUsuarioId(dto.getUsuarioId());

            var alunoSaved = alunoRepository.save(aluno);
            Usuario usuario = usuarioService.findUsuarioById(alunoSaved.getUsuarioId());


            return converters.convertToAlunoResponseDTO(alunoSaved, usuario);
        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao salvar/atualizar o usuário: " + e.getMessage());
        }
    }
}