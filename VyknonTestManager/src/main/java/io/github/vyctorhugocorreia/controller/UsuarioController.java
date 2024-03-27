package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.UserDTO;
import io.github.vyctorhugocorreia.entity.UserEntity;
import io.github.vyctorhugocorreia.repository.UsuarioRepository;
import io.github.vyctorhugocorreia.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {


    private final UserService userService;
private final UsuarioRepository repository;

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UserEntity> salvar(@RequestBody @Valid UserDTO dto){
        UserEntity usuario = userService.salvar(dto);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<List<UserEntity>> getUsuario(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String perfilDeAcesso
    ) {

        List<UserEntity> usuarios = repository.searchUser(nome, login,perfilDeAcesso);


        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable String id, @RequestParam @Pattern(regexp = "^(ACTIVE|INACTIVE)$") String status) {
        return userService.deletar(id, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> editar(@PathVariable String id, @RequestBody @Valid UserDTO dto) {
        return ResponseEntity.ok(userService.editar(id, dto));
    }


}
