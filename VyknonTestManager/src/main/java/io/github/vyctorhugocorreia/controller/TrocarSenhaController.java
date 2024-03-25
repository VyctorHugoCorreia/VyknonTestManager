package io.github.vyctorhugocorreia.controller;

import io.github.vyctorhugocorreia.dto.UsuarioDTO;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import io.github.vyctorhugocorreia.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trocar-senha")
@RequiredArgsConstructor
@CrossOrigin
public class TrocarSenhaController {


    private final UsuarioService usuarioService;

    @PutMapping
    public ResponseEntity<UsuarioEntity> editar(@RequestBody @Valid UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.editarSenha(dto));
    }
}
