package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.AccessProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilDeAcessoRepository extends JpaRepository<AccessProfileEntity, String> {

    Optional<AccessProfileEntity> findByNome(String nome);
    boolean existsByNome(String nome);
}
