package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface HistoryStatusScenarioRepository extends JpaRepository<HistoryStatusScenarioEntity, Integer> {

    @Query("SELECT h FROM HistoryStatusScenarioEntity h WHERE h.idCenario.idCenario = :idCenario")
    List<HistoryStatusScenarioEntity> findByCenarioId(@Param("idCenario") Long idCenario);
}

