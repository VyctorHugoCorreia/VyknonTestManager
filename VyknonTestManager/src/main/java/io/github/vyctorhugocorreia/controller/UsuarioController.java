package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.CadastroUsuarioDTO;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import io.github.vyctorhugocorreia.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioEntity> salvar(@RequestBody CadastroUsuarioDTO body){
        UsuarioEntity usuarioSalvo = usuarioService.salvar(body.getUsuario(), body.getPerfilDeAcesso());
        return ResponseEntity.ok(usuarioSalvo);
    }
}
