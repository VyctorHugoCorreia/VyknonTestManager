package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.LoginDTO;
import io.github.vyctorhugocorreia.dto.TokenDTO;
import io.github.vyctorhugocorreia.dto.UserDTO;
import io.github.vyctorhugocorreia.entity.UserEntity;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.repository.UsuarioRepository;
import io.github.vyctorhugocorreia.security.TokenService;
import io.github.vyctorhugocorreia.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@CrossOrigin
public class LoginController {


    private final TokenService tokenService;
    private final UserService userService;
    private final UsuarioRepository usuarioRepository;

    @PostMapping
    public TokenDTO autenticar(@RequestBody LoginDTO dto) {
        try {
            UserEntity usuario = UserEntity.builder()
                    .login(dto.getLogin())
                    .senha(dto.getSenha())
                    .build();

            UserDetails usuarioAutenticado = userService.autenticar(usuario);
            String token = tokenService.generateToken(usuario);

            String perfilDeAcesso = usuarioRepository.findPerfilDeAcessoByLogin(dto.getLogin());
            String nomeUsuario = usuarioRepository.findNomeByLogin(dto.getLogin());


            return new TokenDTO(usuario.getLogin(), token, perfilDeAcesso, nomeUsuario);
        } catch (RegraNegocioException e) {
            throw new RegraNegocioException(e.getMessage());
        }
    }

    @PutMapping("/trocar-senha")
    public ResponseEntity<UserEntity> editar(@RequestBody @Valid UserDTO dto) {
        return ResponseEntity.ok(userService.editarSenha(dto));
    }
}
