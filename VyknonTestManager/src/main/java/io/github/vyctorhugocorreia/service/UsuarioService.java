package io.github.vyctorhugocorreia.service;

import io.github.vyctorhugocorreia.dto.ProdutoDTO;
import io.github.vyctorhugocorreia.dto.UsuarioDTO;
import io.github.vyctorhugocorreia.entity.ProdutoEntity;
import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioService {

    UsuarioEntity salvar(UsuarioDTO dto);
    UserDetails loadUserByUsername(String username);
    UserDetails autenticar(UsuarioEntity usuario);

    String deletar(String id, @Pattern(regexp = "^(ACTIVE|INACTIVE)$") String status);
    UsuarioEntity editar(String id, UsuarioDTO dto);

}
