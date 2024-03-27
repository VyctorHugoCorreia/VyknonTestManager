package io.github.vyctorhugocorreia.util;

import io.github.vyctorhugocorreia.entity.UserEntity;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserInfo {

    private final UsuarioRepository userRepository;

    public Optional<UserEntity> getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserEntity> user = userRepository.findByLogin(authentication.getName());

        return user;
    }

    public UserEntity user() {
        Optional<UserEntity> userEntityOptional =  getLoggedInUser();
        UserEntity user = userEntityOptional.orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        return user;
    }
}
