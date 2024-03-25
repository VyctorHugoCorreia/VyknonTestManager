package io.github.vyctorhugocorreia.util;

import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import io.github.vyctorhugocorreia.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@RequiredArgsConstructor
public class UserInfo {

    private final UsuarioRepository usuarioRepository;

    public Optional<UsuarioEntity> obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UsuarioEntity> usuario = usuarioRepository.findByLogin(authentication.getName());
        return usuario;
    }
}
