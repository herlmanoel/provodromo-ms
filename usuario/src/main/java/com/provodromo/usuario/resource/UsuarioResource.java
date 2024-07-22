 package com.provodromo.usuario.resource;


 import com.provodromo.usuario.dto.request.UsuarioRequestDTO;
 import com.provodromo.usuario.dto.response.UsuarioCompleteResponseDTO;
 import com.provodromo.usuario.dto.response.UsuarioResponseDTO;
 import com.provodromo.usuario.resource.base.BaseResource;
 import com.provodromo.usuario.services.UsuarioService;
 import lombok.AllArgsConstructor;
 import org.springframework.http.HttpStatus;
 import org.springframework.web.bind.annotation.*;

 import javax.validation.Valid;
 import java.util.Set;

 @RestController
 @RequestMapping(value = "/usuario", produces = {"application/json"})
 @AllArgsConstructor
 public class UsuarioResource implements BaseResource<UsuarioRequestDTO, UsuarioResponseDTO> {

     private final UsuarioService usuarioService;

     @GetMapping
     @Override
     public Set<UsuarioResponseDTO> listar() {
         return usuarioService.findAll();
     }

     @GetMapping("/search")
     @ResponseStatus(HttpStatus.OK)
     public UsuarioCompleteResponseDTO buscarPorEmail(@RequestParam String email) {
        return usuarioService.buscarPorEmail(email);
     }

     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     @Override
     public UsuarioResponseDTO criar(@Valid @RequestBody UsuarioRequestDTO usuario) {
         return usuarioService.create(usuario);
     }

     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     @Override
     public UsuarioResponseDTO buscar(@PathVariable Long id) {
         return usuarioService.findById(id);
     }

     @PutMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     @Override
     public UsuarioResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO usuario) {
         return usuarioService.update(id, usuario);
     }

     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     @Override
     public void excluir(@PathVariable Long id) {
         usuarioService.deleteById(id);
     }

     @PutMapping("/{usuarioId}/tipo/{tipoUsuarioId}")
     @ResponseStatus(HttpStatus.OK)
     public UsuarioResponseDTO associarTipoUsuario(@PathVariable Long usuarioId, @PathVariable Long tipoUsuarioId) {
         return usuarioService.associarTipoUsuario(usuarioId, tipoUsuarioId);
     }
 }
