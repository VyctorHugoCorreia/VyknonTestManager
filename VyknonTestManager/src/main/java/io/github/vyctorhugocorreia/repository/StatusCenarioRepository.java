package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.StatusAutomatizadoEntity;
import io.github.vyctorhugocorreia.entity.StatusCenarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StatusCenarioRepository extends JpaRepository<StatusCenarioEntity, Integer> {

    List<StatusCenarioEntity> findAll();
}

