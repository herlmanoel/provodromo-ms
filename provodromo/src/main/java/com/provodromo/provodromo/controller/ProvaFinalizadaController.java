package com.provodromo.provodromo.controller;

import com.provodromo.provodromo.dto.request.ProvaFinalizadaRequestDTO;
import com.provodromo.provodromo.dto.response.ProvaFinalizadaResponseDTO;
import com.provodromo.provodromo.service.ProvaFinalizadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/prova-finalizada")
public class ProvaFinalizadaController {
    @Autowired
    private ProvaFinalizadaService provaFinalizadaService;

    @PostMapping
    public ResponseEntity<ProvaFinalizadaResponseDTO> finalizarProva(@RequestBody ProvaFinalizadaRequestDTO provaFinalizadaRequestDTO) {
        ProvaFinalizadaResponseDTO provaFinalizadaResponseDTO = provaFinalizadaService.finalizarProva(provaFinalizadaRequestDTO);
        return ResponseEntity.ok(provaFinalizadaResponseDTO);
    }

    @GetMapping("/por-usuario-e-prova")
    public ResponseEntity<ProvaFinalizadaResponseDTO> retrieveProvaFinalizadaByUsuarioIdAndProvaId(@QueryParam("usuarioId") Long usuarioId, @QueryParam("provaId") Long provaId) {
        ProvaFinalizadaResponseDTO provaFinalizadaResponseDTO = provaFinalizadaService.retrieveProvaFinalizadaByUsuarioIdAndProvaId(usuarioId, provaId);
        return ResponseEntity.ok(provaFinalizadaResponseDTO);
    }

    @GetMapping("/por-prova")
    public ResponseEntity<List<ProvaFinalizadaResponseDTO>> retrieveByProvaId(@QueryParam("provaId") Long provaId) {
        List<ProvaFinalizadaResponseDTO> provaFinalizadaResponseDTO = provaFinalizadaService.retrieveProvasFinalizadasByProvaId(provaId);
        return ResponseEntity.ok(provaFinalizadaResponseDTO);
    }

    @GetMapping("/por-usuario")
    public ResponseEntity<List<ProvaFinalizadaResponseDTO>> retrieveByUsuarioId(@QueryParam("usuarioId") Long usuarioId) {
        List<ProvaFinalizadaResponseDTO> provaFinalizadaResponseDTO = provaFinalizadaService.retrieveProvasFinalizadasByUsuarioId(usuarioId);
        return ResponseEntity.ok(provaFinalizadaResponseDTO);
    }

    @GetMapping("/por-usuario-e-turma")
    public ResponseEntity<List<ProvaFinalizadaResponseDTO>> retrieveByUsuarioIdAndTurmaId(@QueryParam("usuarioId") Long usuarioId, @QueryParam("turmaId") Long turmaId) {
        List<ProvaFinalizadaResponseDTO> provaFinalizadaResponseDTO = provaFinalizadaService.retrieveProvasFinalizadasByUsuarioIdAndTurmaId(usuarioId, turmaId);
        return ResponseEntity.ok(provaFinalizadaResponseDTO);
    }
}
