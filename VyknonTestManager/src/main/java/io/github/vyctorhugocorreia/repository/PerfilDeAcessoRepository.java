package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.PerfilDeAcessoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilDeAcessoRepository extends JpaRepository<PerfilDeAcessoEntity, String> {

    Optional<PerfilDeAcessoEntity> findByNome(String nome);
}
