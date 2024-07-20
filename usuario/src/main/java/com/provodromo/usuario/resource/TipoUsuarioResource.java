package com.provodromo.usuario.resource;

import com.provodromo.usuario.dto.request.TipoUsuarioRequestDTO;
import com.provodromo.usuario.dto.response.TipoUsuarioResponseDTO;
import com.provodromo.usuario.resource.base.BaseResource;
import com.provodromo.usuario.services.TipoUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/tipoUsuario", produces = {"application/json"})
@AllArgsConstructor
public class TipoUsuarioResource implements BaseResource<TipoUsuarioRequestDTO, TipoUsuarioResponseDTO> {

    private TipoUsuarioService tipoUsuarioService;


    @GetMapping
    public Set<TipoUsuarioResponseDTO> listar() {
        return tipoUsuarioService.findAll();
    }

    @PostMapping
    public TipoUsuarioResponseDTO criar(@Valid @RequestBody TipoUsuarioRequestDTO tipoUsuario) {
        return tipoUsuarioService.create(tipoUsuario);
    }

    @GetMapping("/{id}")
    public TipoUsuarioResponseDTO buscar(@PathVariable Long id) {
        return tipoUsuarioService.findById(id);
    }

    @PutMapping("/{id}")
    public TipoUsuarioResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody TipoUsuarioRequestDTO tipoUsuario) {
        return tipoUsuarioService.update(id, tipoUsuario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        tipoUsuarioService.deleteById(id);
    }
}

