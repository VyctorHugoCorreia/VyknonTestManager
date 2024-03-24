package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.LoginDTO;
import io.github.vyctorhugocorreia.dto.TokenDTO;
import io.github.vyctorhugocorreia.dto.UsuarioDTO;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
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

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {


    private final TokenService tokenService;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @PostMapping
    public TokenDTO autenticar(@RequestBody LoginDTO dto) {
        try {
            UsuarioEntity usuario = UsuarioEntity.builder()
                    .login(dto.getLogin())
                    .senha(dto.getSenha())
                    .build();

            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = tokenService.generateToken(usuario);

            String perfilDeAcesso = usuarioRepository.findPerfilDeAcessoByLogin(dto.getLogin());
            String nomeUsuario = usuarioRepository.findNomeByLogin(dto.getLogin());


            return new TokenDTO(usuario.getLogin(), token, perfilDeAcesso, nomeUsuario);
        } catch (RegraNegocioException e) {
            throw new RegraNegocioException(e.getMessage());
        }
    }

}
