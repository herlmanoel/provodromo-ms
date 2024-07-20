package com.provodromo.usuario.services;

import com.provodromo.usuario.domain.HistoricoTipoUsuario;
import com.provodromo.usuario.domain.TipoUsuario;
import com.provodromo.usuario.domain.Usuario;
import com.provodromo.usuario.dto.request.TipoUsuarioRequestDTO;
import com.provodromo.usuario.dto.response.TipoUsuarioResponseDTO;
import com.provodromo.usuario.error.exception.RegraNegocioException;
import com.provodromo.usuario.repository.HistoricoTipoUsuarioRepository;
import com.provodromo.usuario.repository.TipoUsuarioRepository;
import com.provodromo.usuario.repository.UsuarioRepository;
import com.provodromo.usuario.services.base.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TipoUsuarioService implements BaseService<TipoUsuarioRequestDTO, TipoUsuarioResponseDTO, Long> {

    private final TipoUsuarioRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final HistoricoTipoUsuarioRepository historicoRepository;

    @Override
    public TipoUsuarioResponseDTO findById(Long id) {
        TipoUsuario tipoUsuario = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Tipo de usuário não encontrado com o id: " + id));
        return convertToTipoUsuarioResponseDTO(tipoUsuario);
    }

    @Override
    public Set<TipoUsuarioResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(this::convertToTipoUsuarioResponseDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public TipoUsuarioResponseDTO update(Long id, TipoUsuarioRequestDTO dto) {
        TipoUsuario tipoUsuario = convertToTipoUsuario(dto);
        if (!repository.existsById(id)) {
            throw new RegraNegocioException("Tipo de usuário não encontrado com o id: " + id);
        }

        TipoUsuario tipoUsuarioAntigo = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Tipo de usuário não encontrado com o id: " + id));

        salvarHistorico(tipoUsuarioAntigo);

        tipoUsuario.setId(id);
        return convertToTipoUsuarioResponseDTO(repository.save(tipoUsuario));
    }

    @Override
    public TipoUsuarioResponseDTO create(TipoUsuarioRequestDTO dto) {
        if (repository.findByNome(dto.getNome()) != null) {
            throw new RegraNegocioException("Já existe um tipo de usuário com o nome: " + dto.getNome());
        }

        TipoUsuario tipoUsuario = convertToTipoUsuario(dto);
        TipoUsuario tipoUsuarioCreated = repository.save(tipoUsuario);
        salvarHistorico(tipoUsuarioCreated);

        return convertToTipoUsuarioResponseDTO(tipoUsuarioCreated);
    }

    @Override
    public void deleteById(Long id) {
        TipoUsuario tipoUsuario = repository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Tipo de usuário não encontrado com o id: " + id));

        salvarHistorico(tipoUsuario);

        if (!tipoUsuario.getNome().equals("Comum")) {
            List<Usuario> usuarios = usuarioRepository.findByTipoUsuarioId(id);
            TipoUsuario tipoComum = repository.findByNome("Comum");

            for (Usuario usuario : usuarios) {
                usuario.setTipoUsuario(tipoComum);
                usuarioRepository.save(usuario);
            }

            repository.deleteById(id);
        }
    }

    public TipoUsuarioResponseDTO findByName(String name) {
        TipoUsuario tipoUsuario = repository.findByNome(name);
        if (tipoUsuario == null) {
            throw new RegraNegocioException("Tipo de usuário não encontrado com o nome: " + name);
        }
        return convertToTipoUsuarioResponseDTO(tipoUsuario);
    }

    private TipoUsuario convertToTipoUsuario(TipoUsuarioRequestDTO dto) {
        TipoUsuario entity = new TipoUsuario();
        entity.setNome(dto.getNome());
        return entity;
    }

    private TipoUsuarioResponseDTO convertToTipoUsuarioResponseDTO(TipoUsuario entity) {
        TipoUsuarioResponseDTO dto = new TipoUsuarioResponseDTO();
        dto.setNome(entity.getNome());
        return dto;
    }
    private void salvarHistorico(TipoUsuario tipoUsuarioAntigo) {
        HistoricoTipoUsuario historico = new HistoricoTipoUsuario();
        historico.setNome(tipoUsuarioAntigo.getNome());
        historico.setDataAlteracao(LocalDateTime.now());
        historico.setTipoUsuarioId(tipoUsuarioAntigo.getId());
        historicoRepository.save(historico);
    }
}

