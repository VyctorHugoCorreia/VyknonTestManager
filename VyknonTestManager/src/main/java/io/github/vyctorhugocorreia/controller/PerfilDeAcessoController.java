package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.AccessProfileDTO;
import io.github.vyctorhugocorreia.entity.AccessProfileEntity;
import io.github.vyctorhugocorreia.repository.PerfilDeAcessoRepository;
import io.github.vyctorhugocorreia.service.AccessProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfilDeAcesso")
@RequiredArgsConstructor
@CrossOrigin
public class PerfilDeAcessoController {

    private final PerfilDeAcessoRepository repository;
    private final AccessProfileService service;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<AccessProfileEntity> salvar(@RequestBody @Valid AccessProfileDTO dto){
        AccessProfileEntity perfil = service.salvar(dto);
        return ResponseEntity.ok(perfil);
    }


    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<AccessProfileEntity>> listar(){
        return ResponseEntity.ok(repository.findAll());
    }
}
