 package com.provodromo.institucional.resource;

 import com.provodromo.institucional.dto.request.AlunoRequestDTO;
 import com.provodromo.institucional.dto.response.AlunoCompleteResponseDTO;
 import com.provodromo.institucional.dto.response.AlunoResponseDTO;
 import com.provodromo.institucional.resource.base.BaseResource;
 import com.provodromo.institucional.services.AlunoService;
 import lombok.AllArgsConstructor;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.web.bind.annotation.*;

 import javax.validation.Valid;
 import java.util.Set;

 @RestController
 @RequestMapping(value = "/api/aluno", produces = {"application/json"})
 @AllArgsConstructor
 public class AlunoResource implements BaseResource<AlunoRequestDTO, AlunoResponseDTO> {
     private final AlunoService alunoService;

     @GetMapping
     @Override
     public Set<AlunoResponseDTO> listar() {
         return alunoService.findAll();
     }

     @PostMapping
     @ResponseStatus(HttpStatus.CREATED)
     @Override
     public AlunoResponseDTO criar(@Valid @RequestBody AlunoRequestDTO Aluno) {
         return alunoService.create(Aluno);
     }

     @GetMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     @Override
     public AlunoResponseDTO buscar(@PathVariable Long id) {
         return alunoService.findById(id);
     }

     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     @Override
     public void excluir(@PathVariable Long id) {
         alunoService.deleteById(id);
     }

     @PutMapping("/{alunoId}/materia/{turmaId}")
     @ResponseStatus(HttpStatus.OK)
     public AlunoCompleteResponseDTO associarTurma(@PathVariable Long alunoId, @PathVariable Long turmaId) {
         return alunoService.associarTurma(alunoId, turmaId);
     }

 }
