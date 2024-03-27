package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.ScenarioStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ScenarioStatusRepository extends JpaRepository<ScenarioStatusEntity, Integer> {

    List<ScenarioStatusEntity> findAll();
}

