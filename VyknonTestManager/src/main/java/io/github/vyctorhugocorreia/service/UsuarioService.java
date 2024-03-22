package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.UsuarioDTO;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioService {

    UsuarioEntity salvar(UsuarioDTO dto);
    UserDetails loadUserByUsername(String username);
    UserDetails autenticar(UsuarioEntity usuario);

}
