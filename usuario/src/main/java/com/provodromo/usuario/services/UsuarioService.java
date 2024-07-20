package com.provodromo.usuario.services;

import com.provodromo.usuario.domain.TipoUsuario;
import com.provodromo.usuario.domain.Usuario;
import com.provodromo.usuario.dto.request.TipoUsuarioRequestDTO;
import com.provodromo.usuario.dto.request.UsuarioRequestDTO;
import com.provodromo.usuario.dto.response.UsuarioResponseDTO;
import com.provodromo.usuario.error.exception.RegraNegocioException;
import com.provodromo.usuario.repository.TipoUsuarioRepository;
import com.provodromo.usuario.repository.UsuarioRepository;
import com.provodromo.usuario.services.base.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioService implements BaseService<UsuarioRequestDTO, UsuarioResponseDTO, Long> {
    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado com o ID: " + id));
        return convertToUsuarioResponseDTO(usuario);
    }

    @Override
    public Set<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertToUsuarioResponseDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public UsuarioResponseDTO create(UsuarioRequestDTO dto) {
        if (dto == null || dto.getEmail() == null || dto.getSenha() == null) {
            throw new IllegalArgumentException("Dados de usuário inválidos");
        }

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RegraNegocioException("Já existe um usuário com o e-mail " + dto.getEmail());
        }

        return createOrUpdate(dto);
    }

    @Override
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RegraNegocioException("Usuário não encontrado com o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto) {
        if (dto == null || dto.getEmail() == null || dto.getSenha() == null) {
            throw new RegraNegocioException("Dados de usuário inválidos");
        }

        if (!usuarioRepository.existsById(id)) {
            throw new RegraNegocioException("Usuário não encontrado com o ID: " + id);
        }

        dto.setId(id);
        return createOrUpdate(dto);
    }

    @Transactional
    public UsuarioResponseDTO associarTipoUsuario(Long usuarioId, Long tipoUsuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado com o ID: " + usuarioId));

        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(tipoUsuarioId)
                .orElseThrow(() -> new RegraNegocioException("Tipo de usuário não encontrado com o ID: " + tipoUsuarioId));

        usuario.setTipoUsuario(tipoUsuario);

        var usuarioSaved = usuarioRepository.save(usuario);
        return convertToUsuarioResponseDTO(usuarioSaved);
    }

    public UsuarioResponseDTO buscarPorEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("E-mail não pode ser nulo");
        }

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new RegraNegocioException("Usuário não encontrado com o e-mail: " + email);
        }
        return convertToUsuarioResponseDTO(usuario);
    }

    private UsuarioResponseDTO convertToUsuarioResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        String tipoUsuarioNome = (usuario.getTipoUsuario() != null) ? usuario.getTipoUsuario().getNome() : "Indefinido";

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                tipoUsuarioNome
        );
    }


    private Usuario convertToUsuario(UsuarioRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTipoUsuario(convertToTipoUsuario(dto.getTipoUsuario()));

        return usuario;
    }

    private TipoUsuario convertToTipoUsuario(TipoUsuarioRequestDTO tipoUsuarioRequestDTO) {
        if (tipoUsuarioRequestDTO == null) {
            return null;
        }
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNome(tipoUsuarioRequestDTO.getNome());
        return tipoUsuario;
    }


    private UsuarioResponseDTO createOrUpdate(UsuarioRequestDTO dto) {
        try {
            Usuario usuario = convertToUsuario(dto);

            if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
                String senhaCodificada = passwordEncoder.encode(dto.getSenha());
                usuario.setSenha(senhaCodificada);
            }

            if (dto.getTipoUsuario() != null && !dto.getTipoUsuario().getNome().isEmpty()) {
                TipoUsuario tipoUsuario = tipoUsuarioRepository.findByNome(dto.getTipoUsuario().getNome());
                if (tipoUsuario == null) {
                    throw new RegraNegocioException("Tipo de usuário não encontrado: " + dto.getTipoUsuario().getNome());
                }
                usuario.setTipoUsuario(tipoUsuario);
            }

            usuario = usuarioRepository.save(usuario);
            return convertToUsuarioResponseDTO(usuario);
        } catch (Exception e) {
            throw new RegraNegocioException("Erro ao salvar/atualizar o usuário: " + e.getMessage());
        }
    }
}