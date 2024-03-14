package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.TipoCenarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TipoCenarioRepository extends JpaRepository<TipoCenarioEntity, Integer> {
    List<TipoCenarioEntity> findAll();
}

