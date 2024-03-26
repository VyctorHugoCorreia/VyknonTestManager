package io.github.vyctorhugocorreia.util;

import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import io.github.vyctorhugocorreia.exception.RegraNegocioException;
import io.github.vyctorhugocorreia.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserInfo {

    private final UsuarioRepository usuarioRepository;

    public Optional<UsuarioEntity> obterUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UsuarioEntity> usuario = usuarioRepository.findByLogin(authentication.getName());

        return usuario;
    }

    public UsuarioEntity usuario() {
        Optional<UsuarioEntity> usuarioOptional =  obterUsuarioLogado();
        UsuarioEntity usuario = usuarioOptional.orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
        return usuario;
    }
}
