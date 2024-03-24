package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.LoginDTO;
import io.github.vyctorhugocorreia.dto.TokenDTO;
import io.github.vyctorhugocorreia.dto.UsuarioDTO;
import io.github.vyctorhugocorreia.entity.ProdutoEntity;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import io.github.vyctorhugocorreia.repository.UsuarioRepository;
import io.github.vyctorhugocorreia.security.TokenService;
import io.github.vyctorhugocorreia.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {


    private final UsuarioService usuarioService;
private final UsuarioRepository repository;

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UsuarioEntity> salvar(@RequestBody @Valid UsuarioDTO dto){
        UsuarioEntity usuario = usuarioService.salvar(dto);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<UsuarioEntity>> getUsuario(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String perfilDeAcesso
    ) {

        List<UsuarioEntity> usuarios = repository.searchUser(nome, login,perfilDeAcesso);


        return ResponseEntity.ok(usuarios);
    }

}
