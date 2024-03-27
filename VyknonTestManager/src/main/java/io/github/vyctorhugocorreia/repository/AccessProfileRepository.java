package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.AccessProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessProfileRepository extends JpaRepository<AccessProfileEntity, String> {

    Optional<AccessProfileEntity> findByName(String name);
    boolean existsByName(String nome);
}
