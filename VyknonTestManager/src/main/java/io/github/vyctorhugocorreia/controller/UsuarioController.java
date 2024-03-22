package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.LoginDTO;
import io.github.vyctorhugocorreia.dto.TokenDTO;
import io.github.vyctorhugocorreia.dto.UsuarioDTO;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;
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

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {


    private final TokenService tokenService;
    private final UsuarioService usuarioService;


    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<UsuarioEntity> salvar(@RequestBody @Valid UsuarioDTO dto){
        UsuarioEntity usuario = usuarioService.salvar(dto);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody LoginDTO dto) {
        try {
            UsuarioEntity usuario = UsuarioEntity.builder()
                    .login(dto.getLogin())
                    .senha(dto.getSenha())
                    .build();

            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = tokenService.generateToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);

        } catch (UsernameNotFoundException | ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
