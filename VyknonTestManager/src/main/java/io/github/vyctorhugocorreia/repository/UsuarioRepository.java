package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<UsuarioEntity, String> {

    Optional<UsuarioEntity> findByLogin(String login);
}
