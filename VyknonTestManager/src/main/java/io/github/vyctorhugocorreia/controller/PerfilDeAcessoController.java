package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.CadastroUsuarioDTO;
import io.github.vyctorhugocorreia.entity.PerfilDeAcessoEntity;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import io.github.vyctorhugocorreia.repository.PerfilDeAcessoRepository;
import io.github.vyctorhugocorreia.service.UsuarioService;
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

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<PerfilDeAcessoEntity> salvar(@RequestBody PerfilDeAcessoEntity perfilDeAcesso){
        repository.save(perfilDeAcesso);
        return ResponseEntity.ok(perfilDeAcesso);
    }

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<PerfilDeAcessoEntity>> listar(){
        return ResponseEntity.ok(repository.findAll());
    }
}
