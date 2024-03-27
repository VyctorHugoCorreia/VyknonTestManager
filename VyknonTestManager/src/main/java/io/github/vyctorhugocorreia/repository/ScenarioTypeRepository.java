package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.ScenarioTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ScenarioTypeRepository extends JpaRepository<ScenarioTypeEntity, Integer> {
    List<ScenarioTypeEntity> findAll();
}

