package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.TipoPlataformaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TipoPlataformaRepository extends JpaRepository<TipoPlataformaEntity, Integer> {

    List<TipoPlataformaEntity> findAll();
}

