 package com.provodromo.institucional.resource;

 import com.provodromo.institucional.dto.request.NotaRequestDTO;
 import com.provodromo.institucional.dto.response.AlunoCompleteResponseDTO;
 import com.provodromo.institucional.dto.response.NotaCompleteResponseDTO;
 import com.provodromo.institucional.dto.response.NotaResponseDTO;
 import com.provodromo.institucional.resource.base.BaseResource;
 import com.provodromo.institucional.services.NotaService;
 import lombok.AllArgsConstructor;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.web.bind.annotation.*;

 import javax.validation.Valid;
 import java.util.Set;

 @RestController
 @RequestMapping(value = "/nota", produces = {"application/json"})
 @AllArgsConstructor
 public class NotaResource implements BaseResource<NotaRequestDTO, NotaResponseDTO> {
     private final NotaService notaService;

     @GetMapping
     @Override
     public Set<NotaResponseDTO> listar() {
         return notaService.findAll();
     }

     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     @Override
     public NotaResponseDTO criar(@Valid @RequestBody NotaRequestDTO Aluno) {
         return notaService.create(Aluno);
     }

     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     @Override
     public NotaResponseDTO buscar(@PathVariable Long id) {
         return notaService.findById(id);
     }

     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     @Override
     public void excluir(@PathVariable Long id) {
         notaService.deleteById(id);
     }
 }
