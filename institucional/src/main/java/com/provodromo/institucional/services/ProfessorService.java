package com.provodromo.institucional.services;

import com.provodromo.institucional.domain.Professor;
import com.provodromo.institucional.domain.Usuario;
import com.provodromo.institucional.dto.request.ProfessorRequestDTO;
import com.provodromo.institucional.dto.response.ProfessorCompleteResponseDTO;
import com.provodromo.institucional.dto.response.ProfessorResponseDTO;
import com.provodromo.institucional.error.exception.RegraNegocioException;
import com.provodromo.institucional.helpers.Converters;
import com.provodromo.institucional.repository.ProfessorRepository;
import com.provodromo.institucional.services.base.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfessorService implements BaseService<ProfessorRequestDTO, ProfessorResponseDTO, Long> {
    private final UsuarioService usuarioService;
    private final ProfessorRepository professorRepository;
    private final Converters converters;

    @Override
    public ProfessorResponseDTO findById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Professor não encontrado com o ID: " + id));

        Usuario usuario = usuarioService.findUsuarioById(professor.getUsuarioId());

        return converters.convertToProfessorResponseDTO(professor, usuario);
    }

    public ProfessorCompleteResponseDTO findCompleteById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Professor não encontrado com o ID: " + id));
        Usuario usuario = usuarioService.findUsuarioById(professor.getUsuarioId());

        return converters.convertToProfessorCompleteResponseDTO(professor, usuario);
    }

    @Override
    public Set<ProfessorResponseDTO> findAll() {
        return professorRepository.findAll()
                .stream()
                .map(professor -> {
                    Usuario usuario = usuarioService.findUsuarioById(professor.getUsuarioId());
                    return converters.convertToProfessorResponseDTO(professor, usuario);
                })
                .collect(Collectors.toSet());
    }

    @Override
    public ProfessorResponseDTO create(ProfessorRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Dados de usuário inválidos");
        }

        if (professorRepository.existsByUsuarioId(dto.getUsuarioId())) {
            throw new RegraNegocioException("Já existe um Professor com o id " + dto.getClass());
        }

        return createOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new RegraNegocioException("Professor não encontrado com o ID: " + id);
        }
        professorRepository.deleteById(id);
    }

    @Transactional
    public void deleteByUsuarioId(Long usuarioid) {
        if (!professorRepository.existsByUsuarioId(usuarioid)) {
            throw new RegraNegocioException("Professor não encontrado com o usuario ID: " + usuarioid);
        }
        professorRepository.deleteByUsuarioId(usuarioid);
    }

    private ProfessorResponseDTO createOrUpdate(ProfessorRequestDTO dto) {
        try {
            Professor professor = new Professor();
            professor.setUsuarioId(dto.getUsuarioId());

            var ProfessorSaved = professorRepository.save(professor);
            Usuario usuario = usuarioService.findUsuarioById(ProfessorSaved.getUsuarioId());


            return converters.convertToProfessorResponseDTO(ProfessorSaved, usuario);
        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao salvar o professor: " + e.getMessage());
        }
    }

}