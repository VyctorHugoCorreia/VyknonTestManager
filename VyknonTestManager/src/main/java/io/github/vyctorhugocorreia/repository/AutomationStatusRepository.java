package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.AutomationStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AutomationStatusRepository extends JpaRepository<AutomationStatusEntity, Integer> {

    List<AutomationStatusEntity> findAll();
}

