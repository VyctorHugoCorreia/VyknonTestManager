package io.github.vyctorhugocorreia.controller;


import io.github.vyctorhugocorreia.dto.LoginDTO;
import io.github.vyctorhugocorreia.dto.TokenDTO;
import io.github.vyctorhugocorreia.dto.UserDTO;
import io.github.vyctorhugocorreia.entity.UserEntity;
import io.github.vyctorhugocorreia.exception.RuleBusinessException;
import io.github.vyctorhugocorreia.repository.UserRepository;
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
    private final UserRepository userRepository;

    @PostMapping
    public TokenDTO autenticar(@RequestBody LoginDTO dto) {
        try {
            UserEntity usuario = UserEntity.builder()
                    .login(dto.getLogin())
                    .password(dto.getPassword())
                    .build();

            UserDetails userAuthentication = userService.authentication(usuario);
            String token = tokenService.generateToken(usuario);

            String accessProfile = userRepository.findAccessProfileByLogin(dto.getLogin());
            String username = userRepository.findNameByLogin(dto.getLogin());


            return new TokenDTO(usuario.getLogin(), token, accessProfile, username);
        } catch (RuleBusinessException e) {
            throw new RuleBusinessException(e.getMessage());
        }
    }

    @PutMapping("/trocar-senha")
    public ResponseEntity<UserEntity> passwordEdit(@RequestBody @Valid UserDTO dto) {
        return ResponseEntity.ok(userService.passwordEdit(dto));
    }
}
