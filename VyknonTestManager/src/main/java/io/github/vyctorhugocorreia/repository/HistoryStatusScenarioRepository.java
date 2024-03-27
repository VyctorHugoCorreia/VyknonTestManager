package io.github.vyctorhugocorreia.repository;

import io.github.vyctorhugocorreia.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface HistoryStatusScenarioRepository extends JpaRepository<HistoryStatusScenarioEntity, Integer> {

    @Query("SELECT h FROM HistoryStatusScenarioEntity h WHERE h.idScenario.idScenario = :idScenario")
    List<HistoryStatusScenarioEntity> findByIdScenario(@Param("idScenario") Long idScenario);

    @Modifying
    @Query("DELETE FROM HistoryStatusScenarioEntity h WHERE h.idScenario.idScenario = :idScenario")
    void deleteByScenario(Long idScenario);
}

